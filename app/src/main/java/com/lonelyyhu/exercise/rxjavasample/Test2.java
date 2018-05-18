package com.lonelyyhu.exercise.rxjavasample;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Test2 {

    static Disposable disposable;

    public static void main(String[] args) throws InterruptedException {


        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("1");

                Thread.sleep(3000);

                e.onNext("2");

                Thread.sleep(3000);

                e.onNext("3");

                e.onError(new RuntimeException());

                Thread.sleep(5000);

                e.onNext("4");

                e.onComplete();

            }
        });





        observable
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");

                        disposable = d;

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });





        Thread.sleep(9000);

        System.out.println("disposable1: " + disposable.isDisposed());
        disposable.dispose();
        System.out.println("disposable2: " + disposable.isDisposed());

        Thread.sleep(10000);

    }

}
