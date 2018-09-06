package com.realpower.petitionwatch.modelwatch.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.TaskIdParam;
import com.realpower.petitionwatch.net.param.TaskStateChangeParam;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.UpLoadResutlText;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.PathUtil;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.CustomDialog;
import com.realpower.petitionwatch.view.CustomGridVeiw;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.VideoAdapter;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

//任务状态上报
@EActivity
public class TaskBackActivity extends BaseActivity {

    @Extra
    WatcherTaskBean bean;
    @ViewById(R.id.GV_pic)
    CustomGridVeiw GV_pic;
    @ViewById(R.id.lv_video)
    CustomListView lv_video;
    private List<String> videoData;
    private List<String> picData;
    PicAdapter picAdapter;
    VideoAdapter videoAdapter;
    @ViewById
    Spinner sp_state, sp_safe;
    SpinnerAdapter stateAdapter;
    SpinnerAdapter safeAdapter;
    @ViewById
    TextView tv_name;
    private boolean picUpload, videoUpload;
    private String videoPath, picPath = "";
    private int level = 1, status = 1;
    @ViewById
    ClearEditText et_description;
    @ViewById
    AppCompatButton btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_back);
    }

    @AfterViews
    void initViews() {
        setTitleName("状态上报");
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        GV_pic.setAdapter(picAdapter);
        picAdapter.setOnDeleteClickLisnter(new PicAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                picData.remove(position);
                picAdapter.notifyDataSetChanged();
            }
        });
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoData);
        lv_video.setAdapter(videoAdapter);
        videoAdapter.setOnDeleteClickLisnter(new VideoAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                videoData.remove(position);
                videoAdapter.notifyDataSetChanged();
            }
        });
        List<String> stateData = new ArrayList<>();
        stateData.add("外出");
        stateData.add("在家");
        List<String> safeData = new ArrayList<>();
        safeData.add("安全");
        safeData.add("危险");
        stateAdapter = new SpinnerAdapter(stateData, this);
        safeAdapter = new SpinnerAdapter(safeData, this);
        sp_state.setAdapter(stateAdapter);
        sp_safe.setAdapter(safeAdapter);
        tv_name.setText(bean.getMonitored().getMonitoredRealname());
        sp_safe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                level = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bean.getStatus() != 1) {
            findViewById(R.id.btn_start).setVisibility(View.GONE);
            findViewById(R.id.btn_end).setVisibility(View.GONE);
        }

    }

    @Click({R.id.btn_end, R.id.btn_video, R.id.btn_pic, R.id.btn_start})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_end:
                showEndDialog();
                break;
            case R.id.btn_video:
                getFile(0);
                break;
            case R.id.btn_pic:
                getFile(1);
                break;
            case R.id.btn_start:
                beforStart();
                break;
        }
    }

    private void beforStart() {
        picUpload = picData.size() == 0 ? false : true;
        videoUpload = videoData.size() == 0 ? false : true;
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

        if (!picUpload && !videoUpload) {
            updateTaskStatus();
        }
    }

    private void updateTaskStatus() {
        Call<StringResult> call = apiService.updateTaskStatus(new TaskStateChangeParam(et_description.getText().toString(),
                level, status, bean.getTaskId(), picPath, videoPath));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("3".equals(result.getDesc().getCode())) {
                        MyToastUtils.showToast(result.getDesc().getDescription());
                        finish();
                    } else {
                        MyToastUtils.showToast(result.getDesc().getDescription());
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

    HttpUtils httpUtils;

    /**
     * @param paths 上传文件路径
     * @param type  文件类型 1 图片  2 视频 3 语音
     */
    private void upLoadFile(List<String> paths, final int type) {
        //  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("enctype", "multipart/form-data");
        params.addHeader("Authorization", myPrefs.token().get());
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
        httpUtils.send(HttpRequest.HttpMethod.POST, Mate.TASK_UPLOAD, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson gson = new Gson();
                UpLoadResutlText result = gson.fromJson(responseInfo.result, UpLoadResutlText.class);
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

                    }
                    needUploadFile();
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

    private void showEndDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_taskend);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.80);
        layoutParams.height = layoutParams.width;
        dialog.getWindow().setAttributes(layoutParams);
        final Spinner spinner = (Spinner) dialog.getCustomView().findViewById(R.id.sp_state);
        List<String> data = new ArrayList<>();
        data.add("提前结束");
        data.add("正常结束");
        SpinnerAdapter adapter = new SpinnerAdapter(data, this);
        spinner.setAdapter(adapter);
        TextView tv_cancel = (TextView) dialog.getCustomView().findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) dialog.getCustomView().findViewById(R.id.tv_ok);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (0 == spinner.getSelectedItemPosition()) {
                    //提前结束
                    endTaskEarly();
                } else {
                    //正常结束
                    endTaskNormal();
                }
                dialog.dismiss();
            }
        });
    }

    private void endTaskNormal() {
        Call<StringResult> call = apiService.endTaskNormal(new TaskIdParam(bean.getTaskId()));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("已结束");
                    finish();
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

    private void endTaskEarly() {
        Call<StringResult> call = apiService.endTaskEarly(new TaskIdParam(bean.getTaskId()));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("已结束");
                    finish();
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
            // showVoiceDialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (requestCode == 1) {//  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
                picData.add(PathUtil.getRealPathFromUri(this, uri));
                Log.e("aaa", "onActivityRESULT  " + PathUtil.getRealPathFromUri(this, uri));
                picAdapter.notifyDataSetChanged();

            } else if (requestCode == 0) {
                Log.e("aaa", "shi视频  " + uri.getPath());
                videoData.add(PathUtil.getRealPathFromUri(this, uri));
                videoAdapter.notifyDataSetChanged();
            } else if (requestCode == 2) {
                Log.e("aaa", "音频  " + uri.getPath());

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
