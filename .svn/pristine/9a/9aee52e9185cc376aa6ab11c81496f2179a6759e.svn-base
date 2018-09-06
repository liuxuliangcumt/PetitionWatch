package com.realpower.petitionwatch.modelcounty.fragment;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcounty.activity.MonitorTaskDetailActivity_;
import com.realpower.petitionwatch.modelcounty.adapter.MonitoreTaskAdapter;
import com.realpower.petitionwatch.modelcounty.bean.MonitorTaskBean;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.MonitorTaskResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 */
@EFragment(R.layout.fragment_assigntask)
public class MonitorTasksFragment extends BaseFragment {

    @ViewById
    ListView lv_task;
    MonitoreTaskAdapter adapter;
    private List<MonitorTaskBean.ListBean> datas;
    @ViewById(R.id.spinner)
    AppCompatSpinner mySpinner;

    @AfterViews
    void onInIiViews() {
        setTitleName("监控任务");
        setRightIcon(R.drawable.search);
        datas = new ArrayList<>();
        adapter = new MonitoreTaskAdapter(getContext(), datas);
        lv_task.setAdapter(adapter);
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonitorTaskDetailActivity_.intent(getActivity()).taskId(datas.get(position).getTaskId()).start();
            }
        });

        List<String> spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerDate.add("进行中");
        spinnerDate.add("已完成");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(spinnerDate, getActivity());
        mySpinner.setAdapter(spinnerAdapter);
        mySpinner.setSelection(0, true);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        Call<MonitorTaskResult> call = apiService.taskAll(new PagingParam("1"));
        call.enqueue(new MyCallback<MonitorTaskResult>() {
            @Override
            public void onSuccessRequest(MonitorTaskResult result) {
                if ("1".equals(result.getStatus())) {
                    setTaskListData(result.getMessage().getList());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitorTaskResult> call, Throwable t) {

            }
        });
    }

    private void getDataByStatus(String statu) {
        Call<MonitorTaskResult> call = apiService.taskAll(new PagingParam("1"));
        call.enqueue(new MyCallback<MonitorTaskResult>() {
            @Override
            public void onSuccessRequest(MonitorTaskResult result) {
                if ("1".equals(result.getStatus())) {
                    setTaskListData(result.getMessage().getList());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitorTaskResult> call, Throwable t) {

            }
        });
    }


    private void setTaskListData(List<MonitorTaskBean.ListBean> list) {
        datas.clear();
        datas.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
