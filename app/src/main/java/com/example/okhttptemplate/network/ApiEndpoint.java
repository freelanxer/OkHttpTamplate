package com.example.okhttptemplate.network;

import io.reactivex.Observable;

import com.example.okhttptemplate.vo.UserListModel;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("App/UserList")
    Observable<ApiResult<UserListModel>> getUserList(@Query("UserId") String UserId);

}
