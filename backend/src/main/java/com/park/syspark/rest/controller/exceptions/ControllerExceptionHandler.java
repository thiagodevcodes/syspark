package com.park.syspark.rest.controller.exceptions;

import com.park.syspark.service.exceptions.role.RoleInsertException;
import com.park.syspark.service.exceptions.role.RoleNotFoundException;
import com.park.syspark.service.exceptions.role.RoleUpdateException;
import com.park.syspark.service.exceptions.user.InvalidCredentials;
import com.park.syspark.service.exceptions.user.UserInsertException;
import com.park.syspark.service.exceptions.user.UserNotFoundException;
import com.park.syspark.service.exceptions.user.UserUpdateException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionsDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMsg = ex.getBindingResult().getFieldError().getDefaultMessage();
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Request",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserInsertException.class)
    public ResponseEntity<ExceptionsDto> handleUserInsertException(UserInsertException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionsDto> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<ExceptionsDto> handleUserUpdateException(UserUpdateException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionsDto> handleRoleNotFoundException(RoleNotFoundException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleInsertException.class)
    public ResponseEntity<ExceptionsDto> handleProductInsertException(RoleInsertException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoleUpdateException.class)
    public ResponseEntity<ExceptionsDto> handleProductUpdateException(RoleUpdateException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionsDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String errorMsg = ex.getMessage();
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Request",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ExceptionsDto> handleInvalidCredentials(InvalidCredentials ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.UNAUTHORIZED);
    }
}