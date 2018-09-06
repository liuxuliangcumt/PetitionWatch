package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.ContactBean;
import com.realpower.petitionwatch.chatui.actvity.ChatActivity;
import com.tencent.imsdk.TIMConversationType;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class ContactDetailActivity extends BaseActivity {

    @Extra
    ContactBean bean;
    @ViewById
    TextView tv_name,tv_phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        setTitleName("他的卡片");
        tv_name.setText(bean.getSysUser().getNickname());
        tv_phoneNum.setText(bean.getSysUser().getMobilePhone());
    }

    @Click({R.id.tv_message, R.id.tv_tel})
    void inViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_message:
                ChatActivity.navToChat(this,bean.getTxAccount(), TIMConversationType.C2C);
                break;
            case R.id.tv_tel:
                makeTel();
                break;
        }
    }

    private void makeTel() {
    }
}