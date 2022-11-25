package com.example.okhttptemplate.vm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.okhttptemplate.network.BaseSubscriber;
import com.example.okhttptemplate.vo.UserListModel;

public class UserVM extends BaseVM {
    private final MutableLiveData<UserListModel> userListModel;

    public UserVM(@NonNull Application application) {
        super(application);
        userListModel = new MutableLiveData<>();
    }

    public void getUserListModel(Context context, String userId) {
        UserListModel.getData(userId, new BaseSubscriber<UserListModel>(mContext, true, true) {
            @Override
            public void onNext(UserListModel response) {
                if (response == null)
                    return;
                userListModel.setValue(response);
            }
        });
    }

    public LiveData<UserListModel> userListModel() {
        return userListModel;
    }

}
