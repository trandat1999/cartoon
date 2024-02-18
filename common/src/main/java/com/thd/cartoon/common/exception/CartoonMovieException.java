package com.thd.cartoon.common.exception;

/**
 * @author DatNuclear 19/01/2024
 * @project cartoon-movie
 */
public class CartoonMovieException extends RuntimeException {
    public CartoonMovieException(String message) {
        super(message);
    }

    public CartoonMovieException(String message, Exception exception) {
        super(message, exception);
    }
}
