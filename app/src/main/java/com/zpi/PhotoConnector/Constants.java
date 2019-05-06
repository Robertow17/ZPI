package com.zpi.PhotoConnector;

class Constants {
    private static final String SERVER_URI = "http://10.0.2.2:8090/photos/experiment/";

    static String getRequestUrl(String serviceId, String photoId) {
        return SERVER_URI + serviceId + "/" + photoId;
    }
}
