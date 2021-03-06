package com.realpower.petitionwatch.modelwatch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.activity.VideoPlayActivity_;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.bean.StatusListBean;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.TaskParam;
import com.realpower.petitionwatch.net.result.WatcherTaskResult;
import com.realpower.petitionwatch.view.CustomGridVeiw;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.modelwatch.adapter.TaskDetailRemarkAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.TaskDetailStateAdpter;
import com.realpower.petitionwatch.modelwatch.adapter.VideoAdapter;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class TaskSearchDetailActivity extends BaseActivity {

    @ViewById(R.id.lv_state)
    CustomListView lv_state;

    @ViewById(R.id.lv_safe)
    CustomListView lv_safe;

    @ViewById(R.id.lv_remarks)
    CustomListView lv_remarks;

    @ViewById(R.id.gv_pic)
    CustomGridVeiw gv_pic;

    @ViewById(R.id.lv_video)
    CustomGridVeiw lv_video;

    private List<StatusListBean> stateData;
    TaskDetailRemarkAdapter remarkAdapter;//备注
    TaskDetailStateAdpter safeLeveAdapter;//安全级别
    TaskDetailStateAdpter stateAdpter;//状态

    private List<LocalMedia> picData;
    private List<LocalMedia> videoData;
    private PicAdapter picAdapter;
    private VideoAdapter videoAdapter;

    @Extra
    int taskId;
    @ViewById
    TextView tv_name;
    @ViewById
    TextView tv_phone;
    @ViewById
    TextView tv_idCard, tv_videoname, tv_picname, tv_remarksName, tv_safeName, tv_stateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_search_detail);
        setTitleName("任务详情");
        getData();
    }

    private void getData() {
        Call<WatcherTaskResult> call = apiService.getTaskById(new TaskParam(taskId));
        call.enqueue(new MyCallback<WatcherTaskResult>() {
            @Override
            public void onSuccessRequest(WatcherTaskResult result) {
                setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<WatcherTaskResult> call, Throwable t) {

            }
        });
    }

    private void setData(WatcherTaskBean message) {
        tv_idCard.setText(message.getMonitored().getMonitoredIdcard());
        tv_name.setText(message.getMonitored().getMonitoredRealname());
        tv_phone.setText(message.getMonitored().getMonitoredPhone());
        stateData.addAll(message.getStatusList());
        safeLeveAdapter.notifyDataSetChanged();
        stateAdpter.notifyDataSetChanged();
        remarkAdapter.notifyDataSetChanged();
        List<StatusListBean> statusListBeans = message.getStatusList();
        if (statusListBeans.size() == 0) {
            tv_remarksName.setVisibility(View.GONE);
            tv_safeName.setVisibility(View.GONE);
            tv_picname.setVisibility(View.GONE);
            tv_stateName.setVisibility(View.GONE);
            tv_videoname.setVisibility(View.GONE);

            return;
        }

        for (int i = 0; i < statusListBeans.size(); i++) {
            if (statusListBeans.get(i).getImageUrl().contains(".")) {
                String[] path = statusListBeans.get(i).getImageUrl().split(",");
                for (int j = 0; j < path.length; j++) {
                    LocalMedia media = new LocalMedia();
                    media.setPath(Mate.PIC_PATH + path[j]);
                    picData.add(media);
                }
                picAdapter.notifyDataSetChanged();
            }

            if (statusListBeans.get(i).getVideoUrl().contains(".")) {
                String[] path = statusListBeans.get(i).getVideoUrl().split(",");
                for (int j = 0; j < path.length; j++) {
                    LocalMedia media = new LocalMedia();
                    media.setPath(Mate.PIC_PATH + path[j]);
                    videoData.add(media);

                }
                videoAdapter.notifyDataSetChanged();
            }
        }
        if (picData.size() != 0) {
            tv_picname.setVisibility(View.VISIBLE);
        }else {
            tv_picname.setVisibility(View.GONE);

        }
        if (videoData.size() != 0) {
            tv_videoname.setVisibility(View.VISIBLE);
        }else {
            tv_videoname.setVisibility(View.GONE);
        }
    }

    @AfterViews
    void initViews() {
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
                VideoPlayActivity_.intent(TaskSearchDetailActivity.this).path(videoData.get(position).getPath()).start();

            }
        });
    }
}
