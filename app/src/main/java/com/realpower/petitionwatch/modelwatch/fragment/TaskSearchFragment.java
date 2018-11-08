package com.realpower.petitionwatch.modelwatch.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelwatch.activity.TaskSearchDetailActivity_;
import com.realpower.petitionwatch.modelwatch.adapter.TaskAdapter;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.SearchMonitoredParam;
import com.realpower.petitionwatch.net.result.TasksResult;
import com.realpower.petitionwatch.util.EmptyViewHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by ruipu on 2018/9/12.
 */
@EFragment(R.layout.fragment_search_task)
public class TaskSearchFragment extends BaseFragment {

    @ViewById(R.id.lv_task)
    ListView lv_task;
    TaskAdapter adapter;
    List<WatcherTaskBean> data;
    @ViewById
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private boolean isFristPage, isLastPage;
    private int category = 1;//yijian类别


    @AfterViews
    void initViews() {


        data = new ArrayList<>();
        adapter = new TaskAdapter(getContext(), data);
        lv_task.setAdapter(adapter);
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskSearchDetailActivity_.intent(getActivity()).taskId(data.get(position).getTaskId()).start();
            }
        });
        new EmptyViewHelper(lv_task, (ViewGroup) getView().findViewById(R.id.parent));
        List<String> spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerDate.add("进行中");
        spinnerDate.add("已完成");
        /*refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               *//* data.clear();
                page = 1;
                getData(page);
                refreshLayout.finishRefresh(1000);*//*
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

               if (!isLastPage) {
                    page++;
                    getData(page);
                    refreshLayout.finishLoadMore(1000);
                } else {
                    MyToastUtils.showToast("没有更多了");
                    refreshLayout.finishLoadMore();
                }
            }
        });*/
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        switch (category) {
            case 0:
                getData("");
                break;
            case 1:
                getUnFinishedTask();
                break;
            case 2:
                getFinishedTask();
                break;
        }

    }

    private void getUnFinishedTask() {
        Call<TasksResult> call = apiService.getUnFinishedTask();
        call.enqueue(new MyCallback<TasksResult>() {
            @Override
            public void onSuccessRequest(TasksResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TasksResult> call, Throwable t) {

            }
        });
    }

    private void getFinishedTask() {
        Call<TasksResult> call = apiService.getFinishedTask();
        call.enqueue(new MyCallback<TasksResult>() {
            @Override
            public void onSuccessRequest(TasksResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TasksResult> call, Throwable t) {

            }
        });
    }

    public void getData(String message) {
        Call<TasksResult> call = apiService.searchTask(new SearchMonitoredParam(message));
        call.enqueue(new MyCallback<TasksResult>() {
            @Override
            public void onSuccessRequest(TasksResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TasksResult> call, Throwable t) {

            }
        });
    }

    private void setData(List<WatcherTaskBean> message) {
        data.clear();
        data.addAll(message);
        adapter.notifyDataSetChanged();

    }

    public void setCategory(int category) {
        this.category = category;

    }
}