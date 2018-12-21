package com.mit.http.session;

/**
 * Created by Lap Pham on 2/13/17.
 */
public interface SessionManager<T> {
    T get(String sessionKey);
}
