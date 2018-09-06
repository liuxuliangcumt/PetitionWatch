package com.realpower.petitionwatch.modelcounty.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelcounty.adapter.TrackListAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.util.SystemInfoUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class TrackListActivity extends BaseActivity {
    @ViewById(R.id.lv_task)
    ListView lv_task;
    TrackListAdapter adapter;
    List<String> data;

    @ViewById(R.id.et_petition)
    AppCompatEditText et_petition;
    @ViewById(R.id.spinner)
    AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);
    }

    @AfterViews
    void initViews() {
        setTitleName("人员轨迹");
        Drawable drawable = getResources().getDrawable(R.drawable.search);
        drawable.setBounds(0, 0, SystemInfoUtils.dp2px(this, 12), SystemInfoUtils.dp2px(this, 12));
        et_petition.setCompoundDrawables(drawable, null, null, null);

        data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter = new TrackListAdapter(this, data);
        lv_task.setAdapter(adapter);
        lv_task.setDivider(getResources().getDrawable(R.drawable.task_listview_divider1));
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        et_petition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // getData(et_petition.getText().toString().trim());
            }
        });
        List<String> spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerDate.add("进行中");
        spinnerDate.add("已完成");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(spinnerDate, this);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(0, true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {//全部

                } else if (position == 1) {//进行中

                } else if (position == 2) {//已完成

            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
