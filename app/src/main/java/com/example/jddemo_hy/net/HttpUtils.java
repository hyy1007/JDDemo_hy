package com.example.jddemo_hy.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hyy
 * @date 2018/10/17
 */
public class HttpUtils {

    public final Api api;

    private HttpUtils() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(Api.class);
    }

    private static class GetHttpUtilsInstance {
        private static HttpUtils httpUtils = new HttpUtils();
    }

    /**
     * 拦截器
     */
    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            //logger.info(String.format("Sending request %s on %s%n%s",
            //request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            //logger.info(String.format("Received response for %s in %.1fms%n%s",
            //response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    public static HttpUtils getHttpUtilsInstace() {
        return GetHttpUtilsInstance.httpUtils;
    }
}
