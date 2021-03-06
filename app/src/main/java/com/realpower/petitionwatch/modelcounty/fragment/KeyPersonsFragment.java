package com.realpower.petitionwatch.modelcounty.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcounty.activity.KeyPersonDetailActivity_;
import com.realpower.petitionwatch.modelcounty.adapter.ManagerMonitorAdapter;
import com.realpower.petitionwatch.modelcounty.bean.MonitoredBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.CountyMonitoredsResult;
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

@EFragment(R.layout.fragment_keyperson_county)
public class KeyPersonsFragment extends BaseFragment {
    private ManagerMonitorAdapter monitorAdapter;
    private List<MonitoredBean.ListBean> data;
    @ViewById
    ListView lv_key;
    @ViewById
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private boolean isLastPage;

    @AfterViews
    void onInitViews() {
        View headview = View.inflate(getContext(), R.layout.listview_headview, null);
        lv_key.addFooterView(headview);
        lv_key.addHeaderView(headview);
        data = new ArrayList<>();
        monitorAdapter = new ManagerMonitorAdapter(getContext(), data);
        lv_key.setAdapter(monitorAdapter);
        EmptyViewHelper emptyViewHelper = new EmptyViewHelper(lv_key, "暂无数据", (FrameLayout) getView().findViewById(R.id.parent));//
        lv_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KeyPersonDetailActivity_.intent(getActivity())
                        .id(data.get(position-1).getMonitoredId())
                        .isInTask(data.get(position-1).getIsInTask()).start();
            }
        });

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

    void getData(int page) {
        Call<CountyMonitoredsResult> call = apiService.monitoredAll(new PagingParam(page + ""));
        call.enqueue(new MyCallback<CountyMonitoredsResult>() {

            @Override
            public void onSuccessRequest(CountyMonitoredsResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<CountyMonitoredsResult> call, Throwable t) {

            }
        });
       /* Call<SupervisorResult> call1 = apiService.supervisorAll(new PagingParam("1"));
        call1.enqueue(new MyCallback<SupervisorResult>() {
            @Override
            public void onSuccessRequest(SupervisorResult result) {
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SupervisorResult> call, Throwable t) {

            }
        });*/
    }

    private void setData(MonitoredBean message) {
        isLastPage = message.isIsLastPage();
        refreshLayout.setEnableLoadMore(!isLastPage);
        data.addAll(message.getList());
        monitorAdapter.notifyDataSetChanged();
    }
}
