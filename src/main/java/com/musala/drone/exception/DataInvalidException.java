package com.musala.drone.exception;

import lombok.Getter;

@Getter
public class DataInvalidException extends RuntimeException {
    String field;

    public DataInvalidException(String message, String field) {
        super(message);
        this.field = field;
    }
}
