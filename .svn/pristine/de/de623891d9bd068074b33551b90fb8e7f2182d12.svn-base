package com.realpower.petitionwatch.modelcounty.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcounty.adapter.ManagerSupervisorAdapter;
import com.realpower.petitionwatch.modelcounty.bean.SupervisorBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.SupervisorResult;
import com.realpower.petitionwatch.util.EmptyViewHelper;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 *
 */
@EFragment(R.layout.fragment_monitor_county)
public class MonitorFragment extends BaseFragment {
    private List<SupervisorBean.ListBean> data;
    private ManagerSupervisorAdapter supervisorAdapter;
    @ViewById
    ListView lv_monitor;
    @ViewById
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private boolean isLastPage;

    @AfterViews
    void onInitViews() {
        View headview = View.inflate(getContext(), R.layout.listview_headview, null);
        View booterView = View.inflate(getContext(), R.layout.listview_booterview, null);
        lv_monitor.addHeaderView(headview);
        lv_monitor.addFooterView(booterView);
        data = new ArrayList<>();
        supervisorAdapter = new ManagerSupervisorAdapter(getContext(), data);
        lv_monitor.setAdapter(supervisorAdapter);
        EmptyViewHelper emptyViewHelper = new EmptyViewHelper(lv_monitor, "暂无数据", (FrameLayout) getView().findViewById(R.id.parent));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                data.clear();
                page = 1;
                getData(page);
                refreshLayout.finishRefresh(1000);
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
        });

        getData(1);
    }

    private void getData(int page) {
        Call<SupervisorResult> call1 = apiService.supervisorAll(new PagingParam(page + ""));
        call1.enqueue(new MyCallback<SupervisorResult>() {
            @Override
            public void onSuccessRequest(SupervisorResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SupervisorResult> call, Throwable t) {

            }
        });
    }

    private void setData(SupervisorBean message) {
        isLastPage = message.isIsLastPage();
        refreshLayout.setEnableLoadMore(!isLastPage);
        data.addAll(message.getList());
        supervisorAdapter.notifyDataSetChanged();
    }
}
