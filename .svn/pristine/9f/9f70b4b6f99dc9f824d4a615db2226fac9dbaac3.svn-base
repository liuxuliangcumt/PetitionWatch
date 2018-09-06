package com.realpower.petitionwatch.modelcity.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcounty.bean.AlarmDetailBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.StringIdParam;
import com.realpower.petitionwatch.net.result.AlarmDetailResult;
import com.realpower.petitionwatch.net.result.StringResult;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;

@EActivity
public class CityAutoAlarmDetailActivity extends BaseActivity {
    @Extra
    String id;
    @ViewById
    TextView tv_name, tv_monitor,
            tv_address, tv_alarmTime;
    @ViewById
    AppCompatButton btn_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_auto_alarm_detail);
        setTitleName("自动告警详情");
        getData();
    }

    private void getData() {
        Call<AlarmDetailResult> call = apiService.selectById(new StringIdParam(id));
        call.enqueue(new MyCallback<AlarmDetailResult>() {
            @Override
            public void onSuccessRequest(AlarmDetailResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<AlarmDetailResult> call, Throwable t) {

            }
        });
    }


    private void setData(AlarmDetailBean message) {
        tv_name.setText(message.getMonitoredNames());
        tv_address.setText(message.getAreaName());
        tv_alarmTime.setText(message.getTime());
        tv_monitor.setText(message.getMonitoredNames());
        if (1 == message.getCommitStatus()) {
            btn_feedback.setClickable(false);
            btn_feedback.setText("已上报");
        }
    }


    @Click(R.id.btn_feedback)
    void onVeiwClick() {
        feedback();
    }

    private void feedback() {
        Call<StringResult> call = apiService.commitAlarm(new StringIdParam(id));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    btn_feedback.setClickable(false);
                    btn_feedback.setText("已上报");
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }
}
