package ru.csu.profcom.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsAPI {
    @GET("/News/")
    Call<List<News>> getAllNews();

    @GET("/User/News/{UserID}/")
    Call<List<News>> getAllUserNews(@Path("UserID") Long UserID);
}
