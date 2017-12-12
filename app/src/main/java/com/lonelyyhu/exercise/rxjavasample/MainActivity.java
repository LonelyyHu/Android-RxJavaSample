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

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    public void onClickUpdate(View view) {

        if (MyApplication.getSessionModel() != null) {

            SessionModel model = MyApplication.getSessionModel();
            RemoteService service = ServiceFactory.getInstance().createService(RemoteService.class);

            Map<String, Object> updateData = new HashMap<>();
            updateData.put("timezone", 1);

            service.updateUser(model.getObjectId(), updateData)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UpdateResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(UpdateResult updateResult) {
                            Log.wtf("MainActivity", "onResponse =>"+updateResult.getUpdatedAt());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.wtf("MainActivity", "onFailure =>"+e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.wtf("MainActivity", "onComplete");
                        }
                    });

        }



    }
}
