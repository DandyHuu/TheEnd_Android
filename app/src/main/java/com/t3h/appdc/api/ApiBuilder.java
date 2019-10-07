package com.t3h.appdc.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private static Retrofit retrofit;
    private static Api api;

    public static Retrofit getInstan(){
        if (retrofit == null) {
//            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.0.104/demo_pets/").build();
//            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.82.102:88/demo_pets/").build();
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.252.116.231:88/demo_pets/").build();
        }
        return retrofit;

    }

    public static Api getInstance(){
        if (api == null) {
//            api = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.0.104/demo_pets/").build().create(Api.class);
//            api = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.82.102:88/demo_pets/").build().create(Api.class);
            api = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.252.116.231:88/demo_pets/").build().create(Api.class);
        }
        return api;

    }

}
