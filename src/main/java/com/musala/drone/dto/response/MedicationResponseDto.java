package com.musala.drone.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musala.drone.entity.Medication;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class MedicationResponseDto {

    @JsonProperty(value = "is_success")
    private boolean isSuccess;

    private Medication medication;
    private ExceptionResponseDto error;
}
