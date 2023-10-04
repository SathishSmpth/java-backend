package com.threepm.threepm.exception;

import org.springframework.http.HttpStatus;

public class PostsApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
    public PostsApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public PostsApiException(String message, HttpStatus status, String message2) {
        super(message);
        this.status = status;
        message = message2;
    }
    
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    

    
}
