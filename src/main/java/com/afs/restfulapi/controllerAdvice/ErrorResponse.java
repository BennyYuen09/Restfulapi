package com.afs.restfulapi.controllerAdvice;

public class ErrorResponse {
    private Integer httpCode;
    private String message;

    public ErrorResponse(Integer httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
