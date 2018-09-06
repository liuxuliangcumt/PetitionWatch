package com.realpower.petitionwatch.modelcounty.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcounty.adapter.MyTaskAdapter;
import com.realpower.petitionwatch.modelcounty.bean.MyTaskBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.PagingParam;
import com.realpower.petitionwatch.net.result.MyTaskResult;
import com.realpower.petitionwatch.net.result.StringResult;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class MyTaskActivity extends BaseActivity {

    @ViewById
    ListView lv_task;
    private List<MyTaskBean.ListBean> data;
    private MyTaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        setTitleName("我的任务");
        data = new ArrayList<>();
        adapter = new MyTaskAdapter(this, data);
        lv_task.setAdapter(adapter);
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyTaskDetailActivity_.intent(MyTaskActivity.this).listBean(data.get(position)).start();
            }
        });
        getData();
    }

    private void getData() {
        Call<MyTaskResult> call = apiService.selectSelfTask(new PagingParam("1"));
        call.enqueue(new MyCallback<MyTaskResult>() {
            @Override
            public void onSuccessRequest(MyTaskResult result) {
                if ("1".equals(result.getStatus()))
                    setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MyTaskResult> call, Throwable t) {

            }
        });
    }

    private void setData(MyTaskBean message) {
        data.clear();
        data.addAll(message.getList());
        adapter.notifyDataSetChanged();
    }


}
