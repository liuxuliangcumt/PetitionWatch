package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.util.Log;

import com.realpower.petitionwatch.MyApplication;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.ContactBean;
import com.realpower.petitionwatch.chatui.model.FriendProfile;
import com.realpower.petitionwatch.chatui.model.FriendshipInfo;
import com.realpower.petitionwatch.modelcity.activity.CityMainActivity_;
import com.realpower.petitionwatch.modelcounty.activity.CountyMainActivity_;
import com.realpower.petitionwatch.modelstaff.activity.StaffMainActivity_;
import com.realpower.petitionwatch.modelwatch.activity.WatchMainActivity_;
import com.realpower.petitionwatch.net.ApiService;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.LoginParam;
import com.realpower.petitionwatch.net.result.ContactResult;
import com.realpower.petitionwatch.net.result.LoginResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SharedPreferencesHelper;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.tamic.novate.exception.ServerException;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import org.androidannotations.annotations.EActivity;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelcomeActivity extends BaseActivity {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Mate.API)
            .addConverterFactory(GsonConverterFactory.create())
            // .client(OkHttpClientUtil.getOkHttpClient("token"))
            .build();
    public static ApiService apiService = retrofit.create(ApiService.class);
    private String useSig = "eJxlj1FPgzAUhd-5FYRn42gHTEx8KBMnxi3KJhpfGqQFr0hpaNkgxv**DZeI8fn7zj33fBmmaVqb*-V5mmV1KzTVveSWeWlatnX2C6UERlNNpw37B3knoeE0zTVvBohc18W2PXaAcaEhh5OhudIjqlhJh4qfuHPI*mj654CCYoDL8GkekTux9KWavMIqzthHdNvGdVy*Ve1Kp7BIZEUecC1nghTvBELyOVn0SR-ldeSsk8DrnJfguijdtrBvpBds58-dzvfCjYyqx6tRpYaKn-ZgD3uzC*SM6JY3CmoxCNhGLsKHh4*jjW9jDx6-XX4_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        login();
    }

    void login() {
        final String account = myPrefs.username().get();
        String password = myPrefs.password().get();
        if (account.length() != 11) {
            LoginActivity_.intent(this).start();
            return;
        }
        if (password.length() < 6) {
            LoginActivity_.intent(this).start();
            return;
        }
//        showMyDialog("正在登录");
        Call<LoginResult> call = apiService.login(new LoginParam(account, password));
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                hideDialog();
                if (200 == response.body().getCode()) {
                    myPrefs.token().put("token " + response.body().getToken());
                    Log.e("aaa", "登录成功  " + response.body().toString());
                    SharedPreferencesHelper.getInstance().saveData("token", "token " + response.body().getToken());
                    SharedPreferencesHelper.getInstance().saveData("username", account);
                    MyToastUtils.showToast("登录成功");
                    addClient(response.body().getToken());

                    Log.e("aaa", response.body().getRole() + "  登录角色");
                    switch (response.body().getRole()) {
                        case 1://市
                            CityMainActivity_.intent(WelcomeActivity.this).start();
                            break;
                        case 2://县
                            // CityMainActivity_.intent(LoginActivity.this).start();

                            CountyMainActivity_.intent(WelcomeActivity.this).start();
                            break;
                        case 3://工作
                            StaffMainActivity_.intent(WelcomeActivity.this).start();
                            break;
                        case 4://监控
                            WatchMainActivity_.intent(WelcomeActivity.this).start();
                            break;
                        default:
                            LoginActivity_.intent(WelcomeActivity.this).start();
                            break;

                    }
                    getContact();
                    loginTenXun(response.body().getAccount(), response.body().getUserSig());


                } else if (403 == response.body().getCode()) {
                    MyToastUtils.showToast("密码错误");
                    LoginActivity_.intent(WelcomeActivity.this).start();

                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e("aaa", "登录   " + t.getMessage().toString());
                Exception ex = (Exception) t;
                MyCallback.NetError error = MyCallback.NetError.UNDEFINED;
                if (ex instanceof SocketTimeoutException) {
                    error = MyCallback.NetError.TIME_OUT;
                } else if (ex instanceof UnknownHostException) {
                    // error = NetError.DNS_ERROR;
                    error = MyCallback.NetError.UNDEFINED;
                } else if (ex instanceof ServerException || ex instanceof HttpException) {
                    error = MyCallback.NetError.SERVER_ERROR;
                } else if (ex instanceof FormatterClosedException || ex instanceof NumberFormatException) {
                    error = MyCallback.NetError.DATA_FORMAT_ERROR;
                } else if (!SystemInfoUtils.isNetworkAvailable(MyApplication.getInstance().getmContext())) {
                    error = MyCallback.NetError.NETWORK_DISABLE;
                }
                MyToastUtils.showToast(error.desc);
                hideDialog();
                LoginActivity_.intent(WelcomeActivity.this).start();
            }
        });
    }

    private void loginTenXun(String accont, String useSig) {
        TIMManager.getInstance().login(accont, useSig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e("aaa", "login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.e("aaa", "login succ");
                // registerPush();
            }
        });
    }

    private void getContact() {
        Call<ContactResult> call = BaseActivity.apiService.allContacts();
        call.enqueue(new MyCallback<ContactResult>() {
            @Override
            public void onSuccessRequest(ContactResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<ContactResult> call, Throwable t) {

            }
        });
    }

    private void setData(List<ContactBean> message) {

        Map<String, List<FriendProfile>> friends = new HashMap<>();
        for (ContactBean bean : message) {
            FriendProfile profile = new FriendProfile(bean.getSysUser().getNickname(), bean.getTxAccount());
            List<FriendProfile> profileList = new ArrayList<>();
            profileList.add(profile);
            friends.put(bean.getTxAccount(), profileList);
        }

        FriendshipInfo.getInstance().setFriends(friends);
    }
}
