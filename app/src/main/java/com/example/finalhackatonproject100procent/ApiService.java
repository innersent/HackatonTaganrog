package com.example.finalhackatonproject100procent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("JakeyStorm/HackatonDB/main/NewsDB.json")
    Call<List<NewsView>> getNews();
}

