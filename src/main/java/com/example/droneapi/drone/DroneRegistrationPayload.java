package com.example.droneapi.drone;

import com.example.droneapi.drone.enums.DroneModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegistrationPayload {
    private DroneModel model;
    private String serialNumber;
}
