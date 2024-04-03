package com.geektrade.geektradebackend.exception;

public class UserNotFoundForIdException extends RuntimeException {
    public UserNotFoundForIdException(Long id) { super(String.valueOf(id)); }
}
