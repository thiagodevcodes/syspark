package com.park.syspark.service.exceptions.user;

public class InvalidCredentials extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCredentials(String msg) { super(msg); }

    public InvalidCredentials(String msg, Throwable cause) {super(msg, cause);}
}

