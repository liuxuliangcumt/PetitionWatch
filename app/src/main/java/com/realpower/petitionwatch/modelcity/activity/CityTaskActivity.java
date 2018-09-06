package com.realpower.petitionwatch.modelcity.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcity.adapter.CityTaskAdapter;
import com.realpower.petitionwatch.modelcity.bean.TaskBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.CityTaskResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class CityTaskActivity extends BaseActivity {
    @ViewById
    ListView lv_cityTask;
    CityTaskAdapter adapter;
    List<TaskBean.ListBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_task);
    }

    @AfterViews
    void onInitViews() {
        setTitleName("任务列表");
        setRightName("新建");
        data = new ArrayList<>();
        adapter = new CityTaskAdapter(this, data);
        lv_cityTask.setAdapter(adapter);
        getlistData();
    }

    private void getlistData() {
        Call<CityTaskResult> call = apiService.taskSelectAll(new PagingParam("1"));
        call.enqueue(new MyCallback<CityTaskResult>() {
            @Override
            public void onSuccessRequest(CityTaskResult result) {
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
            public void onFailureRequest(Call<CityTaskResult> call, Throwable t) {

            }
        });

    }

    @Click(R.id.tv_right)
    void onViewClick() {
        CityAddTaskActivity_.intent(this).start();
    }

}
