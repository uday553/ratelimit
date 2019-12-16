package com.fma.ratelimit.exceptions;

public class ServiceNotRegisteredException  extends Exception {
    public ServiceNotRegisteredException(String errorMessage) {
        super(errorMessage);
    }
}
