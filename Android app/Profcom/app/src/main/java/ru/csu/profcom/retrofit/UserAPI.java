package ru.csu.profcom.retrofit;

import android.media.Image;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {
    @GET("/User/autorizate/{login}/{password}")
    Call<Long> authorize(@Path("login") String login, @Path("password") String password);

    @POST("/User")
    Call<User> registerUser(
            @Header("Content-Type") String contentType,
            @Body User user
            /*@Field("login") String login,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("surName") String surName,
            @Field("budget") Boolean budget,
            @Field("fulltime") Boolean fulltime,
            @Field("feePay") Boolean feePay*/
            //@Field("group") String group
    );

    @GET("/User/{UserID}")
    Call<User> getUser(@Path("UserID") Long UserID);
}
