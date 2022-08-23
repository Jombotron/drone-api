package com.example.droneapi.medication;

import org.springframework.http.ResponseEntity;

public interface MedicationService {
    ResponseEntity<String> createMedication(MedicationPayload medicationPayload);
}
