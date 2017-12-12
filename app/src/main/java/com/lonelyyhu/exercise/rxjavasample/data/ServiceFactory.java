package com.lonelyyhu.exercise.rxjavasample.data;


import com.google.gson.Gson;
import com.lonelyyhu.exercise.rxjavasample.MyApplication;
import com.lonelyyhu.exercise.rxjavasample.model.SessionModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hulonelyy on 2017/12/12.
 */

public class ServiceFactory {

    private Retrofit retrofit;
    Gson gson = new Gson();

    private static class LazyHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    private ServiceFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://watch-master-staging.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getHttpClient())
                .build();
    }

    public static ServiceFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public <T> T createService(Class<T> cls) {
        return retrofit.create(cls);
    }


    private OkHttpClient getHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new AppKeyInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

    }

    private class AppKeyInterceptor implements Interceptor{


        @Override
        public Response intercept(Chain chain) throws IOException {

            Request originRequest = chain.request();

            Request.Builder builder = originRequest.newBuilder();

            builder.addHeader("Content-Type", "application/json");
            builder.addHeader("X-Parse-Application-Id", "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD");
            builder.addHeader("X-Parse-REST-API-Key", "");
            builder.addHeader("X-Parse-Revocable-Session", "1");

            if (MyApplication.getSessionModel() != null) {
                SessionModel sessionModel = MyApplication.getSessionModel();

                builder.addHeader("X-Parse-Session-Token", sessionModel.getSessionToken());
            }

            builder.method(originRequest.method(), originRequest.body());

            Request newReq = builder.build();

            return chain.proceed(newReq);
        }


    }





}
