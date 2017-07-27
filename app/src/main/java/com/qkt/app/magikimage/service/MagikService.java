package com.qkt.app.magikimage.service;

import com.qkt.app.magikimage.model.MagikResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by qkt on 25/07/2017.
 */

public class MagikService {
    private static final String BASE_URL = "http://appserver.magik.vn/";
    private static final int VERSION = 0;
    private MakgikApi mApi;

    public MagikService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(MakgikApi.class);
    }

    public Call<MagikResponse> downloadWordList(){
        return mApi.getWordList(VERSION);
    }
}
