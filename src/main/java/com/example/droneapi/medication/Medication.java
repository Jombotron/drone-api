package com.example.droneapi.medication;

import com.example.droneapi.drone.DroneEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Pattern(regexp="^[a-zA-Z0-9_-]$",message="Should contain only letters, numbers, ‘-‘ and ‘_")
    private String name;

    @Pattern(regexp="^[A-Z0-9_]$",message="Should contain only upper case, numbers and ‘_")
    private String code;

    private double weight;

    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    @JsonIgnore
    private DroneEntity drone;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedTime;

}
