package com.example.demo.exception;

public class DecommissionedSatelliteException extends RuntimeException {
    public DecommissionedSatelliteException(String message) {
        super(message);
    }
}