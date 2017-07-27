package com.qkt.app.magikimage.service;

import com.qkt.app.magikimage.model.MagikResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by qkt on 24/07/2017.
 */

public interface MakgikApi {
    @GET("swipeenglish/get_changes")
    Call<MagikResponse> getWordList(@Query("version") int version);
}
