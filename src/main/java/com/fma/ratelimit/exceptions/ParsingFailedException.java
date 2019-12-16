package com.fma.ratelimit.exceptions;

public class ParsingFailedException  extends Exception {
    public ParsingFailedException(String errorMessage) {
        super(errorMessage);
    }
}
