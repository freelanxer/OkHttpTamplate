package com.example.okhttptemplate.vm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseVM extends AndroidViewModel {
    protected final Context mContext;//To avoid leaks, this must be an Application Context.

    public BaseVM(@NonNull Application application) {
        super(application);
        mContext = application;
    }

}
