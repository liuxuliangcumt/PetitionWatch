package com.realpower.petitionwatch.modelstaff.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.DealParam;
import com.realpower.petitionwatch.net.param.IdParam;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.SuggestionDetailResult;
import com.realpower.petitionwatch.modelstaff.bean.SuggestionDetailBean;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.CustomDialog;
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
public class SuggestionDetailActivity extends BaseActivity {

    @Extra
    int suggestionId;

    @ViewById
    AppCompatTextView tv_number, tv_name, tv_idCard,
            tv_location, tv_phone, tv_urge, tv_detail, tv_result, tv_voicename, tv_videoname, tv_picname;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_detail);
    }

    @AfterViews
    void onInitViews() {
        setTitleName("意见详情");
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        gv_pic.setAdapter(picAdapter);
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoData);
        lv_video.setAdapter(videoAdapter);
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(this, voiceData);
        lv_voice.setAdapter(voiceAdapter);
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        voiceAdapter.isDetail = true;
        getData();
    }

    private void getData() {
        Call<SuggestionDetailResult> call = apiService.suggestionDetail(new IdParam(suggestionId));
        call.enqueue(new MyCallback<SuggestionDetailResult>() {
            @Override
            public void onSuccessRequest(SuggestionDetailResult result) {
                setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SuggestionDetailResult> call, Throwable t) {

            }
        });
    }

    private String moniterId;

    private void setData(SuggestionDetailBean message) {
        tv_number.setText("NO." + message.getSuggestionNum());
        tv_name.setText(message.getMonitoredName());
        tv_detail.setText(message.getSuggestionInfo());
        tv_location.setText(message.getSuggestionAreaName());
        tv_phone.setText(message.getSuggestionContactInformation());
        tv_urge.setText(message.getReminders() + " ;" + message.getLasttime());
        tv_idCard.setText(message.getCard());
        if ("暂无".equals(message.getSuggestionImageUrl())) {
            tv_picname.setVisibility(View.GONE);
            gv_pic.setVisibility(View.GONE);
        } else {
            String[] path = message.getSuggestionImageUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                picData.add(Mate.PIC_PATH + path[i]);
            }
            picAdapter.notifyDataSetChanged();
        }
        if ("暂无".equals(message.getSuggestionVideoUrl())) {
            lv_video.setVisibility(View.GONE);
            tv_videoname.setVisibility(View.GONE);
        } else {
            String[] path = message.getSuggestionVideoUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                videoData.add(Mate.PIC_PATH + path[i]);
            }
            videoAdapter.notifyDataSetChanged();
        }

        if ("暂无".equals(message.getSuggestionVoiceUrl())) {
            lv_voice.setVisibility(View.GONE);
            tv_voicename.setVisibility(View.GONE);
        } else {
            String[] path = message.getSuggestionVoiceUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                VoiceBean bean = new VoiceBean();
                bean.setPath(Mate.PIC_PATH + path[i]);
                voiceData.add(bean);
            }
            voiceAdapter.notifyDataSetChanged();
        }
        moniterId = message.getMonitoredId() + "";
        if (0!=message.getSuggestionResult().length()){
            tv_result.setText(message.getSuggestionResult());
        }else {
            tv_result.setVisibility(View.GONE);
            findViewById(R.id.tv_resultName).setVisibility(View.GONE);
        }

        if (message.getCurrentStatus() == 1) {
            findViewById(R.id.btn_acount).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.btn_acount).setVisibility(View.GONE);
        }
    }

    @Click({R.id.btn_acount})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acount:
                showDeelDailog();
                break;
        }
    }

    private void showDeelDailog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_deel);
        dialog.show();
        dialog.getWindow().setWindowAnimations(R.style.customDialog);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.8);
        layoutParams.height = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.8);
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv_cancel = (TextView) dialog.getCustomView().findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) dialog.getCustomView().findViewById(R.id.tv_ok);
        final EditText et_content = (EditText) dialog.getCustomView().findViewById(R.id.et_content);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString();
                if (content.trim().length() != 0) {
                    deelingPitition(content, dialog);
                } else {
                    MyToastUtils.showToast("请输入反馈内容");
                }
            }
        });
    }

    private void deelingPitition(String content, final CustomDialog dialog) {

        showMyDialog("请稍后");
        List<String> stringList = new ArrayList<>();
        stringList.add(suggestionId + "");
        Call<StringResult> call = apiService.suggestionFeedback(new DealParam(stringList, content));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        MyToastUtils.showToast(result.getDesc().getDescription());
                        dialog.dismiss();
                        findViewById(R.id.btn_acount).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }
}
