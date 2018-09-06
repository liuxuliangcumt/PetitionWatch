package com.realpower.petitionwatch.modelcounty.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.RelativeAdapter;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.IdParam;
import com.realpower.petitionwatch.net.result.MonitoredResult;
import com.realpower.petitionwatch.view.CustomGridVeiw;
import com.realpower.petitionwatch.view.CustomListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class KeyPersonDetailActivity extends BaseActivity {

    @ViewById
    TextView tv_name, tv_idCard, tv_phone, tv_carType,
            tv_carNum, tv_kins, tv_carColor, tv_address,
            tv_addressDetail;
    @Extra
    int id;
    @ViewById
    CustomListView lv_kins;
    private List<MonitoredBean.RelativesBean> relativesBeans;
    private RelativeAdapter relativeAdapter;
    private List<String> picData;
    PicAdapter picAdapter;
    @ViewById
    CustomGridVeiw GV_pic;
    private String monitoredName;
    @Extra(" ")
    String isInTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_person_detail);
    }

    @AfterViews
    void onInitViews() {
        setTitleName("重点人详情");
        setRightName("监控");
        relativesBeans = new ArrayList<>();
        relativeAdapter = new RelativeAdapter(this, relativesBeans);
        lv_kins.setAdapter(relativeAdapter);
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        picAdapter.isDetail = true;
        GV_pic.setAdapter(picAdapter);
        getData();
    }

    private void getData() {
        Call<MonitoredResult> call = apiService.getMonitoredById(new IdParam(id));
        call.enqueue(new MyCallback<MonitoredResult>() {
            @Override
            public void onSuccessRequest(MonitoredResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitoredResult> call, Throwable t) {

            }
        });
    }

    private void setData(MonitoredBean message) {
        monitoredName = message.getMonitoredRealname();
        tv_address.setText(message.getPermanentAddress());
        tv_addressDetail.setText(message.getMonitoredCurrentaddress());
        tv_phone.setText(message.getMonitoredPhone());
        tv_idCard.setText(message.getMonitoredIdcard());
        tv_name.setText(message.getMonitoredRealname());
        if (message.getCars().size() != 0) {
            tv_carColor.setText(message.getCars().get(0).getColor());
            tv_carNum.setText(message.getCars().get(0).getNum());
            tv_carType.setText(message.getCars().get(0).getModel());
        }

        if (message.getRelatives().size() != 0) {
            relativesBeans.addAll(message.getRelatives());
            relativeAdapter.notifyDataSetChanged();
            lv_kins.setVisibility(View.VISIBLE);
            tv_kins.setVisibility(View.VISIBLE);
        } else {
            lv_kins.setVisibility(View.GONE);
            tv_kins.setVisibility(View.GONE);
        }
        if ("暂无".equals(message.getMonitoredLastimage())) {
            GV_pic.setVisibility(View.GONE);
        } else {
            GV_pic.setVisibility(View.VISIBLE);
            String[] picPaths = message.getMonitoredLastimage().split(",");
            for (int i = 0; i < picPaths.length; i++) {
                picData.add(Mate.PIC_PATH + picPaths[i]);
            }
            picAdapter.notifyDataSetChanged();
        }
        if (!"待监控".equals(isInTask)) {
            findViewById(R.id.tv_right).setVisibility(View.GONE);
            findViewById(R.id.btn_edit).setVisibility(View.GONE);
        }
        Log.e("aaa", "监控状态  " + isInTask);

    }

    @Click({R.id.tv_right, R.id.btn_edit})
    void onViewClick() {
        NewMonitorTaskActivity_.intent(this).id(id).name(monitoredName).start();
    }
}
