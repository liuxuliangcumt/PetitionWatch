package com.realpower.petitionwatch.modelstaff.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.activity.VideoPlayActivity_;
import com.realpower.petitionwatch.modelcounty.activity.AlarmDetailActivity;
import com.realpower.petitionwatch.modelwatch.listtener.VoicePlayClickListener;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.DealParam;
import com.realpower.petitionwatch.net.param.IdParam;
import com.realpower.petitionwatch.net.result.PetitionDetailResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.modelstaff.bean.PetitionDetailBean;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.view.CustomGridVeiw;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VideoAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VoiceAdapter;
import com.realpower.petitionwatch.modelwatch.bean.VoiceBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class PetitionDetailActivity extends BaseActivity {
    @Extra
    int appealId;

    @ViewById
    AppCompatTextView tv_number, tv_name, tv_idCard, tv_jointers,
            tv_location, tv_category, tv_phone, tv_urge, tv_detail, tv_voicename, tv_videoname, tv_picname;
    @ViewById(R.id.gv_pic)
    CustomGridVeiw gv_pic;

    @ViewById(R.id.lv_video)
    CustomListView lv_video;

    @ViewById(R.id.lv_voice)
    CustomListView lv_voice;
    private PicAdapter picAdapter;
    private List<String> picData;
    private VoiceAdapter voiceAdapter;
    private List<VoiceBean> voiceData;
    private List<String> videoData;
    private VideoAdapter videoAdapter;
    @ViewById
    AppCompatButton btn_acount, btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition_detail);
    }

    @AfterViews
    void onInitViews() {
        setTitleName("诉求详情");
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        gv_pic.setAdapter(picAdapter);
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoData);
        lv_video.setAdapter(videoAdapter);
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(this, voiceData);
        lv_voice.setAdapter(voiceAdapter);
        lv_voice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
                    VoicePlayClickListener.currentPlayListener.stopPlayVoice();
                }
                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice), PetitionDetailActivity.this).onClick(view);
            }
        });
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoPlayActivity_.intent(PetitionDetailActivity.this).path(videoData.get(position)).start();
            }
        });
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        voiceAdapter.isDetail = true;
        getDetail();
    }

    private void getDetail() {
        Call<PetitionDetailResult> call = apiService.appealDetail(new IdParam(appealId));
        call.enqueue(new MyCallback<PetitionDetailResult>() {
            @Override
            public void onSuccessRequest(PetitionDetailResult result) {
                setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<PetitionDetailResult> call, Throwable t) {

            }
        });
    }

    private void setData(PetitionDetailBean message) {
        tv_number.setText("NO." + message.getAppealNum());
        tv_name.setText(message.getMonitoredName());
        tv_category.setText(message.getCategoryName());
        tv_detail.setText(message.getAppealInfo());
        tv_location.setText(message.getAreaName());
        tv_phone.setText(message.getAppealContactInformation());
        tv_urge.setText(message.getReminders() + " ;" + message.getLasttime());
        tv_jointers.setText(message.getAssociateNames());
        tv_idCard.setText(message.getCard());
        if ("暂无".equals(message.getAppealImageUrl())) {
            tv_picname.setVisibility(View.GONE);
            gv_pic.setVisibility(View.GONE);
        } else {
            String[] path = message.getAppealImageUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                picData.add(Mate.PIC_PATH + path[i]);
            }
            picAdapter.notifyDataSetChanged();
        }
        if ("暂无".equals(message.getAppealVideoUrl())) {
            lv_video.setVisibility(View.GONE);
            tv_videoname.setVisibility(View.GONE);
        } else {
            String[] path = message.getAppealVideoUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                videoData.add(Mate.PIC_PATH + path[i]);
            }
            videoAdapter.notifyDataSetChanged();
        }

        if ("暂无".equals(message.getAppealVoiceUrl())) {
            lv_voice.setVisibility(View.GONE);
            tv_voicename.setVisibility(View.GONE);
        } else {
            String[] path = message.getAppealVoiceUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                VoiceBean bean = new VoiceBean();
                bean.setPath(Mate.PIC_PATH + path[i]);
                voiceData.add(bean);
            }
            voiceAdapter.notifyDataSetChanged();
        }
        moniterId = message.getMonitoredId();
        category = message.getAppealCategory() + "";
        if ("1".equals(message.getIsSerious())) {//在重点人库
            btn_add.setVisibility(View.GONE);
        }
        setStatus(message.getCurrentStatus());
    }

    private int currentStatus = 0;

    private void setStatus(int currentStatus) {
        this.currentStatus = currentStatus;
        switch (currentStatus) {
            case 1:
                btn_acount.setText("开始处理");
                break;
            case 2:
                btn_acount.setText("正在处理");
                break;
            case 3:
                btn_acount.setText("已处理");
                btn_acount.setClickable(false);
                break;
        }
    }

    private String moniterId;
    private String category;

    @Click({R.id.btn_acount, R.id.btn_add})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acount:
                if (currentStatus == 1) {
                    deelingPitition();
                } else {
                    endPitition();
                }

                break;
            case R.id.btn_add:
                addToSerious();
                break;
        }
    }

    private void endPitition() {
        List<String> stringList = new ArrayList<>();
        stringList.add(appealId + "");
        Call<StringResult> call = apiService.endDeal(new DealParam(stringList));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        MyToastUtils.showToast("更新状态成功");
                        setStatus(3);
                    }
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

    private void deelingPitition() {
        List<String> stringList = new ArrayList<>();
        stringList.add(appealId + "");
        Call<StringResult> call = apiService.startDeal(new DealParam(stringList));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        MyToastUtils.showToast("更新状态成功");
                        setStatus(2);
                    }
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

    private void addToSerious() {
        List<String> stringList = new ArrayList<>();
        stringList.add(moniterId);
        Call<StringResult> call = apiService.addToSerious(new DealParam(stringList, category));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {

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
