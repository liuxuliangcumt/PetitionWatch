package com.realpower.petitionwatch.modelwatch.fragment;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.SearchMonitoredParam;
import com.realpower.petitionwatch.net.result.MonitoredsResult;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.modelwatch.activity.KeypersonEditActivity_;
import com.realpower.petitionwatch.modelwatch.activity.KeypersonNewActivity_;
import com.realpower.petitionwatch.modelwatch.adapter.KeyAdapter;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 重点人页面
 */
@EFragment(R.layout.fragment_keyperson)
public class KeypersonFragment extends BaseFragment {
    @ViewById(R.id.lv_key)
    ListView lv_key;
    KeyAdapter adapter;
    List<MonitoredBean> data;
    @ViewById(R.id.et_petition)
    AppCompatEditText et_petition;


    @AfterViews
    void initViews() {
        setTitleName("重点人列表");
        Drawable drawable = getResources().getDrawable(R.drawable.search);
        drawable.setBounds(0, 0, SystemInfoUtils.dp2px(getContext(), 12), SystemInfoUtils.dp2px(getContext(), 12));
        et_petition.setCompoundDrawables(drawable, null, null, null);
        data = new ArrayList<>();

        adapter = new KeyAdapter(getActivity(), data);
        lv_key.setAdapter(adapter);
        lv_key.setFooterDividersEnabled(false);
        lv_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KeypersonEditActivity_.intent(getContext()).bean(data.get(position)).start();
            }
        });
        et_petition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_petition.getText().toString().length() == 0) {
                    getData();
                } else {
                    searchMonitored(et_petition.getText().toString());
                }
            }
        });
        getData();
    }

    private void getData() {
        Call<MonitoredsResult> call = apiService.getAllMonitored();
        call.enqueue(new MyCallback<MonitoredsResult>() {
            @Override
            public void onSuccessRequest(MonitoredsResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitoredsResult> call, Throwable t) {

            }
        });

    }

    private void searchMonitored(String searchContent) {
        Call<MonitoredsResult> call = apiService.searchMonitored(new SearchMonitoredParam(searchContent));
        call.enqueue(new MyCallback<MonitoredsResult>() {
            @Override
            public void onSuccessRequest(MonitoredsResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitoredsResult> call, Throwable t) {

            }
        });
    }

    @Click({R.id.tv_right, R.id.petition_btn})
    void onViewClcik(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                KeypersonNewActivity_.intent(this).start();
                break;
            case R.id.petition_btn:
                KeypersonNewActivity_.intent(getActivity()).start();
                break;
        }
    }
}
