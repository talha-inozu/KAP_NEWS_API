package com.nirengi.kapnews.exception;

import com.nirengi.kapnews.services.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ThreadException.class})
    public ResponseEntity<BaseError> handleRuntimeException(ThreadException exception, HttpServletRequest httpServletRequest) {
        return  ResponseEntity.internalServerError().body(new BaseError(httpServletRequest.getServletPath(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
