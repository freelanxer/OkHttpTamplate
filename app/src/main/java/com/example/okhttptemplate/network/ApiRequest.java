package com.example.okhttptemplate.network;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ApiRequest<T> {
    public ApiRequest(Observable<ApiResult<T>> observable, Consumer<? super ApiResult<T>> consumer) {
        Disposable result = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(error -> {
                    ApiResult<T> retValue = new ApiResult<T>();
                    retValue.error = error;
                    return retValue;
                })
                .subscribe(consumer);
    }
}
