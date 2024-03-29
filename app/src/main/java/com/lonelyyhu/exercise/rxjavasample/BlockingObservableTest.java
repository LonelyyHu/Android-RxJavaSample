package com.lonelyyhu.exercise.rxjavasample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lonelyyhu on 2018/3/21.
 */

public class BlockingObservableTest {

    public static boolean isComplete = false;

    public static String collectStr = "B-";

    public static void main(String[] args) {

        String[] ary = {"1", "2", "3", "4"};

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        final List<String> collectList = new ArrayList<>();

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

//                .collect(new Callable<List>() {
//                    @Override
//                    public List call() throws Exception {
//                        return collectList;
//                    }
//                }, new BiConsumer<List, String>() {
//
//                    @Override
//                    public void accept(List s, String s2) throws Exception {
//
//                        s.add(s2);
//
//                    }
//                })
//
//                .flatMap(new Function<List, SingleSource<String>>() {
//                    @Override
//                    public SingleSource<String> apply(List list) throws Exception {
//                        return new SingleSource<String>() {
//                            @Override
//                            public void subscribe(SingleObserver<? super String> observer) {
//
//                            }
//                        };
//                    }
//                })




                .reduce(new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s+s2;
                    }
                })
                .flatMapObservable(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just(s);
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {

                        System.out.println(s);

                        return Observable.just(s);
                    }
                })


//                .blockingForEach(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s);
//                    }
//                });

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext:"+s);

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                        isComplete = true;

                    }
                });

        System.out.println("finish");


        while (!isComplete) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

}
