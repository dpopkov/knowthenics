package ru.dpopkov.knowthenics.exceptions.data;

public class NotFoundInRepositoryException extends RuntimeException {

    public NotFoundInRepositoryException(String message) {
        super(message);
    }

    public NotFoundInRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
