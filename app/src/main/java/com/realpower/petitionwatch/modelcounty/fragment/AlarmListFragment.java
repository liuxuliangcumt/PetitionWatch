package com.realpower.petitionwatch.modelcounty.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcounty.activity.AlarmDetailActivity_;
import com.realpower.petitionwatch.modelcounty.activity.AutoAlarmDetailActivity_;
import com.realpower.petitionwatch.modelcounty.adapter.AlarmListAdapter;
import com.realpower.petitionwatch.modelcounty.bean.AlarmBean;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.AlarmAllParam;
import com.realpower.petitionwatch.net.result.AlarmListResult;
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

@EFragment(R.layout.fragment_alarmlist)
public class AlarmListFragment extends BaseFragment {

    private List<AlarmBean.ListBean> data;
    private AlarmListAdapter adapter;
    @ViewById
    ListView lv_alarm;
    @ViewById
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private boolean isFristPage, isLastPage;
    private String type;

    @AfterViews
    void onInitView() {
        View headview = View.inflate(getContext(), R.layout.listview_headview, null);

        data = new ArrayList<>();
        lv_alarm.addHeaderView(headview);
        lv_alarm.addFooterView(headview);
        adapter = new AlarmListAdapter(getActivity(), data);
        lv_alarm.setAdapter(adapter);
        EmptyViewHelper emptyViewHelper = new EmptyViewHelper(lv_alarm, "暂无数据", (FrameLayout) getView().findViewById(R.id.parent));
        lv_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("一键报警".equals(data.get(position - 1).getCategory())) {
                    AlarmDetailActivity_.intent(getActivity()).id(data.get(position - 1).getId() + "").start();
                } else {
                    AutoAlarmDetailActivity_.intent(getActivity()).id(data.get(position - 1).getId() + "").start();
                }
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                data.clear();
                page = 1;
                getAlarm(page);
                refreshLayout.finishRefresh(1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if (!isLastPage) {
                    page++;
                    getAlarm(page);
                    refreshLayout.finishLoadMore(1000);
                } else {
                    MyToastUtils.showToast("没有更多了");
                    refreshLayout.finishLoadMore();
                }
            }
        });
        getAlarm(page);
    }

    private void getAlarm(int page) {
        Call<AlarmListResult> call = apiService.alarmAll(new AlarmAllParam(page + "", type));
        call.enqueue(new MyCallback<AlarmListResult>() {
            @Override
            public void onSuccessRequest(AlarmListResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<AlarmListResult> call, Throwable t) {

            }
        });
    }

    private void setData(AlarmBean message) {
        isFristPage = message.isIsFirstPage();
        isLastPage = message.isIsLastPage();
        refreshLayout.setEnableLoadMore(!isLastPage);
        data.addAll(message.getList());
        adapter.notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }
}
