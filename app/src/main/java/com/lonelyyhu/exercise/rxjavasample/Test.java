package com.lonelyyhu.exercise.rxjavasample;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by lonelyyhu on 2018/3/5.
 */

public class Test {

    public static int countor = 0;

    public static void main(String[] args) {

        String[] ary = {"1", "2", "3", "4"};

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Observable.fromIterable(list)

//        values.take(5)
//        Observable.fromArray(ary)

                .subscribeOn(Schedulers.io())


                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        System.out.println(s);
                        return Observable.just("A-"+s);
                    }
                })


                .blockingForEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });


//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        System.out.println("onNext:"+s);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("onError");
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("onComplete");
//
//                    }
//                });

        System.out.println("finish");

    }

    public static void retryTest() {
        Observable.just("Hellow")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
//                        System.out.println(Thread.currentThread().getName()+" => run 1......");

                        Log.wtf("Test", Thread.currentThread().getName()+" => run 1......");


                        return Observable.just(s+"-1");

                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
//                        System.out.println(Thread.currentThread().getName()+" => run 2......");

                        Log.wtf("Test", Thread.currentThread().getName()+" => run 2......");


                        return Observable.just(s+"-2");
                    }
                })

//                .observeOn(AndroidSchedulers.mainThread())


                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String str) throws Exception {

//                        System.out.println(Thread.currentThread().getName()+" => run 3......");
                        Log.wtf("Test", Thread.currentThread().getName()+" => run 3......");


                        Observer<String> observer = new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.wtf("Test", "Exception onSubscribe => ");
                            }

                            @Override
                            public void onNext(String s) {
                                Log.wtf("Test", "Exception onNext => ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.wtf("Test", "Exception onError => ");
                            }

                            @Override
                            public void onComplete() {
                                Log.wtf("Test", "Exception onComplete => ");
                            }
                        };


//                        getObserver().retry(10).subscribe();

//                        getObserver().retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(final Observable<Throwable> throwableObservable) throws Exception {
////                                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
////                                    @Override
////                                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
////                                        return Observable.interval(5, TimeUnit.SECONDS);
////                                    }
////                                });
////                                return throwableObservable.delay(5, TimeUnit.SECONDS);
//
////                                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
////                                    @Override
////                                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
////
////                                        Thread.sleep(5000);
////
////                                        return throwableObservable;
////                                    }
////                                });
//
////                                return throwableObservable;
//                            }
//                        }).subscribe(observer);

//                        throw new RuntimeException("error!!!!!");

                        return Observable.just(str+"-3");


//                        return getObserver();


                    }
                })

//                .observeOn(Schedulers.io())
//                .retry(2)

                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {

//                        System.out.println(Thread.currentThread().getName()+" => run 4......");
                        Log.wtf("Test", Thread.currentThread().getName()+" => run 4......");

                        return Observable.just(s+"-4");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName()+":"+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
        });

    }

    public static Observable<String> getObserver() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

//                System.out.println(Thread.currentThread().getName()+" => run exception......");
                Log.wtf("Test", Thread.currentThread().getName()+" => run exception......countor:"+countor);

//                Thread.sleep(1000);

                countor++;

//                if (countor < 3) {
//                    throw new RuntimeException("throw exception!!");
//                }

            }
        });

    }

}
