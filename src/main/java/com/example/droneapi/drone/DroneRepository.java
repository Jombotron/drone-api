package com.example.droneapi.drone;

import com.example.droneapi.drone.enums.DroneState;
import com.musalatask.Musalatask.drone.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<DroneEntity, Long> {
    @Query("SELECT d FROM DroneEntity d WHERE d.state in (:ds1, :ds2)")
    List<DroneEntity> findDronesWhereStateIs(DroneState ds1, DroneState ds2);
}
