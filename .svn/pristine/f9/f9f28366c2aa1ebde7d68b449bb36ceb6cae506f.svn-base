package com.realpower.petitionwatch.modelcounty.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcounty.fragment.AlarmListFragment;
import com.realpower.petitionwatch.modelcounty.fragment.AlarmListFragment_;
import com.realpower.petitionwatch.modelcounty.fragment.KeyPersonsFragment_;
import com.realpower.petitionwatch.modelcounty.fragment.MonitorFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class AlarmListActivity extends BaseActivity {
    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_suggest;

    @ViewById
    ViewPager vp_petition;
    private List<String> titleData;
    AlarmListFragment alarmListFragment1;
    AlarmListFragment alarmListFragment2;
    AlarmListFragment alarmListFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        setTitleName("告警信息");
        titleData = new ArrayList<>();
        titleData.add("全部");
        titleData.add("一键报警");
        titleData.add("自动报警");
        alarmListFragment1 = AlarmListFragment_.builder().build();
        alarmListFragment2 = AlarmListFragment_.builder().build();
        alarmListFragment3 = AlarmListFragment_.builder().build();
        alarmListFragment1.setType("");
        alarmListFragment2.setType("一键报警");
        alarmListFragment3.setType("自动报警");
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return alarmListFragment1;
                } else if (position == 1) {
                    return alarmListFragment2;
                } else {
                    return alarmListFragment3;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleData.get(position);

            }
        };

        vp_petition.setAdapter(pagerAdapter);
        tl_suggest.setupWithViewPager(vp_petition);
        tl_suggest.setTabMode(TabLayout.MODE_FIXED);
        tl_suggest.setTabsFromPagerAdapter(pagerAdapter);

    }
}
