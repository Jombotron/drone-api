package com.example.droneapi.constants;

public class Default_Messages {

    // ERROR MESSAGES
    public final static String DRONE_NOT_FOUND = "Drone with id %d does not exist.";
    public final static String MEDICATION_NOT_FOUND = "Medication with id %d does not exist.";
    public final static String DRONE_NOT_AVAILABLE = "Drone with id %d not available currently.";
    public final static String DRONE_BATTERY_LOW = "Drone with id %d has a low battery.";
    public final static String CAPACITY_EXCEEDED = "Capacity %.2f gms exceeded for drone with id %d. %.2f gms remaining.";
    public static final String MEDICATION_ATTACHED_EXCEPTION = "Medication already attached to a drone." ;

    // SUCCESS MESSAGES
    public final static String MEDICATION_LOAD_SUCCESS = "Medication with id %d added successfully to drone with id %d.";
    public final static String MEDICATION_CREATED_SUCCESS = "Medication with name %s was added successfully.";


    // Informational
    public final static String DRONE_BATTERY_LEVEL = "Drone battery level is %.0f%%";
    public static final String BATTERY_LEVEL_REPORT = "Drone with serial no {} has {} battery level.";
}
