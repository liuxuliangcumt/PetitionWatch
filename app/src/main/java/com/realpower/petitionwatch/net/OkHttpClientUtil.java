package com.realpower.petitionwatch.net;

import android.util.Log;


import com.realpower.petitionwatch.BuildConfig;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by Administrator on 2017/6/16.
 */

public class OkHttpClientUtil {
    private static OkHttpClient okhttpclient;

    public static OkHttpClient getOkHttpClient(String token) {
       /* if (okhttpclient != null) {
            return okhttpclient;
        } else {*/
            if (BuildConfig.BUILD_TYPE == "debug") {
                okhttpclient = new OkHttpClient.Builder()
                        .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
                        // .hostnameVerifier(new SSLSocketFactoryUtils.TrustAllHostnameVerifier())
                        .addInterceptor(new LoggingInterceptor(token))
                        .build();
                return okhttpclient;
            } else {
                okhttpclient = new OkHttpClient.Builder()
                        .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
                        // .hostnameVerifier(new SSLSocketFactoryUtils.TrustAllHostnameVerifier())
                        .addInterceptor(new AddHeaderInterceptor(token))
                        .build();
                return okhttpclient;
            }

       // }
    }

    static class AddHeaderInterceptor implements Interceptor {
        public AddHeaderInterceptor(String token) {
            this.token = token;
        }

        private String token;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder1 = request.newBuilder();
            Request response = builder1.addHeader("Authorization", token).build();
            Log.e("aaa", "添加header  Authorization  " + token);
            return chain.proceed(response);
        }
    }

    static class LoggingInterceptor implements Interceptor {
        public LoggingInterceptor(String token) {
            Log.e("aaa", "LoggingInterceptor  " + token);
            this.token = token;
        }

        private static final Charset UTF8 = Charset.forName("UTF-8");
        private String token;

        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间

            Request.Builder builder1 = request.newBuilder();
            Request response1 = builder1.addHeader("Authorization", token).build();
            Log.e("aaa", "添加header  " + token);
            Response response = chain.proceed(response1);
            Buffer buffer = new Buffer();
            response1.body().writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = response1.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            Log.e("CSDN_LQR", " 发送请求 参数 " + buffer.readString(charset));

            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Log.e("CSDN_LQR", String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
            return response;
        }
    }
}
