package com.example.okhttptemplate.network;

import android.content.Context;
import android.text.TextUtils;

import com.example.okhttptemplate.R;
import com.example.okhttptemplate.utils.Constant;
import com.example.okhttptemplate.utils.GsonHelper;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static String apiUrl;
    private final ApiEndpoint apiService;
    private static ApiHelper self;
    private static Context mContext;

    public static void init(Context context) {
        String apiServiceUrl = context.getString(R.string.api_service_url);
        ApiHelper.apiUrl = apiServiceUrl.endsWith("/") ? apiServiceUrl : apiServiceUrl + "/";
        mContext = context.getApplicationContext();
    }

    private ApiHelper() throws Exception{
        if (mContext == null) {
            throw new Exception("Must init first.");
        }

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        if (Constant.DEBUG) {
            logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .addInterceptor(chain -> {
                    //設定 Header
                    Request.Builder builder = chain.request().newBuilder();
                    List<String> segments = chain.request().url().encodedPathSegments();
                    String lastSegments = segments.get(segments.size() - 1);
                    Hashtable<String, String> headers = getRequestHeader(mContext, lastSegments);
                    Enumeration<String> keys = headers.keys();
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        String value = headers.get(key);
                        builder.addHeader(key, value);
                    }

                    return chain.proceed(builder.build());
                })
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        apiService = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiEndpoint.class);
    }

    public static ApiEndpoint getApiService() {
        if (self == null) {
            try {
                self = new ApiHelper();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return self.apiService;
    }

    public static Hashtable<String, String> getRequestHeader(Context context, String action) {
        Hashtable<String, String> headers = new Hashtable<>();
        String auth = getAuth(context);
        if (TextUtils.isEmpty(auth))
            return headers;
        headers.put("Authorization", auth);
        if (Constant.DEBUG)
            Constant.logD("Authorization: " + auth);
        return headers;
    }

    public static String getAuth(Context context) {
        String token = "";
        // TODO: Get token
        return TextUtils.concat("Bearer ", token).toString();
    }
}
