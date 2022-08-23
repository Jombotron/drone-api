package com.example.droneapi.drone;

import com.example.droneapi.constants.Default_Messages;
import com.example.droneapi.drone.enums.DroneModel;
import com.example.droneapi.drone.enums.DroneState;
import com.example.droneapi.exception.DroneException;
import com.example.droneapi.exception.MedicationException;
import com.example.droneapi.medication.Medication;
import com.example.droneapi.medication.MedicationRepository;
import com.musalatask.Musalatask.constants.Default_Messages;
import com.musalatask.Musalatask.drone.enums.DroneModel;
import com.musalatask.Musalatask.drone.enums.DroneState;
import com.musalatask.Musalatask.exception.DroneException;
import com.musalatask.Musalatask.exception.MedicationException;
import com.musalatask.Musalatask.medication.Medication;
import com.musalatask.Musalatask.medication.MedicationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService{
    private DroneRepository droneRepository;
    private MedicationRepository medicationRepository;

    @Override
    public ResponseEntity<DroneEntity> createDrone(DroneRegistrationPayload droneDto) {
        DroneEntity droneEntity = DroneEntity.builder()
                .model(droneDto.getModel())
                .serialNumber(droneDto.getSerialNumber())
                .batteryPercentage(100.0)
                .state(DroneState.IDLE)
                .build();
        DroneEntity createdDroneEntity = droneRepository.save(droneEntity);
        return new ResponseEntity<>(createdDroneEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<DroneEntity>> fetchAllDrones() {
        return  ResponseEntity.ok(droneRepository.findAll());
    }

    @Override
    public ResponseEntity<String> loadDroneWithMedication (Long droneId, Long medicationId) {
        DroneEntity droneEntity = findDroneByIdOrElseThrowException(droneId);
        Medication medication = findMedicationByIdOrElseThrowException(medicationId);

        if(medication.getDrone() != null) throw new MedicationException(Default_Messages.MEDICATION_ATTACHED_EXCEPTION);

        validateDroneAvailabilityOrElseThrowException(droneEntity, medication);
        droneEntity.setCurrentWeight(droneEntity.getCurrentWeight() + medication.getWeight());

        droneEntity.getMedicationList().add(medication);
        medication.setDrone(droneEntity);

        revaluateDroneState(droneEntity);
        droneRepository.save(droneEntity);
        return ResponseEntity.ok(String.format(Default_Messages.MEDICATION_LOAD_SUCCESS, medicationId, droneId));
    }

    @Override
    public ResponseEntity<List<Medication>> fetchMedicationsForADrone(Long droneId) {
        DroneEntity droneEntity = findDroneByIdOrElseThrowException(droneId);
        return ResponseEntity.ok(droneEntity.getMedicationList());
    }

    @Override
    public ResponseEntity<List<DroneEntity>> fetchAllAvailableDrones() {
        return ResponseEntity.ok(droneRepository.findDronesWhereStateIs(DroneState.IDLE, DroneState.LOADING));
    }

    @Override
    public ResponseEntity<String> fetchDroneBatteryLevel(Long droneId) {
        DroneEntity droneEntity = findDroneByIdOrElseThrowException(droneId);
        String response = String.format(Default_Messages.DRONE_BATTERY_LEVEL, droneEntity.getBatteryPercentage());
        return ResponseEntity.ok(response);
    }


    private DroneEntity findDroneByIdOrElseThrowException(Long droneId) {
        return droneRepository.findById(droneId)
                .orElseThrow(()->new DroneException(
                        String.format(Default_Messages.DRONE_NOT_FOUND, droneId)
                ));
    }

    private Medication findMedicationByIdOrElseThrowException(Long medicationId) {
        return medicationRepository.findById(medicationId)
                .orElseThrow(()-> new MedicationException(
                        String.format(Default_Messages.MEDICATION_NOT_FOUND, medicationId))
                );
    }

    private void validateDroneAvailabilityOrElseThrowException(DroneEntity droneEntity, Medication medication) {
        DroneState droneState = droneEntity.getState();
        DroneModel droneModel = droneEntity.getModel();

        long droneId = droneEntity.getId();
        double droneCapacity = droneModel.getWeight();
        double droneCurrentWeight = droneEntity.getCurrentWeight();

        if(!(droneState.equals(DroneState.IDLE) || droneState.equals(DroneState.LOADING))) {
            throw new DroneException(
                    String.format(Default_Messages.DRONE_NOT_AVAILABLE, droneId));
        }

        if(droneEntity.getBatteryPercentage() < 25) {
            throw new DroneException(
                    String.format(Default_Messages.DRONE_BATTERY_LOW, droneId)
            );
        }

        if (droneCapacity < medication.getWeight() + droneCurrentWeight){
            throw new DroneException(
                    String.format(Default_Messages.CAPACITY_EXCEEDED, droneCapacity, droneId, (droneCapacity - droneCurrentWeight))
            );
        }
    }

    private void revaluateDroneState(DroneEntity droneEntity) {
        if(droneEntity.getCurrentWeight() < droneEntity.getModel().getWeight()){
            droneEntity.setState(DroneState.LOADING);
        } else if(droneEntity.getCurrentWeight() == droneEntity.getModel().getWeight()){
            droneEntity.setState(DroneState.LOADED);
        }
    }

}
