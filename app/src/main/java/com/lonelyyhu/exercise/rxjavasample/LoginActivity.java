package com.lonelyyhu.exercise.rxjavasample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lonelyyhu.exercise.rxjavasample.data.RemoteService;
import com.lonelyyhu.exercise.rxjavasample.data.ServiceFactory;
import com.lonelyyhu.exercise.rxjavasample.model.SessionModel;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mCompositeDisposable.clear();
    }

    public void onLoginClick(View view) {

        EditText etUN = findViewById(R.id.et_username);
        EditText etPW = findViewById(R.id.et_password);

        String username = etUN.getText().toString();
        String password = etPW.getText().toString();

        RemoteService service = ServiceFactory.getInstance().createService(RemoteService.class);

        service.login(username, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SessionModel>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.disposable = d;

                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SessionModel sessionModel) {
                        MyApplication application = (MyApplication) getApplication();
                        application.setSessionModel(sessionModel);
                        Log.wtf("LoginActivity", "model.getSessionToken() =>" + sessionModel.getSessionToken());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf("LoginActivity", "onError =>"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.wtf("LoginActivity", "onComplete =>");
                    }
                });

    }


}
