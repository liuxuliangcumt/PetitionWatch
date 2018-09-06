package com.realpower.petitionwatch.modelcity.fragment;

import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcity.activity.CityAddTaskActivity_;
import com.realpower.petitionwatch.modelcity.adapter.CityTaskAdapter;
import com.realpower.petitionwatch.modelcity.bean.TaskBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.CityTaskResult;
import com.realpower.petitionwatch.net.result.StringResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 */
@EFragment(R.layout.fragment_city_task)
public class CityTaskFragment extends BaseFragment {
    @ViewById
    ListView lv_cityTask;
    CityTaskAdapter adapter;
    List<TaskBean.ListBean> data;

    @AfterViews
    void onInitViews() {
        setTitleName("任务列表");
        setRightName("新建");
        data = new ArrayList<>();
        adapter = new CityTaskAdapter(getActivity(), data);
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
        CityAddTaskActivity_.intent(getActivity()).start();
    }
}
