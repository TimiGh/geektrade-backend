package com.geektrade.geektradebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CredentialsDoNotMatchException extends RuntimeException {
    public CredentialsDoNotMatchException(String message){super(message);}
}

