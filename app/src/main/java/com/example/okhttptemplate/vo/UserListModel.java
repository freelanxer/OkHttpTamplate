package com.example.okhttptemplate.vo;

import com.example.okhttptemplate.network.ApiHelper;
import com.example.okhttptemplate.network.ApiRequest;
import com.example.okhttptemplate.network.BaseSubscriber;

import java.util.List;

public class UserListModel {
    public List<User> userList;

    public static class User {
        public String userId;
        public String userName;
    }

    public static void getData(String userId, BaseSubscriber<UserListModel> baseSubscriber) {
        new ApiRequest<>(ApiHelper.getApiService().getUserList(userId), baseSubscriber);
    }

}
