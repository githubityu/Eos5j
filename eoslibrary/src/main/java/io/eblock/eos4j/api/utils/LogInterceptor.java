package io.eblock.eos4j.api.utils;

import android.util.Log;
import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Okhttp日志拦截
 */
public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
//        request.newBuilder().addHeader()
        Log.e("Http", String.format("Sending request %s on %s%n%s  %s",
                request.url(), chain.connection(), request.headers(), request.body()));

        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e("Http", String.format(Locale.getDefault(), response.code() + "--Received response for %s in %.1fms%n%sResponse body: %s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers(), content));
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}


