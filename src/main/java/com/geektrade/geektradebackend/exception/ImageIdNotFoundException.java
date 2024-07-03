package com.geektrade.geektradebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ImageIdNotFoundException extends RuntimeException {
    public ImageIdNotFoundException(String message){super(message);}
}

