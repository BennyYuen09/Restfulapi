package com.afs.restfulapi.controllerAdvice;

import com.afs.restfulapi.CompanyNotFoundException;
import com.afs.restfulapi.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({CompanyNotFoundException.class, EmployeeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(Exception exception){
        return new ErrorResponse(404, exception.getMessage());
    }
}
