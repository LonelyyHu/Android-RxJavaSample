package com.lonelyyhu.exercise.rxjavasample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lonelyyhu.exercise.rxjavasample.data.RemoteService;
import com.lonelyyhu.exercise.rxjavasample.data.ServiceFactory;
import com.lonelyyhu.exercise.rxjavasample.model.SessionModel;
import com.lonelyyhu.exercise.rxjavasample.model.UpdateResult;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickUpdate(View view) {

        if (MyApplication.getSessionModel() != null) {

            SessionModel model = MyApplication.getSessionModel();
            RemoteService service = ServiceFactory.getInstance().createService(RemoteService.class);

            Map<String, Object> updateData = new HashMap<>();
            updateData.put("timezone", 1);

            Call<UpdateResult> call = service.updateUser(model.getObjectId(), updateData);

            call.enqueue(new Callback<UpdateResult>() {
                @Override
                public void onResponse(Call<UpdateResult> call, Response<UpdateResult> response) {

                    if (response.isSuccessful()) {
                        UpdateResult result = response.body();
                        Log.wtf("MainActivity", "onResponse =>"+result.getUpdatedAt());
                    } else {
                        Log.wtf("MainActivity", "onResponse fail");
                    }


                }

                @Override
                public void onFailure(Call<UpdateResult> call, Throwable t) {
                    Log.wtf("MainActivity", "onFailure =>"+t.getMessage());
                }
            });

        }



    }
}
