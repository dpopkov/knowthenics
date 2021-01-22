package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundInRepositoryException.class)
    public ModelAndView handleNotFoundInRepository(Exception exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST);
    }

    private ModelAndView handleException(Exception exception, HttpStatus status) {
        log.error("Handling {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/" + status.value() + "error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
