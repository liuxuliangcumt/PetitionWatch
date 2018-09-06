package com.realpower.petitionwatch.modelcounty.fragment;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
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
    private int pageNum;//当前page
    private int pages;//page 总数

    @AfterViews
    void onInitView() {
        setTitleName("告警信息");
        View view = View.inflate(getContext(), R.layout.headerview_alarmlist, null);
        AppCompatSpinner mySpinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
        final AppCompatEditText et_petition = (AppCompatEditText) view.findViewById(R.id.et_petition);
        et_petition.setVisibility(View.GONE);
       /* Drawable drawable = getResources().getDrawable(R.drawable.search);
        drawable.setBounds(0, 0, SystemInfoUtils.dp2px(getContext(), 12), SystemInfoUtils.dp2px(getContext(), 12));
        et_petition.setCompoundDrawables(drawable, null, null, null);*/
        List<String> spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerDate.add("一键报警");
        spinnerDate.add("自动报警");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(spinnerDate, getActivity());
        mySpinner.setAdapter(spinnerAdapter);
        mySpinner.setSelection(0, true);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    getAlarm("1", "");
                } else if (position == 1) {
                    getAlarm("1", "一键报警");
                } else {
                    getAlarm("1", "自动报警");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        data = new ArrayList<>();
        adapter = new AlarmListAdapter(getActivity(), data);
        lv_alarm.addHeaderView(view);
        lv_alarm.setAdapter(adapter);
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
        getAlarm("1", "");
    }

    private void getAlarm(final String page, String type) {
        Call<AlarmListResult> call = apiService.alarmAll(new AlarmAllParam(page, type));
        call.enqueue(new MyCallback<AlarmListResult>() {
            @Override
            public void onSuccessRequest(AlarmListResult result) {
                if ("1".equals(result.getStatus())) {
                    pageNum = result.getMessage().getPageNum();
                    pages = result.getMessage().getPages();
                    data.clear();
                    data.addAll(result.getMessage().getList());
                    adapter.notifyDataSetChanged();
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
}
