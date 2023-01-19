package com.decta.mockapi.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Error {
    private final String timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public Error(Integer status, String message, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
