package com.realpower.petitionwatch.modelcity.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcity.fragment.CityAlarmListFragment;
import com.realpower.petitionwatch.modelcity.fragment.CityAlarmListFragment_;
import com.realpower.petitionwatch.modelstaff.fragment.SuggestionFragment;
import com.realpower.petitionwatch.modelstaff.fragment.SuggestionFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class CityAlarmListActivity extends BaseActivity {
    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_suggest;

    @ViewById
    ViewPager vp_petition;
    private List<String> titleData;
    CityAlarmListFragment cityAlarmListFragment1;
    CityAlarmListFragment cityAlarmListFragment2;
    CityAlarmListFragment cityAlarmListFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_alarm_list);
        setTitleName("告警信息");
        titleData = new ArrayList<>();
        titleData.add("全部");
        titleData.add("一键报警");
        titleData.add("自动报警");
        cityAlarmListFragment1 = CityAlarmListFragment_.builder().build();
        cityAlarmListFragment2 = CityAlarmListFragment_.builder().build();
        cityAlarmListFragment3 = CityAlarmListFragment_.builder().build();
        cityAlarmListFragment1.setCategory("");
        cityAlarmListFragment2.setCategory("一键报警");
        cityAlarmListFragment3.setCategory("自动报警");
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return cityAlarmListFragment1;
                } else if (position == 1) {
                    return cityAlarmListFragment2;
                } else {
                    return cityAlarmListFragment3;
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
