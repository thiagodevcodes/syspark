package com.park.syspark.service.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String msg) { super(msg); }

    public UserNotFoundException(String msg, Throwable cause) {super(msg, cause);}
}
