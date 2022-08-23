package com.example.droneapi.drone;


//import com.musalatask.Musalatask.model.DroneMedications;
import com.musalatask.Musalatask.medication.Medication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {
    ResponseEntity<DroneEntity>createDrone(DroneRegistrationPayload droneDto);
    ResponseEntity<List<DroneEntity>> fetchAllDrones();
    ResponseEntity<?> loadDroneWithMedication(Long droneId, Long medicationId);
    ResponseEntity<List<Medication>> fetchMedicationsForADrone(Long droneId);
    ResponseEntity<List<DroneEntity>> fetchAllAvailableDrones();
    ResponseEntity<String> fetchDroneBatteryLevel(Long droneId);
}
