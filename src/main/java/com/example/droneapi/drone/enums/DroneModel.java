package com.example.droneapi.drone.enums;

public enum DroneModel {
    LIGHT_WEIGHT (200.0),
    MIDDLE_WEIGHT (300.0),
    CRUISER_WEIGHT (450.0),
    HEAVY_WEIGHT (500.0);

    private final double weight;

    DroneModel (double weight){
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
