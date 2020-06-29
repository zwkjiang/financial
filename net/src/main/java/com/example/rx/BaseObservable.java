package com.example.rx;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseObservable{
    public static <T> void doObservable(Observable<T> tObservable,BaseObserver<T> observer){
        tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
