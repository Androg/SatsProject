package se.tuppload.android.satstrainingapp;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SatsRestClient {

    private static final String DB_URL = "https://api.parse.com/1/classes/activities?include=bookingId.class,subType";
    private static final String CENTER_URL = "https://api2.sats.com/v1.0/se/centers/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type", "application/json");
        client.addHeader("X-Parse-Application-Id", "23p8xhISFQKfAfDa0kdS8NYnuKwiXHolJmXWLMyi");
        client.addHeader("X-Parse-REST-API-Key", "fKgzdx8dze90xyzlMY8e5uLcry6bT131ixcPcUfr");
        client.get(DB_URL, responseHandler);
    }

    public static void getCenter(String centerId, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type", "application/json");
        client.get(getCenterUrl(centerId), responseHandler);
    }

    private static String getCenterUrl(String centerId) {
        return CENTER_URL + centerId;
    }

}
