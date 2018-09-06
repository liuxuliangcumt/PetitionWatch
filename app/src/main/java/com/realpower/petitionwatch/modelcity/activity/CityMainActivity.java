package com.realpower.petitionwatch.modelcity.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.realpower.petitionwatch.activity.TrackActivity_;
import com.realpower.petitionwatch.chatui.fragment.ConversationFragment;
import com.realpower.petitionwatch.chatui.fragment.ConversationFragment_;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.fragment.ContactsFragment;
import com.realpower.petitionwatch.fragment.ContactsFragment_;
import com.realpower.petitionwatch.modelcity.fragment.CityAlarmListFragment;
import com.realpower.petitionwatch.modelcity.fragment.CityAlarmListFragment_;
import com.realpower.petitionwatch.modelcity.fragment.CityMeFragment;
import com.realpower.petitionwatch.modelcity.fragment.CityMeFragment_;
import com.realpower.petitionwatch.modelcity.fragment.CityTaskFragment;
import com.realpower.petitionwatch.modelcity.fragment.CityTaskFragment_;
import com.realpower.petitionwatch.modelcounty.fragment.HomeFragment;
import com.realpower.petitionwatch.modelcounty.fragment.HomeFragment_;
import com.realpower.petitionwatch.util.DataGenerator;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.CustomDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class CityMainActivity extends BaseActivity {
    @ViewById(R.id.tablayout)
    TabLayout tablayout;
    HomeFragment homeFragment;
    CityMeFragment meFragment;
    CityTaskFragment tasksFragment;
    CityAlarmListFragment alarmListFragment;
    ContactsFragment contactsFragment;
    // ConversationFragment conversationFragment;
    ConversationFragment conversationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_main);
    }

    @AfterViews
    void onInitView() {
        alarmListFragment = CityAlarmListFragment_.builder().build();
        tasksFragment = CityTaskFragment_.builder().build();
        meFragment = CityMeFragment_.builder().build();
        homeFragment = HomeFragment_.builder().build();
        contactsFragment = ContactsFragment_.builder().build();
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
                        icon.setImageResource(DataGenerator.mCityTabResPressed[i]);
                        //   text.setTextColor(getResources().getColor(android.R.color.black));
                    } else {// 未选中状态
                        icon.setImageResource(DataGenerator.mCityTabRes[i]);
                        //  text.setTextColor(getResources().getColor(android.R.color.darker_gray));
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

        for (int i = 0; i < 5; i++) {
            tablayout.addTab(tablayout.newTab().setCustomView(DataGenerator.getCityTabView(this, i)));
        }
    }

    private void onTabItemSelected(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 4:
                fragment = meFragment;
                break;
            case 1:
                fragment = contactsFragment;
                break;
            case 0:
                //  fragment = conversationFragment;
                break;
            case 3:
                fragment = alarmListFragment;
                break;
        }
        if (position == 2) {
            TrackActivity_.intent(this).start();
        } else if (position == 0) {
            switchContent(conversationFragment);
        } else {
            switchContent(fragment);
        }
    }

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

    private Fragment mContent = new Fragment();

    @Click(R.id.iv_plus)
    void onViewClick() {
        showChoseDailog();
    }

    private void showChoseDailog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_city_mainchose);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        ImageView iv_cha = (ImageView) dialog.getCustomView().findViewById(R.id.iv_cha);
        //人员轨迹  任务  监控
        LinearLayout ll_camera = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_camera);
        LinearLayout ll_location = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_location);
        LinearLayout ll_task = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_task);
        ll_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackActivity_.intent(CityMainActivity.this).start();
            }
        });
        iv_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ll_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityTaskActivity_.intent(CityMainActivity.this).start();
            }
        });

        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CamerasActivity_.intent(CityMainActivity.this).start();
            }
        });
    }
}
