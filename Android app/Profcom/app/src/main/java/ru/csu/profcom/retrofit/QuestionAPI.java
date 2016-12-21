package ru.csu.profcom.retrofit;

import android.media.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionAPI {
    @GET("/User/{UserID}/Question/")
    Call<List<Question>> getUserQuestions(@Path("UserID") Long UserID);
	
	@POST("/Question/")
	Call<Question> postQuestion(
		@Header("Content-Type") String contentType,
        @Body Question question
	);
	
	@GET("/User/findWhereAnswererNull/")
	Call<List<Question>> getNotAnsweredQuestions();
}
