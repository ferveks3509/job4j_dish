package ru.jo4j.job4j_dish.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<ErrorDto> catchNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> catchBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
