package com.realpower.petitionwatch.modelstaff.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelstaff.adapter.PetitionAdapter;
import com.realpower.petitionwatch.modelstaff.bean.PetitionBean;
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
    PetitionAdapter adapter;
    private List<PetitionBean.ListBean> data;
    @ViewById
    ListView lv_petition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition);
        setTitleName("诉求列表");
        data = new ArrayList<>();
        adapter = new PetitionAdapter(this, data);
        lv_petition.setAdapter(adapter);
        lv_petition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                PetitionDetailActivity_.intent(PetitionActivity.this).appealId(data.get(position).getAppealId()).start();
            }
        });
        adapter.setViewClick(new PetitionAdapter.OnViewClick() {
            @Override
            public void onBtnClick(int position) {
                deelingPitition(position, data.get(position).getAppealId() + "");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getdata();

    }

    private void deelingPitition(final int position, String criterias) {
        List<String> stringList = new ArrayList<>();
        stringList.add(criterias);
        Call<StringResult> call = apiService.startDeal(new DealParam(stringList));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        data.get(position).setCurrentStatus(2);
                        adapter.notifyDataSetChanged();
                        MyToastUtils.showToast("更新状态成功");
                    }
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }

    private void getdata() {
        Call<PetitionResult> call = apiService.getAppeal(new PagingParam("46"));
        call.enqueue(new MyCallback<PetitionResult>() {
            @Override
            public void onSuccessRequest(PetitionResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage().getList());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<PetitionResult> call, Throwable t) {

            }
        });

    }
}
