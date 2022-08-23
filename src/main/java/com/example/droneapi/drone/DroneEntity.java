package com.example.droneapi.drone;

import com.example.droneapi.drone.enums.DroneModel;
import com.example.droneapi.drone.enums.DroneState;
import com.example.droneapi.medication.Medication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drone")
public class DroneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 1, max = 100)
    @Column( unique = true, name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "battery_percentage", nullable = false, columnDefinition = "float default 100")
    @Size(max = 100)
    private double batteryPercentage;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private double currentWeight;

    @Column(columnDefinition = "varchar(32) default 'IDLE'")
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedTime;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Medication> medicationList;
}
