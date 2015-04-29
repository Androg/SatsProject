package se.tuppload.android.satstrainingapp;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

public class RequestJson {

    public String getJsonData(String url) {
        final String[] rawJsonResponse = new String[1];

       SatsRestClient.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                rawJsonResponse[0] = jsonResponse.toString();
            }
        });

        String tempRawJsonResponse = rawJsonResponse[0];
        return tempRawJsonResponse;

    }
}
