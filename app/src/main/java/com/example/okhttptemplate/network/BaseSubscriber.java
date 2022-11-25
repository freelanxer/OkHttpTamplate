package com.example.okhttptemplate.network;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.okhttptemplate.utils.Constant;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;


public abstract class BaseSubscriber<T> implements Consumer<ApiResult<T>> {
    private final Context mContext;
    private final boolean mAutoDismissDialog;
    private final boolean mShowApiError;

    private static AlertDialog mAlertDialog;

    public abstract void onNext(T retObj);

    public BaseSubscriber(Context context) {
        this(context, false, true);
    }

    public BaseSubscriber(Context context, boolean autoDismissDialog) {
        this(context, autoDismissDialog, true);
    }

    public BaseSubscriber(Context context, boolean autoDismissDialog, boolean showApiError) {
        super();
        mContext = context;
        mAutoDismissDialog = autoDismissDialog;
        mShowApiError = showApiError;
    }

    @Override
    public void accept(ApiResult<T> tApiResult) {
        if (mAutoDismissDialog) {

        }

        if (tApiResult.error != null) {
            if (tApiResult.error instanceof ApiException) {
                if (mShowApiError) {
                    Toast.makeText(mContext, tApiResult.error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else if (tApiResult.error instanceof ConnectException) {
                Toast.makeText(mContext, "error_network_connect", Toast.LENGTH_SHORT).show();
            } else if (tApiResult.error instanceof UnknownHostException) {
                Toast.makeText(mContext, "error_network_unknown_host", Toast.LENGTH_SHORT).show();
            }
            else if (tApiResult.error instanceof HttpException && ((HttpException) tApiResult.error).code() == 401) {
                // code = 401 Timeout
                if (mAlertDialog != null && mAlertDialog.isShowing())
                    return;
            }
            else {
                Constant.logI("onError: " + tApiResult.error.getMessage());
                Toast.makeText(mContext, tApiResult.error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (tApiResult.success) {
                // Save token
                if (!TextUtils.isEmpty(tApiResult.token)) {
                    Constant.logD("Refresh jwtToken: " + tApiResult.token);
                }
                onNext(tApiResult.value);
            } else if (mShowApiError) {
                Toast.makeText(mContext, tApiResult.msg, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static void showAlertDialog(AlertDialog.Builder builder) {
        dismissDialog();
        try {
            mAlertDialog = builder.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void dismissDialog() {
        try {
            if (mAlertDialog != null && mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
                mAlertDialog = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
