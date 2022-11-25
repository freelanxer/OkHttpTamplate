package com.example.okhttptemplate.activity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.okhttptemplate.R;
import com.example.okhttptemplate.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.titleTv.setText(R.string.app_description);
        mBinding.setLifecycleOwner(this);

        mBinding.getUserListBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, GetUserListActivity.class);
            startActivity(intent);
        });

    }
}