package com.lonelyyhu.exercise.rxjavasample.data;

import com.lonelyyhu.exercise.rxjavasample.model.SessionModel;
import com.lonelyyhu.exercise.rxjavasample.model.UpdateResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hulonelyy on 2017/12/12.
 */

public interface RemoteService {

//    @GET("login")
//    Call<SessionModel> login(@Query("username") String username, @Query("password") String password);

    @GET("login")
    Observable<SessionModel> login(@Query("username") String username, @Query("password") String password);


    @PUT("users/{objectId}")
    Call<UpdateResult> updateUser(@Path("objectId") String objectId, @Body Map model);

}
