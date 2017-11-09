package com.uca.isi.squaddev.dracarys.api;

/**
 * Created by ericb on 28/10/2017.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private final static String URL = "https://api-complaint.herokuapp.com/api";

    public static String getBase() {
        return URL + "/";
    }

    public static ApiInterface instance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

}