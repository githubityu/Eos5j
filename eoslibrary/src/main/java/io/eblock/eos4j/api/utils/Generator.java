package io.eblock.eos4j.api.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import io.eblock.eos4j.api.exception.ApiError;
import io.eblock.eos4j.api.exception.ApiException;
import java.io.IOException;
import java.lang.annotation.Annotation;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.jackson.JacksonConverterFactory;

public class Generator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create());
    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        builder.baseUrl(baseUrl);
        setHttpConfig(httpClient);
        builder.client(httpClient.build());
        builder.addConverterFactory(JacksonConverterFactory.create());
        retrofit = builder.build();
        return (S) retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
              Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return (T) response.body();
            }
            ApiError apiError = getApiError(response);
            throw new ApiException(apiError);
        } catch (Exception e) {
            //捕获超时异常
            throw new ApiException(e);
        }
    }

    private static ApiError getApiError(Response<?> response) throws IOException, ApiException {
        return (ApiError) retrofit.responseBodyConverter(ApiError.class, new Annotation[0]).convert(response.errorBody());
    }

    //设置http配置信息
    public static void setHttpConfig(OkHttpClient.Builder builder) {
        //希望超时时能重连
        builder.connectTimeout(60, TimeUnit.SECONDS);//1.连接时间
        builder.readTimeout(120, TimeUnit.SECONDS);// 2.读取服务器资源的时间
        builder.writeTimeout(60, TimeUnit.SECONDS);//3.上传资源的时间
        //错误重连
        builder.retryOnConnectionFailure(true);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.e("Log", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        builder.addInterceptor(new LogInterceptor());
    }
}
