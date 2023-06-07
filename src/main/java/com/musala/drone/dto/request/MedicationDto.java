package com.musala.drone.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicationDto {

    @Pattern(regexp = "^[A-Za-z0-9\\-_]+$")
    private String name;

    @NotNull
    @Min(0)
    private Integer weight;

    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    @NotNull
    @NotBlank
    private String image;
}
