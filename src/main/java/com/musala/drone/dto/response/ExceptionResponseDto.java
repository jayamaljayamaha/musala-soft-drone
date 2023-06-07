package com.musala.drone.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponseDto {
    private String message;
    private String field;
}
