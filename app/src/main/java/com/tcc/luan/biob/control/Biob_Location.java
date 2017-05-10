package com.tcc.luan.biob.control;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.tcc.luan.biob.model.Collect;
import com.tcc.luan.biob.model.Learn;
import com.tcc.luan.biob.model.SESSION;
import com.tcc.luan.biob.model.database.DataBase;
import com.tcc.luan.biob.model.util.Item;
import com.tcc.luan.biob.view.MainActivity;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luan on 05/09/16.
 */
public class Biob_Location implements GoogleApiClient.ConnectionCallbacks,
        LocationListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient apiClient;
    private MainActivity context;
    private LocationRequest locationRequest;
    List<Item> close_items = new ArrayList<>();
    public static final long REAL_REFRESH_TIME = 5000;
    public static final long SAFE_REFRESH_TIME = 20000;
    private static long REFRESH_TIME = REAL_REFRESH_TIME;

    public Biob_Location(MainActivity context){
        this.context = context;
    }
    public Biob_Location(){
    }
    public synchronized void callConnection(){
        apiClient = new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        apiClient.connect();
    }
    private void initLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(REFRESH_TIME);
        locationRequest.setFastestInterval(REFRESH_TIME);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    public static void setRefreshTime(long new_time){
        REFRESH_TIME = new_time;
    }

    private void startLocationUpdate(){
        initLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, this);
    }


    public void stopLocationUpdate(){
        LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
    }
    //Listeners
    @Override
    public void onConnected(Bundle bundle) {

        Location l = LocationServices
                .FusedLocationApi
                .getLastLocation(apiClient);
        if (l != null)
            startLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        SESSION.test = true;
        try {
            DataBase db = new DataBase(context);
            if (location != null) {
                Log.i("Location", location.getLatitude() + " - " + location.getLongitude());
                close_items = db.getItensByLocationPoint(location.getLatitude(), location.getLongitude());
                if (close_items.size() > 0 && !context.getBlok().isChecked()) {
                    Collect.prepareCollect(context, close_items);
                } else {
                    Learn.prepareLearn(context);
                }
            }
        }catch (Exception e){}

    }
}
