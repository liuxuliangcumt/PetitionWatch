package com.realpower.petitionwatch.modelcounty.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.activity.VideoPlayActivity_;
import com.realpower.petitionwatch.modelcounty.bean.TaskDetailBean;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.TaskDetailRemarkAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.TaskDetailStateAdpter;
import com.realpower.petitionwatch.modelwatch.adapter.VideoAdapter;
import com.realpower.petitionwatch.modelwatch.bean.StatusListBean;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.IdParam;
import com.realpower.petitionwatch.net.param.TaskSelectorStatusParam;
import com.realpower.petitionwatch.net.result.TaskDetailResult;
import com.realpower.petitionwatch.net.result.TaskStatusListResult;
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
public class MonitorTaskDetailActivity extends BaseActivity {
    @ViewById
    TextView tv_taskTitle, tv_detail, tv_taskState,
            tv_name, tv_phone, tv_idCard, tv_time, tv_address;
    @ViewById
    CustomListView lv_state, lv_safe,
            lv_remarks, lv_video;
    @ViewById
    CustomGridVeiw gv_pic;
    private List<StatusListBean> stateData;
    TaskDetailRemarkAdapter remarkAdapter;//备注
    TaskDetailStateAdpter safeLeveAdapter;//安全级别
    TaskDetailStateAdpter stateAdpter;//状态
    private List<String> picData;
    private List<String> videoData;
    private PicAdapter picAdapter;
    private VideoAdapter videoAdapter;
    SpinnerAdapter spinnerAdapter;
    @ViewById(R.id.spinner)
    AppCompatSpinner mySpinner;
    List<String> spinnerDate;
    @Extra
    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_task_detail);
    }

    @AfterViews
    void onInitViews() {
        setTitleName("监控任务详情");
        stateData = new ArrayList<>();
        stateAdpter = new TaskDetailStateAdpter(this, stateData);
        safeLeveAdapter = new TaskDetailStateAdpter(this, stateData);
        safeLeveAdapter.isSafeLeve = true;
        lv_state.setAdapter(stateAdpter);
        lv_safe.setAdapter(safeLeveAdapter);
        remarkAdapter = new TaskDetailRemarkAdapter(this, stateData);
        lv_remarks.setAdapter(remarkAdapter);
        picData = new ArrayList<>();
        videoData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        videoAdapter = new VideoAdapter(this, videoData);
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        gv_pic.setAdapter(picAdapter);
        lv_video.setAdapter(videoAdapter);
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoPlayActivity_.intent(MonitorTaskDetailActivity.this).path(videoData.get(position)).start();
            }
        });

        spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerAdapter = new SpinnerAdapter(spinnerDate, this);
        mySpinner.setAdapter(spinnerAdapter);
        mySpinner.setSelection(0, true);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    getStatus("");
                } else {
                    getStatus(keyValuesBeans.get(position - 1).getValue());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getData();
        getStatus("");
    }

    private void getData() {
        Call<TaskDetailResult> call = apiService.taskDetail(new IdParam(taskId));
        call.enqueue(new MyCallback<TaskDetailResult>() {
            @Override
            public void onSuccessRequest(TaskDetailResult result) {
                if ("1".equals(result.getStatus())) {
                    setTaskData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TaskDetailResult> call, Throwable t) {

            }
        });

    }

    private void getStatus(String supervisorId) {
        Call<TaskStatusListResult> call1 = apiService.taskSelectStatus(new TaskSelectorStatusParam(taskId + "",
                supervisorId, "", ""));
        call1.enqueue(new MyCallback<TaskStatusListResult>() {
            @Override
            public void onSuccessRequest(TaskStatusListResult result) {
                setStatuData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TaskStatusListResult> call, Throwable t) {

            }
        });
    }

    private void setStatuData(List<StatusListBean> message) {
        stateData.clear();
        picData.clear();
        videoData.clear();
        stateData.addAll(message);
        safeLeveAdapter.notifyDataSetChanged();
        stateAdpter.notifyDataSetChanged();
        remarkAdapter.notifyDataSetChanged();
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i).getImageUrl().length() > 2) {
                String[] path = message.get(i).getImageUrl().split(",");
                for (int j = 0; j < path.length; j++) {
                    picData.add(Mate.PIC_PATH + path[j]);
                }
                picAdapter.notifyDataSetChanged();
            }

            if (message.get(i).getVideoUrl().length() > 2) {
                String[] path = message.get(i).getVideoUrl().split(",");
                for (int j = 0; j < path.length; j++) {
                    videoData.add(Mate.PIC_PATH + path[j]);
                }
                videoAdapter.notifyDataSetChanged();
            }
        }

    }

    private List<TaskDetailBean.KeyValuesBean> keyValuesBeans;
    private void setTaskData(TaskDetailBean message) {
        tv_name.setText(message.getMonitoredName());
        tv_idCard.setText(message.getCard());
        tv_phone.setText(message.getPhone());
        tv_taskTitle.setText(message.getTitle());
        tv_detail.setText(message.getInfo());
        tv_time.setText(message.getStarttime() + "--" + message.getEndtime());
        if (1 == message.getStatus()) {
            tv_taskState.setText("进行中");
        } else {
            tv_taskState.setText("已结束");
        }
       /* if ("1".equals(message.getRisk())) {
            tv_safeLevel.setText("");
        } else {

        }*/
        tv_address.setText(message.getMonitoredAreaName());
        keyValuesBeans = message.getKeyValues();
        for (int i = 0; i < keyValuesBeans.size(); i++) {
            spinnerDate.add(keyValuesBeans.get(i).getLabel());
        }
        spinnerAdapter.notifyDataSetChanged();
    }



}
