package com.musala.drone.repository;

import com.musala.drone.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query("SELECT m.weight FROM Medication m JOIN m.drone d WHERE d.serialNumber = :serialNumber")
    List<Integer> getAllWeightsByDroneSerialNumber(@Param("serialNumber") String serialNumber);

    @Query("SELECT m FROM Medication m JOIN m.drone d WHERE d.serialNumber = :serialNumber")
    List<Medication> getAllByDroneSerialNumber(@Param("serialNumber") String serialNumber);
}
