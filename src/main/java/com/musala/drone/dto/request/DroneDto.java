package com.musala.drone.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DroneDto {

    @JsonProperty(value = "serial_number")
    private String serialNumber;

    private String model;

    @JsonProperty(value = "weight_limit")
    private Integer weightLimit;

    @JsonProperty(value = "battery_capacity")
    private Integer batteryCapacity;

    private String state;
}
