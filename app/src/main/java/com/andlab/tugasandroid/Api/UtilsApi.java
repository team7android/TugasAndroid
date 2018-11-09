package com.andlab.tugasandroid.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by greatsoft on 09/11/18.
 */

public class UtilsApi {
    public static final String BASE_URL = "http://localhost/apiPendaftaranAkun/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(BaseApiService.class);
    }
}
