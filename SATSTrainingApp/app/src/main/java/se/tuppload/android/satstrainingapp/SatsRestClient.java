package se.tuppload.android.satstrainingapp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SatsRestClient {
//    private static final String BASE_URL = "https://api.parse.com/1/classes/class?include=classTypeId";
//    private static final String BASE_CENTER_URL =  "https://api2.sats.com/v1.0/se/centers/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type", "application/json");
        client.addHeader("X-Parse-Application-Id", "23p8xhISFQKfAfDa0kdS8NYnuKwiXHolJmXWLMyi");
        client.addHeader("X-Parse-REST-API-Key", "fKgzdx8dze90xyzlMY8e5uLcry6bT131ixcPcUfr");
        client.get(url, params, responseHandler);
    }

//    public static void getCenterName(RequestParams params,
//                           AsyncHttpResponseHandler responseHandler) {
//        client.addHeader("Content-Type", "application/json");
//        client.addHeader("X-Parse-Application-Id", "23p8xhISFQKfAfDa0kdS8NYnuKwiXHolJmXWLMyi");
//        client.addHeader("X-Parse-REST-API-Key", "fKgzdx8dze90xyzlMY8e5uLcry6bT131ixcPcUfr");
//        client.get(BASE_CENTER_URL, params, responseHandler);
//    }

}
