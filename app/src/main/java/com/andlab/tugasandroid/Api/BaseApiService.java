package com.andlab.tugasandroid.Api;

import android.renderscript.Sampler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
//
//import static com.android.volley.Request.Method.GET;
//import static com.android.volley.Request.Method.POST;

/**
 * Created by greatsoft on 09/11/18.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("passwd") String passwd);


    @GET("data_user")
    Call<Sampler.Value> view();
}
