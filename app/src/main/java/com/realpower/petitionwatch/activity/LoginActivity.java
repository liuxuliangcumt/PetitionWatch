package com.realpower.petitionwatch.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.realpower.petitionwatch.MyApplication;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.ContactBean;
import com.realpower.petitionwatch.chatui.model.FriendProfile;
import com.realpower.petitionwatch.chatui.model.FriendshipInfo;
import com.realpower.petitionwatch.keepalive.IntentWrapper;
import com.realpower.petitionwatch.modelcity.activity.CityMainActivity_;
import com.realpower.petitionwatch.modelcounty.activity.CountyMainActivity_;
import com.realpower.petitionwatch.modelstaff.activity.StaffMainActivity_;
import com.realpower.petitionwatch.net.ApiService;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.LoginParam;
import com.realpower.petitionwatch.net.result.ContactResult;
import com.realpower.petitionwatch.net.result.LoginResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SharedPreferencesHelper;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.PassWordEditText;
import com.realpower.petitionwatch.modelwatch.activity.WatchMainActivity_;
import com.tamic.novate.exception.ServerException;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushToken;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EActivity
public class LoginActivity extends BaseActivity {
    @ViewById(R.id.et_account)
    ClearEditText et_account;
    @ViewById(R.id.et_password)
    PassWordEditText et_password;

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Mate.API)
            .addConverterFactory(GsonConverterFactory.create())
            // .client(OkHttpClientUtil.getOkHttpClient("token"))
            .build();
    public static ApiService apiService = retrofit.create(ApiService.class);
    private String useSig = "eJxlj1FPgzAUhd-5FYRn42gHTEx8KBMnxi3KJhpfGqQFr0hpaNkgxv**DZeI8fn7zj33fBmmaVqb*-V5mmV1KzTVveSWeWlatnX2C6UERlNNpw37B3knoeE0zTVvBohc18W2PXaAcaEhh5OhudIjqlhJh4qfuHPI*mj654CCYoDL8GkekTux9KWavMIqzthHdNvGdVy*Ve1Kp7BIZEUecC1nghTvBELyOVn0SR-ldeSsk8DrnJfguijdtrBvpBds58-dzvfCjYyqx6tRpYaKn-ZgD3uzC*SM6JY3CmoxCNhGLsKHh4*jjW9jDx6-XX4_";
    private String useSig2 = "eJxFkN1OwkAQRt*ltxjZHzaCCRctoWAULVCs5WZT2G27FkrZjtVifHfWDejtOZmZ75tvJ3xa3iZVpQRPgFMtnHsHOTcWy69KacmTFKQ2GDPGCEJX20hdq0NpBEGYYUIR*pdKyBJUquygWR-KGi6qVplhs-F89DAJ9Dr2o8e828vcZg006mzIrInFIlhNPbjz0tE0OLpCP*eu8grsHyfsPYx3c7eI3tr2dRHrfhPmn6fdi7*HbqccsKgNcsiGw*sxUXDb7jd-z*QbYPoXEtRe2l4UMYJxn114st0ePkrg0FbSvuPnDKP2WPY_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_account.setText(myPrefs.username().get());
       et_password.setText(myPrefs.password().get());
        if (myPrefs.isShowAlive().get().length() <= 1) {
            IntentWrapper.whiteListMatters(this, "及时收到推送消息");
            myPrefs.isShowAlive().put("add");
        }
        FriendshipInfo.getInstance();
    }

    private int isXian = 0;

    @Click({R.id.btn_login, R.id.tv_password, R.id.btn_register})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //  WatchMainActivity_.intent(this).start();
                login();
                break;
            case R.id.tv_password:
                ResetPasswordActivity_.intent(this).start();
                break;
            case R.id.btn_register:
                et_password.setText("123456");
                switch (isXian % 4) {
                    case 0:
                        et_account.setText("18632090463");
                        MyToastUtils.showToast("县领导");
                        break;
                    case 1:
                        et_account.setText("13230959185");
                        MyToastUtils.showToast("工作人员");
                        break;
                    case 2:
                        et_account.setText("13803198020");
                        MyToastUtils.showToast("监控工作人员");
                        break;
                    case 3:
                        MyToastUtils.showToast("市领导");
                        et_account.setText("13383398730");
                        break;
                }
                isXian++;

                break;
        }
    }

    private void login() {
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        if (account.length() != 11) {
            MyToastUtils.showToast("请输入11位手机号");
            return;
        }
        if (password.length() < 6) {
            MyToastUtils.showToast("请输入密码");
            return;
        }
        showMyDialog("正在登录");
        Call<LoginResult> call = apiService.login(new LoginParam(account, password));
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                hideDialog();
                Log.e("aaa", "登录成功  " + response.body().toString());
                if (200 == response.body().getCode()) {
                    myPrefs.token().put("token " + response.body().getToken());
                    myPrefs.password().put(et_password.getText().toString());
                    myPrefs.username().put(et_account.getText().toString());
                    SharedPreferencesHelper.getInstance().saveData("token", "token " + response.body().getToken());
                    SharedPreferencesHelper.getInstance().saveData("username", et_account.getText().toString());
                    MyToastUtils.showToast("登录成功");
                    addClient(response.body().getToken());

                    Log.e("aaa", response.body().getRole() + "  登录角色");
                    switch (response.body().getRole()) {
                        case 1://市
                            CityMainActivity_.intent(LoginActivity.this).start();
                            break;
                        case 2://县
                            // CityMainActivity_.intent(LoginActivity.this).start();

                            CountyMainActivity_.intent(LoginActivity.this).start();
                            break;
                        case 3://工作
                            StaffMainActivity_.intent(LoginActivity.this).start();
                            break;
                        case 4://监控
                            WatchMainActivity_.intent(LoginActivity.this).start();
                            break;
                    }
                    getContact();
                    loginTenXun(response.body().getAccount(), response.body().getUserSig());
                } else if (403 == response.body().getCode()) {
                    MyToastUtils.showToast("密码错误");
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
                registerPush();
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

    public void registerPush() {
        final TIMOfflinePushToken param = new TIMOfflinePushToken(0, "");
        String vendor = Build.MANUFACTURER;
        if (vendor.toLowerCase(Locale.ENGLISH).contains("xiaomi")) {
            param.setToken((String) SharedPreferencesHelper.getInstance().getData(Mate.MI_REGID, ""));
            param.setBussid(Mate.TIM_MI_ID);
        } else if (vendor.toLowerCase(Locale.ENGLISH).contains("huawei")) {
            //请求华为推送设备 token
           /* param.setToken(token);
            param.setBussid(bussId);*/
        }

        TIMManager.getInstance().setOfflinePushToken(param, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e("aaamiPush", "setOfflinePushToken  onError　　" + s);

            }

            @Override
            public void onSuccess() {
                Log.e("aaamiPush", "setOfflinePushToken  onSuccess　　" + (String) SharedPreferencesHelper.getInstance().getData(Mate.MI_REGID, ""));

            }
        });
    }

    private long mExitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            MyToastUtils.showToast("再按一次退出应用");
            mExitTime = System.currentTimeMillis();
            return;
        } else if ((System.currentTimeMillis() - mExitTime) < 2000) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果是服务里调用，必须加入new task标识
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return;
        }
        super.onBackPressed();
    }
}
