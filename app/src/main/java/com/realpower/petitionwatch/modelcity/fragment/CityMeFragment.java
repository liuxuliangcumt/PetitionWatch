package com.realpower.petitionwatch.modelcity.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.LoginActivity_;
import com.realpower.petitionwatch.activity.ResetPasswordActivity_;
import com.realpower.petitionwatch.activity.ResetPhoneActivity_;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelwatch.activity.TaskSearchActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Administrator on 2018/4/3.
 */
@EFragment(R.layout.fragment_me)
public class CityMeFragment extends BaseFragment {
    @ViewById
    LinearLayout ll_search;

    @AfterViews
    void initViews() {
        setTitleName("我的");
        ll_search.setVisibility(View.GONE);
    }

    @Click({R.id.ll_phone, R.id.ll_update, R.id.ll_password, R.id.ll_search,R.id.btn_out})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                TaskSearchActivity_.intent(getContext()).start();
                break;
            case R.id.ll_phone:
                ResetPhoneActivity_.intent(getContext()).start();
                break;

            case R.id.ll_password:
                ResetPasswordActivity_.intent(getActivity()).isLogin(true).start();
                break;
            case R.id.btn_out:
                LoginActivity_.intent(getActivity()).start();
                getActivity().finish();
                break;
        }
    }
}