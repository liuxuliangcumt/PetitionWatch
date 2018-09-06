package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.ChangePhoneParam;
import com.realpower.petitionwatch.net.param.CodeParam;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.TimeCount;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.PassWordEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;

//修改绑定手机号码
@EActivity
public class ResetPhoneActivity extends BaseActivity {
    @ViewById(R.id.btn_code)
    Button btn_code;
    private TimeCount timeCount;

    @ViewById(R.id.tv_phone)
    TextView tv_phone;

    @ViewById(R.id.et_password)
    PassWordEditText et_password;

    @ViewById
    AppCompatEditText et_phone;

    @ViewById
    ClearEditText et_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_phone);
    }

    @AfterViews
    void initViews() {
        setTitleName("修改绑定手机");
        timeCount = new TimeCount(this, 60000, btn_code);
        tv_phone.setText(myPrefs.username().get());
    }

    @Click({R.id.btn_code, R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                sendCode();
                break;
            case R.id.btn_ok:
                changPhone();
                break;
        }
    }

    private void changPhone() {
        String code = et_code.getText().toString();
        String psw = et_password.getText().toString();
        if (code.length() < 4) {
            MyToastUtils.showToast("请输入验证码");
            return;
        }
        if (psw.length() < 6) {
            MyToastUtils.showToast("请输入密码");
            return;
        }
        showMyDialog("请稍后");
        Call<StringResult> call = apiService.changePhone(new ChangePhoneParam(myPrefs.username().get(), code, psw, phone));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                    if ("6".equals(result.getDesc().getCode())) {
                        myPrefs.username().put(phone);
                        finish();
                    }
                }

            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }


    String phone;

    private void sendCode() {
        phone = et_phone.getText().toString();
        if (phone.length() != 11) {
            MyToastUtils.showToast("请输入11位手机号码");
            return;
        }
        if (!phone.startsWith("1")) {
            MyToastUtils.showToast("您输入的手机号格式不正确");
            return;
        }

        showMyDialog("正在发送验证码");
        Call<StringResult> call = apiService.getCPhoneCode(new CodeParam(phone));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    timeCount.start();
                    MyToastUtils.showToast("验证码已发送,请注意接收");
                } else {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }
                Log.e("aaa", "onSuccessRequest" + result.toString());
            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {
                Log.e("aaa", "onFailure   " + t.getMessage());
            }
        });

    }
}
