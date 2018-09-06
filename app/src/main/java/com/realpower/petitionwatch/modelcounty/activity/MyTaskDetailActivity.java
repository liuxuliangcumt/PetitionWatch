package com.realpower.petitionwatch.modelcounty.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcounty.bean.MyTaskBean;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class MyTaskDetailActivity extends BaseActivity {

    @Extra
    MyTaskBean.ListBean listBean;
    @ViewById
    TextView tv_content, tv_from, tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task_detail);
        setTitleName("新建监控任务");
        setTitleName(listBean.getTitle());
        tv_content.setText(listBean.getInfo());
        tv_time.setText(listBean.getStarttime().substring(0, 10) + "--------" + listBean.getEndtime().substring(0, 10));
        tv_from.setText(listBean.getMunicipalName());
    }
}
