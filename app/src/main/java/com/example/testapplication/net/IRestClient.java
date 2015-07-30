package com.example.testapplication.net;

import com.example.testapplication.model.Order;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by alex_himik on 17.02.15.
 */
public interface IRestClient {

    @GET("/orders")
    public void getOrders(Callback<List<Order>> ordersCallback);

}