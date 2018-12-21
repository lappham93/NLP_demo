package com.mit.http;

/**
 * Created by Lap Pham on 2/13/17.
 */
public interface RequestVerificationManager {

    boolean isAllowRequest(String apiKey, String data, String sign);

    public String getAppName(String apiKey);
}
