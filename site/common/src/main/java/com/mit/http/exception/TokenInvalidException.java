package com.mit.http.exception;

/**
 * Created by Lap Pham on 2/13/17.
 */
public class TokenInvalidException extends Exception {

    public TokenInvalidException() {
    }

    public TokenInvalidException(String message) {
        super(message);
    }
}
