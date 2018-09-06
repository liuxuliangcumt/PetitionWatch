package com.realpower.petitionwatch.modelwatch.fragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.fragment.BaseFragment;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.AlarmParam;
import com.realpower.petitionwatch.net.result.AlarmBeanResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.UpLoadResutlText;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.PathUtil;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.util.record.EaseVoiceRecorderView;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.CustomDialog;
import com.realpower.petitionwatch.view.CustomGridVeiw;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.modelwatch.activity.ChoosePersonActivity_;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VideoAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VoiceAdapter;
import com.realpower.petitionwatch.modelwatch.bean.AlarmBean;
import com.realpower.petitionwatch.modelwatch.bean.VoiceBean;
import com.realpower.petitionwatch.modelwatch.listtener.VoicePlayClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/11/23.
 */
@EFragment(R.layout.fragment_alarm)
public class AlarmFragment extends BaseFragment {
    @ViewById(R.id.GV_pic)
    CustomGridVeiw GV_pic;
    @ViewById(R.id.lv_video)
    CustomListView lv_video;
    @ViewById(R.id.lv_voice)
    CustomListView lv_voice;
    PicAdapter picAdapter;
    VideoAdapter videoAdapter;
    private List<String> videoData;
    private List<String> picData;
    private List<VoiceBean> voiceData;
    VoiceAdapter voiceAdapter;
    private List<AlarmBean> beanList = new ArrayList<>();
    @ViewById
    TextView tv_name;
    @ViewById
    ClearEditText et_description;
    @ViewById
    Spinner sp_state;
    private SpinnerAdapter stateAdapter;

    @AfterViews
    void initViews() {
        setTitleName("报警");
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(getActivity(), picData);
        GV_pic.setAdapter(picAdapter);
        picAdapter.setOnDeleteClickLisnter(new PicAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                picData.remove(position);
                picAdapter.notifyDataSetChanged();
            }
        });
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(getActivity(), videoData);
        lv_video.setAdapter(videoAdapter);
        videoAdapter.setOnDeleteClickLisnter(new VideoAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                videoData.remove(position);
                videoAdapter.notifyDataSetChanged();
            }
        });
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(getActivity(), voiceData);
        lv_voice.setAdapter(voiceAdapter);
        lv_voice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice), getActivity()).onClick(view);
            }
        });
        List<String> stateData = new ArrayList<>();
        stateData.add("状态未知");
        stateData.add("外出离家");
        stateAdapter = new SpinnerAdapter(stateData, getActivity());
        sp_state.setAdapter(stateAdapter);
        getPerson();
    }

    private void getPerson() {
        Call<AlarmBeanResult> call = apiService.preAddAlarm();
        call.enqueue(new MyCallback<AlarmBeanResult>() {
            @Override
            public void onSuccessRequest(AlarmBeanResult result) {
                if ("1".equals(result.getStatus())) {
                    if (result.getMessage() != null && result.getMessage().size() != 0)
                        beanList.add(result.getMessage().get(0));
                    if (beanList.size() != 0) {
                        tv_name.setText(beanList.get(0).getMonitoredRealname());
                    }
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<AlarmBeanResult> call, Throwable t) {

            }
        });
    }

    @Click({R.id.ll_jointer, R.id.btn_video, R.id.btn_voice, R.id.btn_pic, R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_jointer:
                ChoosePersonActivity_.intent(getContext()).startForResult(2);
                break;
            case R.id.btn_video:
                getFile(0);
                break;
            case R.id.btn_voice:
                getFile(2);
                break;
            case R.id.btn_pic:
                getFile(1);
                break;
            case R.id.btn_ok:
                beforAddAlarm();
                break;
        }
    }

    private String videoPath, voicePath, picPath = "";
    private boolean picUpload, voiceUpload, videoUpload;

    private void beforAddAlarm() {
        String describe = et_description.getText().toString().trim();
        if (describe.length() == 0) {
            MyToastUtils.showToast("请输入描述");
            return;
        }
        picUpload = picData.size() == 0 ? false : true;
        videoUpload = videoData.size() == 0 ? false : true;
        voiceUpload = voiceData.size() == 0 ? false : true;
        showMyDialog("请稍后");
        needUploadFile();
    }

    private void needUploadFile() {
        if (picData.size() != 0 && picUpload) {
            upLoadFile(picData, 1);
            return;
        }
        if (videoData.size() != 0 && videoUpload) {
            upLoadFile(videoData, 2);
            return;
        }
        if (voiceData.size() != 0 && voiceUpload) {
            List<String> voiceStringData = new ArrayList<>();
            for (int i = 0; i < voiceData.size(); i++) {
                voiceStringData.add(voiceData.get(i).getPath());
            }
            upLoadFile(voiceStringData, 3);
            return;
        }
        if (!picUpload && !voiceUpload && !videoUpload) {
            addAlarm();
        }
    }

    HttpUtils httpUtils;

    /**
     * @param paths 上传文件路径
     * @param type  文件类型 1 图片  2 视频 3 语音
     */
    private void upLoadFile(List<String> paths, final int type) {
        //  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
        Log.e("aaa", "上传文件    " + myPrefs.token().get());
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("enctype", "multipart/form-data");
        params.addHeader("Authorization", myPrefs.token().get());
        Log.e("aaa", "文件上传  " + myPrefs.token().get());
        for (int i = 0; i < paths.size(); i++) {
            File file = new File(paths.get(i));
            params.addBodyParameter(file.getName(), file);
        }
        if (type == 1) {
            params.addBodyParameter("type", "pic");
        } else if (type == 2) {
            params.addBodyParameter("type", "video");
        } else if (type == 3) {
            params.addBodyParameter("type", "voice");
        }
        httpUtils.send(HttpRequest.HttpMethod.POST, Mate.S_UPLAOD, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson gson = new Gson();
                UpLoadResutlText result = gson.fromJson(responseInfo.result, UpLoadResutlText.class);
                Log.e("aaa", result.toString());
                if ("1".equals(result.getDesc().getCode())) {
                    switch (type) {
                        case 1:
                            picPath = result.getMessage().getUrl();
                            Log.e("aaa", "图片上传成功  " + picPath);
                            picUpload = false;
                            break;
                        case 2:
                            videoPath = result.getMessage().getUrl();
                            videoUpload = false;
                            break;
                        case 3:
                            voicePath = result.getMessage().getUrl();
                            voiceUpload = false;
                            break;
                    }
                    needUploadFile();
                } else {
                    Log.e("aaa", "文件上传失败  " + result.toString());
                    hideDialog();
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }
            }

            @Override
            public void onFailure(HttpException e, String msg) {
                Log.e("aaa", "文件上传失败 " + msg + "   " + e.getMessage() + "   " + e.getExceptionCode());
                hideDialog();
                MyToastUtils.showToast("文件上传失败");
            }

        });

    }

    private void addAlarm() {
        String describe = et_description.getText().toString().trim();
        String monitoredId = "";
        for (int i = 0; i < beanList.size(); i++) {
            if (i != beanList.size() - 1) {
                monitoredId = monitoredId + beanList.get(i).getMonitoredId() + ",";
            } else {
                monitoredId = monitoredId + beanList.get(i).getMonitoredId();
            }
        }

        Call<StringResult> call = apiService.addAlarm(new AlarmParam(describe, monitoredId, sp_state.getSelectedItemPosition() + 1,
                picPath, videoPath, voicePath));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("报警成功");
                    picData.clear();
                    videoData.clear();
                    voiceData.clear();
                    et_description.setText("");
                    picAdapter.notifyDataSetChanged();
                    videoAdapter.notifyDataSetChanged();
                    voiceAdapter.notifyDataSetChanged();
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

    private void getFile(int i) {
        Intent intent = new Intent();
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.setType("image/*"); //选择图片
// intent.setType("audio/*"); //选择音频
// intent1.setType("video/*"); //选择视频类
        if (i == 0) {//获取视频文件
            intent.setType("video/*");
            startActivityForResult(intent, 0);

        } else if (i == 1) {//获取图片
                /* 开启Pictures画面Type设定为image */
            intent.setType("image/*");
                /* 取得相片后返回本画面 */
            startActivityForResult(intent, 1);
        } else {//获取语音
          /*  intent.setType("audio*//*");
            startActivityForResult(intent, 2);*/
            showVoiceDialog();
        }
    }

    private void showVoiceDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.style.customDialog, R.layout.dialog_voice);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(getActivity()));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        LinearLayout ll_speak = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_speak);
        LinearLayout ll_choose = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_choose);
        ll_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                startActivityForResult(intent, 2);
                dialog.dismiss();
            }
        });
        ll_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVoiceInputDialog();
                dialog.dismiss();
            }
        });
    }

    private void showVoiceInputDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.style.customDialog, R.layout.dialog_voice_input);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(getActivity()));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        final EaseVoiceRecorderView recorderView = (EaseVoiceRecorderView) dialog.getCustomView().findViewById(R.id.recorderView);
        ImageView iv_cancel = (ImageView) dialog.getCustomView().findViewById(R.id.iv_cancel);
        Button btn_start = (Button) dialog.getCustomView().findViewById(R.id.btn_start);
        btn_start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                return recorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        Log.e("aaa", "onVoiceRecordComplete录音文件  " + voiceFilePath);
                        VoiceBean voiceBean = new VoiceBean(voiceFilePath, voiceTimeLength + "");
                        voiceData.add(voiceBean);
                        voiceAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public void setChoosedPerson(List<AlarmBean> beans) {
        beanList.clear();
        beanList.addAll(beans);
        String names = "";
        for (int i = 0; i < beanList.size(); i++) {
            names += beanList.get(i).getMonitoredRealname() + " ";
        }
        tv_name.setText(names);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2) {
            List<AlarmBean> beans = (List<AlarmBean>) data.getSerializableExtra("beans");
            Log.e("aaa", "bens.sa  " + beans.size());
            setChoosedPerson(beans);
        }
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (requestCode == 1) {//  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
                picData.add(PathUtil.getRealPathFromUri(getActivity(), uri));
                picAdapter.notifyDataSetChanged();

            } else if (requestCode == 0) {
                Log.e("aaa", "shi视频  " + uri.getPath());
                videoData.add(PathUtil.getRealPathFromUri(getActivity(), uri));
                videoAdapter.notifyDataSetChanged();
            } else if (requestCode == 2) {
                VoiceBean voiceBean = new VoiceBean(PathUtil.getRealPathFromUri(getActivity(), uri),
                        20 + "");
                voiceData.add(voiceBean);
                voiceAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}