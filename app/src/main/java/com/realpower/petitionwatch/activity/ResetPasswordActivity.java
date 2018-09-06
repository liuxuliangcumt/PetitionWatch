package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.CodeParam;
import com.realpower.petitionwatch.net.param.ResetPassworParam;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.TimeCount;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.PassWordEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;

//忘记密码
@EActivity
public class ResetPasswordActivity extends BaseActivity {
    @ViewById(R.id.btn_code)
    Button btn_code;
    private TimeCount timeCount;
    @ViewById(R.id.et_phone)
    AppCompatEditText et_phone;
    @ViewById(R.id.et_passwordRp)
    PassWordEditText et_passwordRp;
    @ViewById(R.id.et_code)
    ClearEditText et_code;
    @ViewById(R.id.et_password)
    PassWordEditText et_password;
    @Extra
    boolean isLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    @AfterViews
    void initViews() {
        if (isLogin) {
            setTitleName("重置密码");
            et_phone.setFocusable(false);
            et_phone.setFocusableInTouchMode(false);
            et_phone.setText(myPrefs.username().get());
        } else {
            setTitleName("忘记密码");
            et_phone.setHint("请输入您的手机号");
        }
        timeCount = new TimeCount(this, 60000, btn_code);
    }

    @Click({R.id.btn_code, R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                sendCode();
                break;
            case R.id.btn_ok:
                resetPassword();
                break;
        }
    }

    private void resetPassword() {
        phone = et_phone.getText().toString();
        String password = et_password.getText().toString();
        String pswRp = et_passwordRp.getText().toString();
        String code = et_code.getText().toString();
        if (code.length() < 4) {
            MyToastUtils.showToast("请输入验证码");
            return;
        }
        if (password.length() < 6 || pswRp.length() < 6) {
            MyToastUtils.showToast("请输入完整密码");
            return;
        }
        if (!password.equals(pswRp)) {
            MyToastUtils.showToast("您两次输入的密码不相同");
            return;
        }

        showMyDialog("请稍后");
        Call<StringResult> call = apiService.resetPassword(new ResetPassworParam(code, phone, password));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                Log.e("aaa", "修改密码  " + result.toString());
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                    if ("5".equals(result.getDesc().getCode())) {
                        btn_code.setClickable(false);
                        LoginActivity_.intent(ResetPasswordActivity.this).start();
                        finish();
                    }
                } else {
                    MyToastUtils.showToast("修改失败");

                }

            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {
                MyToastUtils.showToast("网络或服务器错误");
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
        Call<StringResult> call = apiService.getResetPswCode(new CodeParam(phone));
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
