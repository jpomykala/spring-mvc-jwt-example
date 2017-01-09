package me.jpomykala.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by evelan on 07/01/2017.
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionController {


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accessDenied(AccessDeniedException accessDeniedException) {
        log.error("Access denied", accessDeniedException);
        return "Access denied";
    }

    @ExceptionHandler({Exception.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String error(Exception e) {
        e.printStackTrace();
        return "Well, shit...";
    }

}
