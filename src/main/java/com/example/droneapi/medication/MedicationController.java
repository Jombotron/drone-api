package com.example.droneapi.medication;

//import com.musalatask.Musalatask.model.DroneMedications;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/medication")
public class MedicationController {

    private MedicationService medicationService;

    @PostMapping
    public ResponseEntity<?>registerMedication(@RequestBody MedicationPayload medicationDto){
        return medicationService.createMedication(medicationDto);
    }


}
