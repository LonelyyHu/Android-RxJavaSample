package com.lonelyyhu.exercise.rxjavasample;

import android.app.Application;

import com.lonelyyhu.exercise.rxjavasample.model.SessionModel;

/**
 * Created by hulonelyy on 2017/12/12.
 */

public class MyApplication extends Application {

    private static SessionModel sessionModel;

    public void setSessionModel(SessionModel model) {
        this.sessionModel = model;
    }

    public static SessionModel getSessionModel() {
        return sessionModel;
    }

}
