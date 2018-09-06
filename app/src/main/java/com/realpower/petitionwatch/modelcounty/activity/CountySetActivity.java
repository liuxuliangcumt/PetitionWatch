package com.realpower.petitionwatch.modelcounty.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.activity.LoginActivity_;
import com.realpower.petitionwatch.activity.ResetPasswordActivity_;
import com.realpower.petitionwatch.activity.ResetPhoneActivity_;
import com.realpower.petitionwatch.modelwatch.activity.TaskSearchActivity_;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.CustomDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity
public class CountySetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_county_set);
    }

    @AfterViews
    void onInitView() {
        setTitleName("设置");
    }

    @Click({R.id.ll_phone, R.id.ll_update, R.id.ll_password, R.id.ll_search,R.id.btn_out})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                TaskSearchActivity_.intent(this).start();
                break;
            case R.id.ll_phone:
                ResetPhoneActivity_.intent(this).start();
                break;
            case R.id.ll_update:
                showUpdateDailog();
                break;
            case R.id.ll_password:
                ResetPasswordActivity_.intent(this).isLogin(true).start();
                break;
            case R.id.btn_out:
                LoginActivity_.intent(this).start();
                finish();
                break;
        }
    }

    private void showUpdateDailog() {
        CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_version_update);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.80);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

    }
}
