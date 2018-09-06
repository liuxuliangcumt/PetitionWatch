package com.realpower.petitionwatch.modelcounty.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.modelcounty.activity.KeyPersonDetailActivity_;
import com.realpower.petitionwatch.modelcounty.adapter.ManagerMonitorAdapter;
import com.realpower.petitionwatch.modelcounty.adapter.ManagerSupervisorAdapter;
import com.realpower.petitionwatch.modelcounty.bean.MonitoredBean;
import com.realpower.petitionwatch.modelcounty.bean.SupervisorBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.CountyMonitoredsResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.SupervisorResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EFragment(R.layout.fragment_manage)
public class ManageFragment extends BaseFragment {
    @ViewById
    RadioButton rb_petition, rb_monitor;

    @ViewById
    ListView lv_petition, lv_monitor;
    private int page = 1;
    private ManagerMonitorAdapter monitorAdapter;
    private List<MonitoredBean.ListBean> monitorData;
    private List<SupervisorBean.ListBean> supervisorData;
    private ManagerSupervisorAdapter supervisorAdapter;

    @AfterViews
    void onInitViews() {
        setTitleName("人员管理");
        rb_monitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        rb_petition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lv_monitor.setVisibility(View.VISIBLE);
                    lv_petition.setVisibility(View.GONE);
                } else {
                    lv_monitor.setVisibility(View.GONE);
                    lv_petition.setVisibility(View.VISIBLE);
                }
            }
        });
        monitorData = new ArrayList<>();
        monitorAdapter = new ManagerMonitorAdapter(getActivity(), monitorData);
        lv_monitor.setAdapter(monitorAdapter);
        supervisorData = new ArrayList<>();
        supervisorAdapter = new ManagerSupervisorAdapter(getActivity(), supervisorData);
        lv_petition.setAdapter(supervisorAdapter);

        lv_monitor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KeyPersonDetailActivity_.intent(getActivity()).id(monitorData.get(position).getMonitoredId()).start();
            }
        });

        getDdata();
    }

    private void getDdata() {
        Call<CountyMonitoredsResult> call = apiService.monitoredAll(new PagingParam(page + ""));
        call.enqueue(new MyCallback<CountyMonitoredsResult>() {

            @Override
            public void onSuccessRequest(CountyMonitoredsResult result) {
                if ("1".equals(result.getStatus())) {

                }
                setMonitorData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<CountyMonitoredsResult> call, Throwable t) {

            }
        });
        Call<SupervisorResult> call1 = apiService.supervisorAll(new PagingParam("1"));
        call1.enqueue(new MyCallback<SupervisorResult>() {
            @Override
            public void onSuccessRequest(SupervisorResult result) {
                setSupertorData(result.getMessage().getList());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SupervisorResult> call, Throwable t) {

            }
        });
    }

    private void setSupertorData(List<SupervisorBean.ListBean> list) {
        supervisorData.addAll(list);
        supervisorAdapter.notifyDataSetChanged();
    }

    private void setMonitorData(MonitoredBean message) {
        monitorData.clear();
        monitorData.addAll(message.getList());
        monitorAdapter.notifyDataSetChanged();
    }

    @Click({R.id.tv_right})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:

                break;
        }
    }
}
