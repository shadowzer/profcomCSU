package ru.csu.profcom.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsAPI {
    @GET("/News/")
    Call<List<News>> getAllNews();
}
