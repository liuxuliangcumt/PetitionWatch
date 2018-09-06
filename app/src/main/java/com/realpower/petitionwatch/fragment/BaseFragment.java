package com.realpower.petitionwatch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.net.ApiService;
import com.realpower.petitionwatch.net.PetitionPref_;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;


/**
 * Created by Administrator on 2017/11/9.
 */
@EFragment
public class BaseFragment extends Fragment {
    @Pref
    public static PetitionPref_ myPrefs;
    public static ApiService apiService;

    public void hideDialog() {
        ((BaseActivity) getActivity()).hideDialog();
    }

    public void showMyDialog(String m) {
        ((BaseActivity) getActivity()).showMyDialog(m);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ((BaseActivity) getActivity()).apiService;

    }

    public void setTitleName(String name) {
        TextView textView = (TextView) getView().findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(name);
        }
    }

    public void setRightName(String name) {
        TextView textView = (TextView) getView().findViewById(R.id.tv_right);
        if (textView != null) {
            getView().findViewById(R.id.iv_right).setVisibility(View.GONE);
            textView.setText(name);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void setRightIcon(int id) {
        ImageView iv_right = (ImageView) getView().findViewById(R.id.iv_right);
        if (iv_right != null) {
            getView().findViewById(R.id.tv_right).setVisibility(View.GONE);
            iv_right.setImageDrawable(getActivity().getResources().getDrawable(id));
            iv_right.setVisibility(View.VISIBLE);
        }
    }
}
