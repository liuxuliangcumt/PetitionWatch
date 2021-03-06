package com.realpower.petitionwatch.modelstaff.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelstaff.adapter.PetitionAdapter;
import com.realpower.petitionwatch.modelstaff.bean.PetitionBean;
import com.realpower.petitionwatch.modelstaff.fragment.PetitionFragment;
import com.realpower.petitionwatch.modelstaff.fragment.PetitionFragment_;
import com.realpower.petitionwatch.modelstaff.fragment.SuggestionFragment;
import com.realpower.petitionwatch.modelstaff.fragment.SuggestionFragment_;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.DealParam;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.PetitionResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.util.MyToastUtils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class PetitionActivity extends BaseActivity {
    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_suggest;

    @ViewById
    ViewPager vp_petition;
    private List<String> titleData;
    PetitionFragment petitionFragment1;
    PetitionFragment petitionFragment2;
    PetitionFragment petitionFragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition);
        setTitleName("诉求列表");
        titleData = new ArrayList<>();
        titleData.add("待解决");
        titleData.add("解决中");
        titleData.add("已解决");
        petitionFragment1 = PetitionFragment_.builder().build();
        petitionFragment2 = PetitionFragment_.builder().build();
        petitionFragment3 = PetitionFragment_.builder().build();
        petitionFragment1.setCategory(1);
        petitionFragment2.setCategory(2);
        petitionFragment3.setCategory(3);
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return petitionFragment1;
                } else if (position == 1) {
                    return petitionFragment2;
                } else {
                    return petitionFragment3;
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
