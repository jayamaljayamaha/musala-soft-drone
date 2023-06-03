package com.musala.drone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "medication")
@Data
@Builder
public class Medication {

    private String name;

    private Integer weight;

    private String code;

    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_serial_number")
    private Drone drone;
}
