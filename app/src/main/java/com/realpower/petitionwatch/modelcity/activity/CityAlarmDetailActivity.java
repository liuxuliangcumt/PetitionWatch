package com.realpower.petitionwatch.modelcity.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.activity.VideoPlayActivity_;
import com.realpower.petitionwatch.modelcounty.bean.AlarmDetailBean;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VideoAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VoiceAdapter;
import com.realpower.petitionwatch.modelwatch.bean.VoiceBean;
import com.realpower.petitionwatch.modelwatch.listtener.VoicePlayClickListener;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.StringIdParam;
import com.realpower.petitionwatch.net.result.AlarmDetailResult;
import com.realpower.petitionwatch.net.result.StringResult;
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
public class CityAlarmDetailActivity extends BaseActivity {
    @Extra
    String id;
    @ViewById
    TextView tv_name, tv_monitor,
            tv_address, tv_alarmDetail, tv_picname, tv_videoname, tv_voicename;
    @ViewById
    AppCompatButton btn_feedback;

    @ViewById
    CustomGridVeiw gv_pic;
    @ViewById
    CustomListView lv_video, lv_voice;
    private List<String> picData;
    private List<String> videoData;
    private PicAdapter picAdapter;
    private VideoAdapter videoAdapter;
    private VoiceAdapter voiceAdapter;
    private List<VoiceBean> voiceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_alarm_detail);
        setTitleName("一键告警详情");
    }

    @AfterViews
    void onInitView() {
        picData = new ArrayList<>();
        videoData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        videoAdapter = new VideoAdapter(this, videoData);
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        gv_pic.setAdapter(picAdapter);
        lv_video.setAdapter(videoAdapter);
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(this, voiceData);
        lv_voice.setAdapter(voiceAdapter);
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        voiceAdapter.isDetail = true;
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoPlayActivity_.intent(CityAlarmDetailActivity.this).path(videoData.get(position)).start();
            }
        });
        lv_voice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
                    VoicePlayClickListener.currentPlayListener.stopPlayVoice();
                }
                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice), CityAlarmDetailActivity.this).onClick(view);
            }
        });
        getData();
    }

    private void getData() {
        Call<AlarmDetailResult> call = apiService.alarmDetail(new StringIdParam(id));
        call.enqueue(new MyCallback<AlarmDetailResult>() {
            @Override
            public void onSuccessRequest(AlarmDetailResult result) {
                if ("1".equals(result.getStatus()))
                    setData(result.getMessage());
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
        tv_alarmDetail.setText(message.getTime());
        tv_monitor.setText(message.getMonitoredNames());
        if (1 == message.getCommitStatus()) {
            btn_feedback.setClickable(false);
            btn_feedback.setText("已上报");
        }
        if (message.getSupervisorAlarm() != null) {
            tv_alarmDetail.setText(message.getSupervisorAlarm().getDescription() + "");
            if (message.getSupervisorAlarm().getImageUrl().length() > 10) {
                String[] path = message.getSupervisorAlarm().getImageUrl().split(",");
                for (int j = 0; j < path.length; j++) {
                    picData.add(Mate.PIC_PATH + path[j]);
                }
                picAdapter.notifyDataSetChanged();
            } else {
                tv_picname.setVisibility(View.GONE);
            }
            if (message.getSupervisorAlarm().getVideoUrl().length() > 10) {
                String[] path = message.getSupervisorAlarm().getVideoUrl().split(",");
                for (int j = 0; j < path.length; j++) {
                    videoData.add(Mate.PIC_PATH + path[j]);
                }
                videoAdapter.notifyDataSetChanged();
            } else {
                tv_videoname.setVisibility(View.GONE);
            }
            if (message.getSupervisorAlarm().getVoiceUrl().length() > 10) {
                String[] path = message.getSupervisorAlarm().getVoiceUrl().split(",");
                for (int i = 0; i < path.length; i++) {
                    VoiceBean bean = new VoiceBean();
                    bean.setDetail(true);
                    bean.setLoadPath(path[i]);
                    voiceData.add(bean);
                }
                voiceAdapter.notifyDataSetChanged();
            } else {
                tv_voicename.setVisibility(View.GONE);
            }
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

    @Override
    protected void onPause() {
        super.onPause();
        if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
            VoicePlayClickListener.currentPlayListener.stopPlayVoice();
        }
    }

    @Override
    public void onBackPressed() {
        if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
            VoicePlayClickListener.currentPlayListener.stopPlayVoice();
        }
        super.onBackPressed();
    }
}
