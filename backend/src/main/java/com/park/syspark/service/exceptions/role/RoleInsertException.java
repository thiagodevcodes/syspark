package com.park.syspark.service.exceptions.role;

public class RoleInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RoleInsertException(String msg) { super(msg); }

    public RoleInsertException(String msg, Throwable cause) {super(msg, cause);}
}

