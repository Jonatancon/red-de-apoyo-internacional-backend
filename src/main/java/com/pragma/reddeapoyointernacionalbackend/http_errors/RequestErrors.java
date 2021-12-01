package com.pragma.reddeapoyointernacionalbackend.http_errors;

import org.springframework.http.HttpStatus;

public class RequestErrors extends RuntimeException{

    private final String code;
    private final HttpStatus httpStatus;

    public RequestErrors(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
