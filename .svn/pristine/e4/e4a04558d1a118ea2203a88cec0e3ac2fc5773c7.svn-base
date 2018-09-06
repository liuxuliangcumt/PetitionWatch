package com.realpower.petitionwatch.modelwatch.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.SearchMonitoredParam;
import com.realpower.petitionwatch.net.result.TasksResult;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.TaskAdapter;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class TaskSearchActivity extends BaseActivity {
    @ViewById(R.id.lv_task)
    ListView lv_task;
    TaskAdapter adapter;
    List<WatcherTaskBean> data;
    @ViewById(R.id.petition_btn)
    AppCompatButton petition_btn;
    @ViewById(R.id.et_petition)
    AppCompatEditText et_petition;
    @ViewById(R.id.spinner)
    AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_task);
        setTitleName("查询个人任务");
    }

    @AfterViews
    void initViews() {
        Drawable drawable = getResources().getDrawable(R.drawable.search);
        drawable.setBounds(0, 0, SystemInfoUtils.dp2px(this, 12), SystemInfoUtils.dp2px(this, 12));
        et_petition.setCompoundDrawables(drawable, null, null, null);

        data = new ArrayList<>();
        adapter = new TaskAdapter(this, data);
        lv_task.setAdapter(adapter);
        lv_task.setDivider(getResources().getDrawable(R.drawable.task_listview_divider1));
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskSearchDetailActivity_.intent(TaskSearchActivity.this).taskId(data.get(position).getTaskId()).start();
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
                getData(et_petition.getText().toString().trim());
            }
        });
        List<String> spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerDate.add("进行中");
        spinnerDate.add("已完成");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(spinnerDate, this);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(0, true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {//全部
                    getData("");
                } else if (position == 1) {//进行中
                    getUnFinishedTask();
                } else if (position == 2) {//已完成
                    getFinishedTask();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getData("");
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

    private void getData(String message) {
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
}
