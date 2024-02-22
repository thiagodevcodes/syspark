package com.park.syspark.service.exceptions.user;

public class UserInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserInsertException(String msg) { super(msg); }

    public UserInsertException(String msg, Throwable cause) {super(msg, cause);}
}

