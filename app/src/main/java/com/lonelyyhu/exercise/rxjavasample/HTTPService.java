package com.lonelyyhu.exercise.rxjavasample;

import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.IOException;

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

    public static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/png");

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    public static void main(String[] args) {

        String url = "";

        File file = new File("test3.jpg");

        if (file.exists()) {
            System.out.println("exist!!");
//            put(url, file);
            System.out.println(file.toURI().toString());
            System.out.println("mimeType:"+getMimeType(file));

        } else {
            System.out.println("ohoh!!");
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }


    }

    public static String getMimeType(File file) {
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(file.toURI().toString());
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
    }

    public static void put(String url, File file) {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging).build();

        String mimeType = getMimeType(file);

        Request request = new Request.Builder().url(url)
                .put(RequestBody.create(MediaType.parse(mimeType), file))
                .build();


        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
