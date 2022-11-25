package com.example.okhttptemplate.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.okhttptemplate.R;
import com.example.okhttptemplate.adapter.UserListAdapter;
import com.example.okhttptemplate.databinding.ActivityGetUserListBinding;
import com.example.okhttptemplate.vm.UserVM;
import com.example.okhttptemplate.vo.UserListModel;

import java.util.List;

public class GetUserListActivity extends BaseActivity {
    private ActivityGetUserListBinding mBinding;
    private UserVM mUserVM;
    private UserListAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_get_user_list);
        mBinding.setLifecycleOwner(this);

        mUserVM = ViewModelProviders.of(this).get(UserVM.class);

        mUserVM.userListModel().observe(this, userListModel -> {
            List<UserListModel.User> userList = userListModel.userList;
            mUserAdapter.setData(userList);
        });

        mUserAdapter = new UserListAdapter(this, user -> {
            showToast(user.userName);
        });
        mBinding.userListRv.setHasFixedSize(true);
        mBinding.userListRv.setLayoutManager(new LinearLayoutManager(this));
        mBinding.userListRv.setAdapter(mUserAdapter);

        mBinding.callBtn.setOnClickListener(view -> {
            mUserVM.getUserListModel(this, "");
        });

    }
}