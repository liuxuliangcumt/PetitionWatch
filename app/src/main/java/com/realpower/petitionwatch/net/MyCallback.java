package com.realpower.petitionwatch.net;

import com.realpower.petitionwatch.MyApplication;
import com.realpower.petitionwatch.net.result.BaseResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.tamic.novate.exception.ServerException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.FormatterClosedException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/22.
 */

public abstract class MyCallback<T> implements Callback<T> {
    private Response<T> response;


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.response = response;
        BaseResult result = (BaseResult) response.body();
        onSuccessRequest((T) result);
        afterRequest();
       /* if (result.getStatus() == 1000) {
            MyToastUtils.showToast("您的账户已过期，请重新登录");
            //myPrefs.confimTime().put(System.currentTimeMillis());
            LoginActivity_.intent(BesafeApplication.getInstance().getmContext()).start();
            return;
        } else if (result.getStatus() == 1) {
            onSuccessRequest((T) result);
            afterRequest();
        } else {
            MyToastUtils.showToast(result.getMsg());
        }*/
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailureRequest(call, t);
        afterRequest();
        try {
            NetError netError = getNetErrorType((Exception) t);
            MyToastUtils.showToast(netError.desc);
        } catch (Exception e) {

        }

    }

    public abstract void onSuccessRequest(T result);

    public abstract void afterRequest();

    public abstract void onFailureRequest(Call<T> call, Throwable t);

    public NetError getNetErrorType(Exception ex) {
        NetError error = NetError.UNDEFINED;
        if (ex instanceof SocketTimeoutException) {
            error = NetError.TIME_OUT;
        } else if (ex instanceof UnknownHostException) {
            // error = NetError.DNS_ERROR;
            error = NetError.UNDEFINED;
        } else if (ex instanceof ServerException || ex instanceof HttpException) {
            error = NetError.SERVER_ERROR;
        } else if (ex instanceof FormatterClosedException || ex instanceof NumberFormatException) {
            error = NetError.DATA_FORMAT_ERROR;
        } else if (!SystemInfoUtils.isNetworkAvailable(MyApplication.getInstance().getmContext())) {
            error = NetError.NETWORK_DISABLE;
        }
        return error;
    }

    public enum NetError {
        OK("请求成功"),
        UNDEFINED("未定义网络错误"),
        TIME_OUT("连接超时"),
        SERVER_ERROR("服务器错误"),
        DNS_ERROR("无法解析接口地址"),
        DATA_FORMAT_ERROR("数据格式错误"),
        NETWORK_DISABLE("请检查您的网络"),;
        public String desc;

        NetError(String desc) {
            this.desc = desc;
        }
    }
}
