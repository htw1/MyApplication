package com.example.htw.myapplication.DataStorage;

import com.example.htw.myapplication.Model.ModelHero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceHero {

    String BASE_URL = "https://simplifiedcoding.net/demos/";


    @GET("marvel")
    Call<List<ModelHero>> getHeroes ();

}
