package com.musala.drone.exception;

import com.musala.drone.dto.response.ExceptionResponseDto;
import com.musala.drone.dto.response.MedicationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DroneExceptionHandler {

    @ExceptionHandler(DataInvalidException.class)
    public ResponseEntity<ExceptionResponseDto> handleDataInvalidException(DataInvalidException exception) {
        return new ResponseEntity<>(ExceptionResponseDto.builder().field(exception.getField()).message(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DroneConstraintViolationException.class)
    public ResponseEntity<MedicationResponseDto> handleDroneConstraintViolationException(DroneConstraintViolationException exception) {
        return new ResponseEntity<>(MedicationResponseDto.builder().error(ExceptionResponseDto.builder().field(exception.getConstraint())
                        .message(exception.getMessage()).build()).isSuccess(false).build(), HttpStatus.BAD_REQUEST);
    }
}
