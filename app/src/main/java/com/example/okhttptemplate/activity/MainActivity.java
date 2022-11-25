package com.example.okhttptemplate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.okhttptemplate.R;
import com.example.okhttptemplate.databinding.ActivityMainBinding;
import com.example.okhttptemplate.vm.UserVM;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private UserVM mUserVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.helloWorldTv.setText(R.string.app_description);
        mBinding.setLifecycleOwner(this);

        mUserVM = ViewModelProviders.of(this).get(UserVM.class);

        mUserVM.userListModel().observe(this, userListModel -> {
            mBinding.dataTv.setText(userListModel.userName);
        });

        mBinding.callBtn.setOnClickListener(view -> {
            mUserVM.getUserListModel(this, "");
        });

    }
}