package com.example.testapplication.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.example.testapplication.model.GeoLine;
import com.example.testapplication.model.Order;
import com.example.testapplication.utils.GeoUtils;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apelipets on 7/29/15.
 */
public class GeoLoader extends AsyncTaskLoader<List<GeoLine>> {

    public static  final String TAG = "GeoLoader";

    public final static String ARG_ORDERS = "orders";

    private ArrayList orders;
    private Geocoder geocoder;

    public GeoLoader(Context context, Bundle args) {
        super(context);
        geocoder = new Geocoder(context);
        orders = args.getParcelableArrayList(ARG_ORDERS);
    }

    @Override
    public List<GeoLine> loadInBackground() {
        Log.d(TAG, "loadInBackground enter");
        List<GeoLine> reuslt = new ArrayList<>();
        GeoLine geoline = null;
        Order order = null;
        for(Object object: orders) {
            order = (Order)object;
            geoline = new GeoLine();
            LatLng source = GeoUtils.getLocationFromAddress(geocoder, order.getDepartureAddress().toString());
            LatLng dest = GeoUtils.getLocationFromAddress(geocoder, order.getDestinationAddress().toString());
            geoline.setSource(source);
            geoline.setDestination(dest);
            reuslt.add(geoline);
        }
        return reuslt;
    }
}
