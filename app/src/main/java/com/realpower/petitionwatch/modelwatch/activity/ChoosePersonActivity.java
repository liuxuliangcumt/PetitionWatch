package com.realpower.petitionwatch.modelwatch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.result.AlarmBeanResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.modelwatch.adapter.ChoosePersonAdapter;
import com.realpower.petitionwatch.modelwatch.bean.AlarmBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class ChoosePersonActivity extends BaseActivity {
    @ViewById(R.id.lv_choose)
    ListView lv_choose;
    ChoosePersonAdapter adapter;
    private List<AlarmBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_person);
        setTitleName("选择被监控人");
        setRightName("新建");
    }

    @AfterViews
    void initViews() {
        dataList = new ArrayList<>();
        adapter = new ChoosePersonAdapter(this, dataList);
        lv_choose.setAdapter(adapter);
        lv_choose.setDivider(getResources().getDrawable(R.drawable.task_listview_divider1));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPerson();
    }

    private void getPerson() {
        Call<AlarmBeanResult> call = apiService.preAddAlarm();
        call.enqueue(new MyCallback<AlarmBeanResult>() {
            @Override
            public void onSuccessRequest(AlarmBeanResult result) {
                if ("1".equals(result.getStatus())) {
                    dataList.clear();
                    dataList.addAll(result.getMessage());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<AlarmBeanResult> call, Throwable t) {

            }
        });
    }

    @Click({R.id.tv_right, R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                KeypersonAlarmNewActivity_.intent(this).start();
                break;
            case R.id.btn_ok:
                setChoosed();
                break;
        }
    }

    private void setChoosed() {
        List<AlarmBean> beans = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).isChoose()) {
                beans.add(dataList.get(i));
            }
        }
        if (beans.size() == 0) {
            MyToastUtils.showToast("请选择被监控人");
        } else {
            Intent intent = new Intent();
            intent.putExtra("beans", (Serializable) beans);
            setResult(2, intent);
            finish();
        }
    }


}
