package com.mit.http.exception;

/**
 * Created by Lap Pham on 2/15/17.
 */
public class UnsupportSocialTypeException extends Exception {
    public UnsupportSocialTypeException() {
    }

    public UnsupportSocialTypeException(String message) {
        super(message);
    }
}
