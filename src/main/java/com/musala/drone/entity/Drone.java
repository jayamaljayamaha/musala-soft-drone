package com.musala.drone.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "drone")
@Data
@Builder
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number")
    private Integer serialNumber;

    private String model;

    @Column(name = "weight_capacity")
    private Integer weightCapacity;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    private String state;

}
