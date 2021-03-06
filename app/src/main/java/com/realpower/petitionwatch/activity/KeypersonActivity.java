package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.modelwatch.adapter.KeyAdapter;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.SearchMonitoredParam;
import com.realpower.petitionwatch.net.result.MonitoredsResult;
import com.realpower.petitionwatch.util.EmptyViewHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class KeypersonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyperson);
    }

    @ViewById(R.id.lv_key)
    ListView lv_key;
    KeyAdapter adapter;
    List<MonitoredBean> data;
    @ViewById(R.id.et_petition)
    EditText et_petition;
    /* @ViewById
     SmartRefreshLayout refreshLayout;

     private boolean isFristPage, isLastPage;*/
    private int page = 1;

    @AfterViews
    void initViews() {
        setTitleName("重点人列表");
        View headview = View.inflate(this, R.layout.listview_headview, null);
        View booterView = View.inflate(this, R.layout.listview_booterview, null);
        setRightIcon(R.mipmap.add_navigation_bar_icon);
        data = new ArrayList<>();
        adapter = new KeyAdapter(this, data);
        new EmptyViewHelper(lv_key, "暂无数据", (FrameLayout) findViewById(R.id.parent));
       /* lv_key.setFooterDividersEnabled(false);
        lv_key.addFooterView(booterView);*/
        lv_key.setAdapter(adapter);
       // lv_key.addHeaderView(headview);
        lv_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KeypersonEditActivity_.intent(KeypersonActivity.this).bean(data.get(position)).start();
            }
        });
        et_petition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_petition.getText().toString().length() == 0) {
                    page = 1;
                    getData(page);
                } else {
                    searchMonitored(et_petition.getText().toString());
                }
            }
        });
       /* refreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
        });*/
        getData(1);
    }

    private void getData(int page) {
        Call<MonitoredsResult> call = apiService.getAllMonitored();
        call.enqueue(new MyCallback<MonitoredsResult>() {
            @Override
            public void onSuccessRequest(MonitoredsResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitoredsResult> call, Throwable t) {

            }
        });

    }

    private void searchMonitored(String searchContent) {
        Call<MonitoredsResult> call = apiService.searchMonitored(new SearchMonitoredParam(searchContent));
        call.enqueue(new MyCallback<MonitoredsResult>() {
            @Override
            public void onSuccessRequest(MonitoredsResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitoredsResult> call, Throwable t) {

            }
        });
    }

    @Click({R.id.iv_right})
    void onViewClcik(View view) {
        switch (view.getId()) {

            case R.id.iv_right:
                KeypersonNewActivity_.intent(this).start();
                break;
        }
    }
}
