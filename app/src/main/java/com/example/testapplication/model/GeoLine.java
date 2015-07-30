package com.example.testapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by apelipets on 7/29/15.
 */
public class GeoLine implements Parcelable {
    private LatLng source;
    private LatLng destination;

    public GeoLine(){ }

    public LatLng getSource() {
        return source;
    }

    public void setSource(LatLng source) {
        this.source = source;
    }

    public LatLng getDestination() {
        return destination;
    }

    public void setDestination(LatLng destination) {
        this.destination = destination;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(source,flags);
        parcel.writeParcelable(destination,flags);
    }

    public static final Parcelable.Creator<GeoLine> CREATOR = new Parcelable.Creator<GeoLine>() {

        public GeoLine createFromParcel(Parcel in) {
            return new GeoLine(in);
        }

        public GeoLine[] newArray(int size) {
            return new GeoLine[size];
        }
    };

    private GeoLine(Parcel parcel) {
        source = parcel.readParcelable(null);
        destination = parcel.readParcelable(null);
    }
}

