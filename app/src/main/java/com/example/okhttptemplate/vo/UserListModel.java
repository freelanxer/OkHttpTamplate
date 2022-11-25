package com.example.okhttptemplate.vo;

import com.example.okhttptemplate.network.ApiHelper;
import com.example.okhttptemplate.network.ApiRequest;
import com.example.okhttptemplate.network.BaseSubscriber;

public class UserListModel {
    public String userId;
    public String userName;

    public static void getData(String userId, BaseSubscriber<UserListModel> baseSubscriber) {
        new ApiRequest<>(ApiHelper.getApiService().getUserList(userId), baseSubscriber);
    }
}
