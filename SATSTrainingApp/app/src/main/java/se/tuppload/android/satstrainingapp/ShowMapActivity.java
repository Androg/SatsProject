package se.tuppload.android.satstrainingapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import se.tuppload.android.satstrainingapp.Model.Center;


public class ShowMapActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    ImageView view;
    public GoogleMap map;
    HashMap<String, LatLng> markers = new HashMap();
    RequestJson requestJson = new RequestJson();
    HashMap<String, Center> centers = requestJson.centers;
    //public static double longitude = 18.0785538;
    //public static double latitude = 59.2937625;
    private GoogleApiClient amap ;
    String[] centerUrl = new String[centers.size()];

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);

        //view = (ImageView) findViewById(R.id.location_arrow_test);
        //view.setOnClickListener(this);

        Log.e("centerscenterscenters", String.valueOf(centers.size()));

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        int i = 0;
        if (map != null) {
            for (final Map.Entry<String, Center> center : centers.entrySet()) {
                centerUrl[i] = center.getValue().url;
                Marker Stockholm = map.addMarker(new MarkerOptions()
                        .position(new LatLng(center.getValue().latitude, center.getValue().longitude))
                        .title("SATS " + center.getValue().name)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.sats_pin_normal)));

                // Stockholm.showInfoWindow();
                // Stockholm.hideInfoWindow();

                Log.e("POSITIONPOSITION", String.valueOf(i));
                i++;

            }
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude) ,14) );
        }
        map.setMyLocationEnabled(true);

//        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                Intent intent = new Intent(ShowMapActivity.this, ShowWebActivity.class);
//                startActivity(intent);
//            }
//        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String markerId = marker.getId();
                markerId.split("m");
                String[] stringId = markerId.split("m");
                int id = Integer.parseInt(stringId[1]);
                Intent intent = new Intent(ShowMapActivity.this, ShowWebActivity.class);
                intent.putExtra("URL", centerUrl[id]);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onConnected(Bundle bundle) {

        Location loc = LocationServices.FusedLocationApi.getLastLocation(amap);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_arrow_test:
                buttonClick();
                break;
        }
    }

    private void buttonClick() {
        startActivity(new Intent(".ShowWebActivity"));
    }*/
}