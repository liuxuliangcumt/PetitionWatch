package com.realpower.petitionwatch.modelcity.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcity.adapter.CityTaskAdapter;
import com.realpower.petitionwatch.modelcity.bean.TaskBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.CityTaskResult;
import com.realpower.petitionwatch.util.EmptyViewHelper;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    @ViewById
    SmartRefreshLayout refreshLayout;

    private boolean isFristPage, isLastPage;
    private int page = 1;
    @ViewById
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_task);
    }

    @AfterViews
    void onInitViews() {
        setTitleName("任务列表");
        setRightIcon(R.mipmap.add_navigation_bar_icon);
        View headview = View.inflate(this, R.layout.listview_headview, null);
        lv_cityTask.addHeaderView(headview);
        lv_cityTask.addFooterView(headview);
        data = new ArrayList<>();
        adapter = new CityTaskAdapter(this, data);
        lv_cityTask.setAdapter(adapter);
        new EmptyViewHelper(lv_cityTask, "暂无数据", (FrameLayout) findViewById(R.id.parent));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                data.clear();
                page = 1;
                getlistData(page);
                refreshLayout.finishRefresh(1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if (!isLastPage) {
                    page++;
                    getlistData(page);
                    refreshLayout.finishLoadMore(1000);
                } else {
                    MyToastUtils.showToast("没有更多了");
                    refreshLayout.finishLoadMore();
                }
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getlistData(1);
    }

    private void getlistData(int page) {
        Call<CityTaskResult> call = apiService.taskSelectAll(new PagingParam(page + ""));
        call.enqueue(new MyCallback<CityTaskResult>() {
            @Override
            public void onSuccessRequest(CityTaskResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
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

    private void setData(TaskBean message) {
        isFristPage = message.isIsFirstPage();
        isLastPage = message.isIsLastPage();
        refreshLayout.setEnableLoadMore(!isLastPage);

        data.clear();
        data.addAll(message.getList());
        adapter.notifyDataSetChanged();
    }

    @Click(R.id.iv_right)
    void onViewClick() {
        CityAddTaskActivity_.intent(this).start();
    }

}
