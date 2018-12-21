package com.mit.http.exception;

/**
 * Created by Lap Pham on 2/13/17.
 */
public class TokenExpireException extends Exception {

    public TokenExpireException() {
    }

    public TokenExpireException(String message) {
        super(message);
    }
}
