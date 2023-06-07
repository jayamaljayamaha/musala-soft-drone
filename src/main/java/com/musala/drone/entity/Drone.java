package com.musala.drone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    private String model;

    @Column(name = "weight_capacity")
    private Integer weightCapacity;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    private String state;

}
