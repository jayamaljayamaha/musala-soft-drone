package com.musala.drone.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DroneDto {

    @JsonProperty(value = "serial_number")
    @NotNull
    @NotBlank
    @Schema(defaultValue = "ABCD1234")
    private String serialNumber;

    @NotNull
    @NotBlank
    @Schema(defaultValue = "Lightweight")
    private String model;

    @JsonProperty(value = "weight_limit")
    @NotNull
    @Min(0)
    @Max(500)
    @Schema(defaultValue = "400")
    private Integer weightLimit;

    @JsonProperty(value = "battery_capacity")
    @NotNull
    @Min(0)
    @Max(100)
    @Schema(defaultValue = "100")
    private Integer batteryCapacity;

    @NotNull
    @NotBlank
    @Schema(defaultValue = "IDLE")
    private String state;
}
