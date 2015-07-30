package com.example.testapplication.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.testapplication.Constants;
import com.example.testapplication.R;
import com.example.testapplication.loader.GeoLoader;
import com.example.testapplication.model.GeoLine;
import com.example.testapplication.model.Order;
import com.example.testapplication.net.NetworkClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<GeoLine>>, OnMapReadyCallback {

    public static final String TAG = "MapActivity";

    public static final int LOADER_GEO_ID = 1;

    private NetworkClient mNetworkClient;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mNetworkClient = NetworkClient.getInstance();
        initMap();

    }

    private void initMap() {
        if (mMap == null) {
            SupportMapFragment  mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public Loader<List<GeoLine>> onCreateLoader(int id, Bundle args) {
        Loader<List<GeoLine>> loader = null;
        if (id == LOADER_GEO_ID) {
            loader = new GeoLoader(this, args);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<GeoLine>> loader, List<GeoLine> data) {
        Log.d(TAG, "get GeoLines succes");
        showMarkers(data);
    }

    @Override
    public void onLoaderReset(Loader<List<GeoLine>> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Constants.CENTER_MAP_LATITUDE, Constants.CENTER_MAP_LONGITUDE), 4));

        mNetworkClient.getRestApi().getOrders(new Callback<List<Order>>() {
            @Override
            public void success(List<Order> orders, Response response) {
                Log.d(TAG, "get Orders succes");
                processGeoLines(orders);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });
    }

    private void processGeoLines(List<Order> orders){
        Bundle args = new Bundle();
        args.putParcelableArrayList(GeoLoader.ARG_ORDERS, (ArrayList) orders);
        getLoaderManager().initLoader(LOADER_GEO_ID, args, MapActivity.this).forceLoad();
    }

    private void showMarkers( List<GeoLine> data){
        for (GeoLine geoLine : data){
            if(geoLine.getSource() != null){
                Marker markerSource = mMap.addMarker(new MarkerOptions()
                        .title(geoLine.getSource().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .position(geoLine.getSource()));
            }
            if(geoLine.getDestination() != null){
                Marker markerDestination = mMap.addMarker(new MarkerOptions()
                        .title(geoLine.getDestination().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .position(geoLine.getDestination()));
            }
            if(geoLine.getSource() != null && geoLine.getDestination() != null) {
                PolylineOptions lineOptions = new PolylineOptions()
                        .add(geoLine.getSource(), geoLine.getDestination())
                        .width(2)
                        .color(Color.BLUE);
                Polyline polyline = mMap.addPolyline(lineOptions);
            }
        }
    }
}
