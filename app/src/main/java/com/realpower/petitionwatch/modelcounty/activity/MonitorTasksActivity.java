package com.realpower.petitionwatch.modelcounty.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcounty.fragment.MonitorTasksFragment;
import com.realpower.petitionwatch.modelcounty.fragment.MonitorTasksFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity
public class MonitorTasksActivity extends BaseActivity {

    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_suggest;

    @ViewById
    ViewPager vp_petition;
    private List<String> titleData;
    MonitorTasksFragment all;
    MonitorTasksFragment going;
    MonitorTasksFragment end;
    MonitorTasksFragment end2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_tasks);
        setTitleName("监控任务");

        titleData = new ArrayList<>();
        titleData.add("全部");
        titleData.add("监控中");
        titleData.add("提前结束");
        titleData.add("正常结束");
        all = MonitorTasksFragment_.builder().build();
        going = MonitorTasksFragment_.builder().build();
        end = MonitorTasksFragment_.builder().build();
        end2 = MonitorTasksFragment_.builder().build();
        all.setState("");
        going.setState("1");
        end.setState("2");
        end2.setState("3");
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return all;
                } else if (position == 1) {
                    return going;
                } else if (position == 2) {
                    return end;
                } else {
                    return end2;
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
