package com.musala.drone.repository;

import com.musala.drone.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    @Query("SELECT d FROM Drone d WHERE d.state IN :statuses")
    Set<Drone> getDronesByStatuses(@Param("statuses") Set<String> statuses);

    Optional<Drone> findDroneByBatteryCapacity(Integer batteryCapacity);
}
