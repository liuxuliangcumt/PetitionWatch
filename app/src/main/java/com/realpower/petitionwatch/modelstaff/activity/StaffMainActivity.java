package com.realpower.petitionwatch.modelstaff.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.activity.CamerasActivity_;
import com.realpower.petitionwatch.activity.ContactsActivity_;
import com.realpower.petitionwatch.activity.TrackActivity_;
import com.realpower.petitionwatch.chatui.fragment.ConversationFragment;
import com.realpower.petitionwatch.chatui.fragment.ConversationFragment_;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.fragment.ContactsFragment;
import com.realpower.petitionwatch.fragment.ContactsFragment_;
import com.realpower.petitionwatch.modelcounty.activity.CountyMainActivity;
import com.realpower.petitionwatch.modelcounty.activity.CountySetActivity_;
import com.realpower.petitionwatch.modelcounty.activity.MyTaskActivity_;
import com.realpower.petitionwatch.modelcounty.fragment.HomeFragment;
import com.realpower.petitionwatch.modelcounty.fragment.HomeFragment_;
import com.realpower.petitionwatch.modelstaff.fragment.PetitionFragment;
import com.realpower.petitionwatch.modelstaff.fragment.PetitionFragment_;
import com.realpower.petitionwatch.modelstaff.fragment.StaffMeFragment;
import com.realpower.petitionwatch.modelstaff.fragment.StaffMeFragment_;
import com.realpower.petitionwatch.modelstaff.fragment.SuggestionFragment;
import com.realpower.petitionwatch.modelstaff.fragment.SuggestionFragment_;
import com.realpower.petitionwatch.activity.KeypersonActivity_;
import com.realpower.petitionwatch.util.DataGenerator;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.modelwatch.fragment.KeypersonFragment;
import com.realpower.petitionwatch.modelwatch.fragment.KeypersonFragment_;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.CustomDialog;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class StaffMainActivity extends BaseActivity {
    @ViewById(R.id.tablayout)
    TabLayout tablayout;
    StaffMeFragment meFragment;
   // KeypersonFragment keypersonFragment;
  //  ContactsFragment contactsFragment;
    // ConversationFragment conversationFragment;
    ConversationFragment conversationFragment;
    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);
        onInitViews();
    }

    private void onInitViews() {
        homeFragment = HomeFragment_.builder().build();
        meFragment = StaffMeFragment_.builder().build();
      //  keypersonFragment = KeypersonFragment_.builder().build();
      //  contactsFragment = ContactsFragment_.builder().build();
        conversationFragment = ConversationFragment_.builder().build();
        //  conversationFragment = new ConversationFragment();
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
                // Tab 选中之后，改变各个Tab的状态
                for (int i = 0; i < tablayout.getTabCount(); i++) {
                    View view = tablayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if (i == tab.getPosition()) { // 选中状态
                        icon.setImageResource(DataGenerator.mStaffTabResPressed[i]);
                        text.setTextColor(getResources().getColor(R.color.colorPrimary));
                    } else {// 未选中状态
                        icon.setImageResource(DataGenerator.mStaffTabRes[i]);
                        text.setTextColor(getResources().getColor(R.color.cb3b3b3));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < 3; i++) {
            tablayout.addTab(tablayout.newTab().setCustomView(DataGenerator.getStaffTabView(this, i)));
        }
    }

    private void onTabItemSelected(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = conversationFragment;
                break;
            case 2:
                fragment = meFragment;
                break;
        }
        if (position == 1) {

        } else {
            switchContent(fragment);
        }
    }

    private Fragment mContent = new Fragment();

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.home_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
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

    private String tag = "aaa";
    TIMConversation conversation;

    @Click(R.id.iv_plus)
    void onViewClick() {
        showChoseDailog();
        onBtnClick();
    }

    void onBtnClick() {
        TIMMessage msg = new TIMMessage();
        Log.e(tag, "onBtnClick");
//添加文本内容
        TIMTextElem elem = new TIMTextElem();
        elem.setText("a new msg");

//将elem添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(tag, "addElement failed");
            return;
        }
        conversation = TIMManager.getInstance().getConversation(
                TIMConversationType.C2C,    //会话类型：单聊
                "appTest");
//发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
                Log.d(tag, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(tag, "SendMsg ok");
            }
        });
    }

    private void showChoseDailog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog,
                R.layout.dialog_staff_mainchose);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
        ImageView iv_cha = (ImageView) dialog.getCustomView().findViewById(R.id.iv_cha);
        TextView tv_petition = (TextView) dialog.getCustomView().findViewById(R.id.tv_petition);
        TextView tv_suggestion = (TextView) dialog.getCustomView().findViewById(R.id.tv_suggestion);
        TextView tv_addressBook = (TextView) dialog.getCustomView().findViewById(R.id.tv_addressBook);
        TextView tv_importantPerson = (TextView) dialog.getCustomView().findViewById(R.id.tv_importantPerson);
        iv_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_addressBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  PetitionActivity_.intent(StaffMainActivity.this).start();
                ContactsActivity_.intent(StaffMainActivity.this).start();
            }
        });
        tv_suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuggestionActivity_.intent(StaffMainActivity.this).start();
            }
        });
        tv_petition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CamerasActivity_.intent(StaffMainActivity.this).start();
                PetitionActivity_.intent(StaffMainActivity.this).start();
            }
        });
        tv_importantPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeypersonActivity_.intent(StaffMainActivity.this).start();
            }
        });
    }
}
