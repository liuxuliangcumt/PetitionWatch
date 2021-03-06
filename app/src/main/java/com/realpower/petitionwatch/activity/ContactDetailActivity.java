package com.realpower.petitionwatch.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.ContactBean;
import com.realpower.petitionwatch.chatui.actvity.ChatActivity;
import com.realpower.petitionwatch.util.PermissionUtils;
import com.tencent.imsdk.TIMConversationType;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class ContactDetailActivity extends BaseActivity implements PermissionUtils.PermissionGrant {

    @Extra
    ContactBean bean;
    @ViewById
    TextView tv_name, tv_phoneNum, tv_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        setTitleName(bean.getSysUser().getNickname());
        tv_name.setText(bean.getSysUser().getNickname());
        tv_phoneNum.setText(bean.getSysUser().getMobilePhone());
        tv_post.setText(bean.getSysRole().getName());
    }

    @Click({R.id.tv_message, R.id.tv_tel})
    void inViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_message:
                ChatActivity.navToChat(this, bean.getTxAccount(), TIMConversationType.C2C);
                break;
            case R.id.tv_tel:
                makeTel();
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void makeTel() {
       // PermissionUtils.requestPermission(this, PermissionUtils.CODE_CALL_PHONE, this);
        final String phoneNumber = bean.getSysUser().getMobilePhone();
        AlertDialog dialog = new AlertDialog.Builder(this)

                .setTitle("打电话给")//设置对话框的标题
                .setMessage(phoneNumber + " ?")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                        dialog.dismiss();

                    }
                }).create();
        dialog.show();

    }

    @Override
    public void onPermissionGranted(int requestCode) {
        Log.e("aaa", "onPermissionGranted  " + requestCode);
        if (requestCode == PermissionUtils.CODE_CALL_PHONE) {

        }
    }
}