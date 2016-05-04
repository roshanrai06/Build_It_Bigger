package com.udacity.gradle.builditbigger.service;

import com.github.roshan.myapplication.backendjokes.myApi.model.MyBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;


public interface JokesService {
    //Put Remote base URL for to test n remote server
    String REMOTE_BASE_URL = "https://buildbigger-1300.appspot.com/_ah/api/";
    String LOCAL_BASE_URL = "http://localhost:8080/_ah/api/";
    String EMULATOR_BASE_URL = "http://10.0.2.2:8080/_ah/api/";

    @GET("myApi/v1/mybean")
    Call<MyBean> getJokes();

    class Factory {
        public static JokesService newJokesService() {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(JokesService.EMULATOR_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(JokesService.class);
        }
    }
}
