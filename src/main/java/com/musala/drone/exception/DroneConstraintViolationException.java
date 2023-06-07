package com.musala.drone.exception;

import lombok.Getter;

@Getter
public class DroneConstraintViolationException extends RuntimeException {
    private String constraint;

    public DroneConstraintViolationException(String message, String constraint) {
        super(message);
        this.constraint = constraint;
    }
}
