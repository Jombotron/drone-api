package com.example.droneapi.drone;

//import com.musalatask.Musalatask.model.DroneMedications;

import com.example.droneapi.medication.Medication;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/drone")
public class DispatchController {
    private DroneService droneService;

    @GetMapping("/all")
    public ResponseEntity<List<DroneEntity>> fetchAllDrone(){
        return droneService.fetchAllDrones();
    }

    @GetMapping("/medication")
    private ResponseEntity<List<Medication>> checkMedicationsForGivenDrone (@RequestParam("drone-id")Long droneId){
        return droneService.fetchMedicationsForADrone(droneId);
    }

    @GetMapping("/available")
    private ResponseEntity<List<DroneEntity>> checkAllAvailableDrones (){
        return droneService.fetchAllAvailableDrones();
    }

    @GetMapping("/battery-level")
    private ResponseEntity<String> checkDroneBatteryLevel (@RequestParam("drone-id")Long droneId){
        return droneService.fetchDroneBatteryLevel(droneId);
    }

    @PostMapping
    public ResponseEntity<DroneEntity> registerDrone (@RequestBody DroneRegistrationPayload droneDto){
        return droneService.createDrone(droneDto);
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadDroneWithMedication(@RequestParam("drone-id")Long droneId, @RequestParam("med-id") Long medicationId){
        return droneService.loadDroneWithMedication(droneId,medicationId);
    }


}
