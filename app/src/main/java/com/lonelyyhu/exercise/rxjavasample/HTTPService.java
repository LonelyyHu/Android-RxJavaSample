package com.lonelyyhu.exercise.rxjavasample;

import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by lonelyyhu on 2018/3/7.
 */

public class HTTPService {

    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor logging;


    public HTTPService() {
        logging= new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

    }

    public static String getMimeType(File file) {
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(file.toURI().toString());
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
    }

    public Observable<Response> put(final String url, final File file) {

        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(final ObservableEmitter<Response> emitter) throws Exception {
                String mimeType = getMimeType(file);

                Request request = new Request.Builder()
                        .url(url)
                        .put(RequestBody.create(MediaType.parse(mimeType), file))
                        .build();

                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.wtf("HTTPService", "onFailure => ", e);
                        emitter.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.wtf("HTTPService", "onResponse => " + response.message());
                        emitter.onNext(response);
                        emitter.onComplete();
                    }
                });
            }
        });

    }

}
