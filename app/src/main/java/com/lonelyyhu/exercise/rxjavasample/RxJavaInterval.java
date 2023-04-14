package com.lonelyyhu.exercise.rxjavasample;

import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxJavaInterval {

    static Disposable disposable = null;

    public static void main(String[] args) {

        Observable<Long> mObservable = Observable.interval(3, TimeUnit.SECONDS);

        mObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                System.out.println("onSubscribe " + Calendar.getInstance().getTimeInMillis());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext " + Calendar.getInstance().getTimeInMillis());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError " + Calendar.getInstance().getTimeInMillis());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete " + Calendar.getInstance().getTimeInMillis());
            }
        });

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable.dispose();
        System.out.println("disposed...");

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
