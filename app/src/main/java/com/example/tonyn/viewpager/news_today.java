package com.example.tonyn.viewpager;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tonyn on 2017/4/2.
 */

public interface news_today {
    @FormUrlEncoded
    @POST("toutiao/index")
    Call<News> getCall(@Field("key") String key, @Field("type") String type);
}
