package com.example.testapplication.model;

import android.text.TextUtils;

/**
 * Created by apelipets on 7/29/15.
 */
public class Order {
    private String uuid; // "cdd72bb1-171c-11e5-b83b-8e17ec233556"
    private String number; // "000001851"
    private Address departureAddress;
    private Address destinationAddress;
    private String good; //""
    private int actualWeight; // 0
    private long appointmentFrom; //1435708800000
    private String note1; //""
    private int initialPrice; // 0
    private String status; //"DONE"

    public static class Address{
        public String country; //"D",
        public String zipCode; //"10115",
        public String city; //"Berlin",
        public String countryCode; //"DEU",
        public String street; //"",
        public String houseNumber; //""

        @Override
        public String toString() {
            String result = String.format("%s, %s",city, country);
            if (!TextUtils.isEmpty(street)){
                result = street  +", " +result;
            }
            if(!TextUtils.isEmpty(houseNumber)){
                result = houseNumber  +" " +result;
            }
            return result;
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(Address departureAddress) {
        this.departureAddress = departureAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public int getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(int actualWeight) {
        this.actualWeight = actualWeight;
    }

    public long getAppointmentFrom() {
        return appointmentFrom;
    }

    public void setAppointmentFrom(long appointmentFrom) {
        this.appointmentFrom = appointmentFrom;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
