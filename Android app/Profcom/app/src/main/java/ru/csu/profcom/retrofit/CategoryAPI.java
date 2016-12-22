package ru.csu.profcom.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryAPI {
    @GET("/Category/")
    Call<List<Category>>  getCategoriesList();

    @GET("/User/Category/{UserID}")
    Call<List<Category>> getUserCategoriesList(@Path("UserID") Long UserID);

    @POST("/UserCategory/")
    Call<UserCategory> postUserCategoryRecord(
            @Header("Content-Type") String contentType,
            @Body UserCategory userCategory
    );

    @DELETE("/UserCategory/{UserID}/{CategoryID}/")
    Call<Void> removeCategoryFromUser(@Path("UserID") Long UserID, @Path("CategoryID") Integer CategoryID);
}
