package com.realpower.petitionwatch.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.KeyPersonPicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.IdParam;
import com.realpower.petitionwatch.net.result.MonitoredResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.UpLoadResutlText;
import com.realpower.petitionwatch.util.IdCardVerifyUtil;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.PathUtil;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.CustomDialog;
import com.realpower.petitionwatch.view.CustomGridVeiw;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.view.addressview.PickAddressInterface;
import com.realpower.petitionwatch.view.addressview.PickAddressView;
import com.realpower.petitionwatch.modelwatch.adapter.RelativeAdapter;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class KeypersonEditActivity extends BaseActivity {
    private List<MonitoredBean.RelativesBean> relativesBeans;
    private RelativeAdapter relativeAdapter;
    /* @ViewById(R.id.lv_kins)
     CustomListView lv_kins;*/
    @Extra
    MonitoredBean bean;
    List<ClearEditText> dataHolder;
    @ViewById
    ClearEditText et_carType, et_carNum, et_carColor, et_addressDetail;

    @ViewById
    TextView tv_idCard, tv_name;

    @ViewById
    LinearLayout ll_addkins;
    @ViewById
    TextView tv_address, tv_picLoad;
    private String areaID = "";
    @ViewById
    TextView tv_phone;
    @ViewById
    CustomGridVeiw GV_pic, GV_kins;

    @ViewById
    TextView tv_kins;

    @ViewById
    LinearLayout ll_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyperson_edit);
    }

    @AfterViews
    void initViews() {
        setTitleName("编辑重点人");
        relativesBeans = new ArrayList<>();
        relativeAdapter = new RelativeAdapter(this, relativesBeans);
        relativeAdapter.isDetail = true;
        GV_kins.setAdapter(relativeAdapter);
        GV_kins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 > relativesBeans.size()) {
                    //添加亲属
                    showAddKinsDialog();
                } else {
                    showKinsDialog(relativesBeans.get(position));
                }
            }
        });
        dataHolder = new ArrayList<>();
        dataHolder.add(et_carType);
        dataHolder.add(et_carNum);
        dataHolder.add(et_carColor);
        dataHolder.add(et_addressDetail);
        picData = new ArrayList<>();
        picAdapter = new KeyPersonPicAdapter(this, picData);
        picAdapter.isDetail = true;
        GV_pic.setAdapter(picAdapter);
        GV_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 > picData.size()) {
                    getFile(1);
                } else {
                    //TODO
                    ImageVideoActivity_.intent(KeypersonEditActivity.this).mediaList(picData).position(position).start();
                }
            }
        });
        picAdapter.setOnDeleteClickLisnter(new KeyPersonPicAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                picData.remove(position);
                picAdapter.notifyDataSetChanged();
            }
        });

        getData();
    }


    private void showAddressDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_chose_address);
        dialog.show();
        dialog.getWindow().setWindowAnimations(R.style.DialogBottom);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        PickAddressView pickView = (PickAddressView) dialog.getCustomView().findViewById(R.id.pickView);
        pickView.setOnTopClicklistener(new PickAddressInterface() {
            @Override
            public void onOkClick(String name, String areaId) {
                tv_address.setText(name);
                areaID = areaId;
                dialog.dismiss();
            }

            @Override
            public void onCancelClick() {
                dialog.dismiss();
            }
        });

    }

    private void getData() {
        Call<MonitoredResult> call = apiService.getMonitoredById(new IdParam(bean.getMonitoredId()));
        call.enqueue(new MyCallback<MonitoredResult>() {
            @Override
            public void onSuccessRequest(MonitoredResult result) {
                setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<MonitoredResult> call, Throwable t) {

            }
        });
    }

    private void setData(MonitoredBean message) {
        relativesBeans.clear();
        picData.clear();

        ll_address.setClickable(false);
        tv_address.setText(message.getPermanentAddress());
        et_addressDetail.setText(message.getMonitoredCurrentaddress());
        tv_phone.setText(message.getMonitoredPhone());
        tv_idCard.setText(message.getMonitoredIdcard());
        tv_name.setText(message.getMonitoredRealname());
        if (message.getCars().size() != 0) {
            et_carColor.setText(message.getCars().get(0).getColor());
            et_carNum.setText(message.getCars().get(0).getNum());
            et_carType.setText(message.getCars().get(0).getModel());
        }
        for (int i = 0; i < dataHolder.size(); i++) {
            dataHolder.get(i).setEnabled(false);
        }
        if (message.getRelatives().size() != 0) {
            relativesBeans.addAll(message.getRelatives());
            relativeAdapter.notifyDataSetChanged();
            GV_kins.setVisibility(View.VISIBLE);
            tv_kins.setVisibility(View.VISIBLE);
        } else {
            GV_kins.setVisibility(View.GONE);
            tv_kins.setVisibility(View.GONE);
        }
        if (!"暂无".equals(message.getMonitoredLastimage())) {
            String pic[] = message.getMonitoredLastimage().split(",");
            for (int i = 0; i < pic.length; i++) {
                LocalMedia media = new LocalMedia();
                media.setPath(Mate.PIC_PATH + pic[i]);
                media.setMimeType(1);
                picData.add(media);
            }
            picAdapter.notifyDataSetChanged();
        }
        bean = message;
    }

    @ViewById
    AppCompatButton btn_edit;
    private boolean isEditting;
    @ViewById
    LinearLayout ll_address;

    @Click({R.id.btn_edit, R.id.ll_address})
    void onVeiwClick(View view) {
        switch (view.getId()) {

            case R.id.btn_edit:
                if (isEditting) {
                    beforSave();
                } else {
                    startEdit();
                }
                break;
            case R.id.ll_address:
                showAddressDialog();
                break;

        }
    }

    private void beforSave() {
        ll_address.setClickable(false);
        if (!TextUtils.isEmpty(et_carColor.getText()) || !TextUtils.isEmpty(et_carNum.getText())
                || !TextUtils.isEmpty(et_carType.getText())) {
            Integer carId = 0 == bean.getCars().size() ? null : bean.getCars().get(0).getId();
            List<MonitoredBean.CarsBean> carsBeanList = new ArrayList<>();
            carsBeanList.add(new MonitoredBean.CarsBean(et_carColor.getText().toString(),
                    et_carType.getText().toString(),
                    et_carNum.getText().toString(), carId));
            bean.setCars(carsBeanList);


        }

        bean.setRelatives(relativesBeans);
        bean.setMonitoredAddress(et_addressDetail.getText().toString() + "");
        if (areaID.length() != 0) {
            bean.setAreaId(Integer.parseInt(areaID));
        }
        afterVenifyIdCard();
    }

    private boolean picUpload;

    void afterVenifyIdCard() {
        for (int i = 0; i < picData.size(); i++) {
            if (picData.get(i).isChecked()) {
                picUpload = true;
                break;
            }
        }
        needUploadFile();
    }

    private void needUploadFile() {
        if (picData.size() != 0 && picUpload) {
            showMyDialog("正在上传文件");
            upLoadFile(picData, 1);
            return;
        }
        startSave();
       /* if (!picUpload) {

        }*/
    }

    private void startSave() {
        Call<StringResult> call = apiService.updateMonitored(bean);
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    if ("2".equals(result.getDesc().getCode())) {
                        MyToastUtils.showToast(result.getDesc().getDescription());
                        //ll_addkins.setVisibility(View.GONE);
                        //ll_pic.setVisibility(View.GONE);

                        endEdit();

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

    HttpUtils httpUtils;
    private String picPath = "";

    /**
     * @param paths 上传文件路径
     * @param type  文件类型 1 图片  2 视频 3 语音
     */
    private void upLoadFile(List<LocalMedia> paths, final int type) {

        //  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("enctype", "multipart/form-data");
        params.addHeader("Authorization", myPrefs.token().get());
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).isChecked()) {
                File file = new File(paths.get(i).getPath());
                params.addBodyParameter(file.getName(), file);
            }

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
                            if ("暂无".equals(bean.getMonitoredLastimage())) {
                                bean.setMonitoredLastimage(picPath);
                            } else {
                                bean.setMonitoredLastimage(bean.getMonitoredLastimage() + "," + picPath);
                            }
                            Log.e("aaa", "图片上传成功  " + picPath);
                            picUpload = false;
                            break;
                    }
                    needUploadFile();
                }
                hideDialog();
            }

            @Override
            public void onFailure(HttpException e, String msg) {
                Log.e("aaa", "文件上传失败 " + msg + "   " + e.getMessage() + "   " + e.getExceptionCode());
                hideDialog();
                MyToastUtils.showToast("文件上传失败");
            }
        });
    }

    private void startEdit() {
        for (int i = 0; i < dataHolder.size(); i++) {
            dataHolder.get(i).setEnabled(true);
            dataHolder.get(i).setFocusable(true);
            dataHolder.get(i).setFocusableInTouchMode(true);
        }

        picAdapter.isDetail = false;
        picAdapter.notifyDataSetChanged();
        // findViewById(R.id.btn_pic).setVisibility(View.VISIBLE);
        btn_edit.setText("保存并确认");
        isEditting = true;
        relativeAdapter.isDetail = false;
        ll_address.setClickable(true);
        tv_kins.setVisibility(View.VISIBLE);
        GV_kins.setVisibility(View.VISIBLE);
    }

    private void endEdit() {
        for (int i = 0; i < dataHolder.size(); i++) {
            dataHolder.get(i).setEnabled(false);
            dataHolder.get(i).setFocusable(false);
        }
        btn_edit.setText("信息修改");
        picAdapter.isDetail = true;
        relativeAdapter.isDetail = true;
        picAdapter.notifyDataSetChanged();
        relativeAdapter.notifyDataSetChanged();
        isEditting = false;
        ll_address.setClickable(false);
        getData();
    }

    private void showKinsDialog(MonitoredBean.RelativesBean relativesBean) {
        CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_kins);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.9);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv_name = dialog.getCustomView().findViewById(R.id.tv_name);
        TextView tv_relatition = dialog.getCustomView().findViewById(R.id.tv_relatition);
        TextView tv_number = dialog.getCustomView().findViewById(R.id.tv_number);
        TextView tv_phone = dialog.getCustomView().findViewById(R.id.tv_phone);
        tv_name.setText(relativesBean.getRealname());
        tv_number.setText(relativesBean.getIdcard());
        tv_phone.setText(relativesBean.getPhone());
        tv_relatition.setText(relativesBean.getRelationship());
    }

    private void showAddKinsDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_addkins);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.9);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv_cancel = (TextView) dialog.getCustomView().findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) dialog.getCustomView().findViewById(R.id.tv_ok);
        final ClearEditText et_name = (ClearEditText) dialog.getCustomView().findViewById(R.id.et_name);
        final ClearEditText et_relation = (ClearEditText) dialog.getCustomView().findViewById(R.id.et_relation);
        final ClearEditText et_number = (ClearEditText) dialog.getCustomView().findViewById(R.id.et_number);
        final ClearEditText et_phone = (ClearEditText) dialog.getCustomView().findViewById(R.id.et_phone);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().toString().trim().length() == 0) {
                    MyToastUtils.showToast("请输入姓名");
                    return;
                }
                if (et_number.getText().toString().trim().length() != 18) {
                    MyToastUtils.showToast("请输入身份证号");
                    return;
                }
                if (et_phone.getText().toString().trim().length() != 11) {
                    MyToastUtils.showToast("请输入正确手机号");
                    return;
                }
                if (et_relation.getText().toString().trim().length() == 0) {
                    MyToastUtils.showToast("请输入关系");
                    return;
                }
                MonitoredBean.RelativesBean relativesBean =
                        new MonitoredBean.RelativesBean(et_number.getText().toString(),
                                et_phone.getText().toString(), et_name.getText().toString()
                                , et_relation.getText().toString());
                venifyId(relativesBean, dialog);

            }
        });

    }

    @Background
    public void venifyId(MonitoredBean.RelativesBean relativesBean, CustomDialog dialog) {

        IdCardVerifyUtil idCardVerifyUtil = new IdCardVerifyUtil();
        String js = idCardVerifyUtil.idcard_verify(relativesBean.getRealname(), relativesBean.getIdcard());
        //{"data":{"code":"1108","message":"异常"},"status":"2005"}

        try {
            JSONObject jsonObject = new JSONObject(js);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String status = jsonObject.getString("status");
            String code = dataObject.getString("code");
            if ("1000".equals(code)) {
                addRelative(relativesBean, dialog);
            } else  {
                MyToastUtils.showToast("身份号码验证未通过");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @UiThread
    public void addRelative(MonitoredBean.RelativesBean relativesBean, CustomDialog dialog) {
        relativesBeans.add(relativesBean);
        relativeAdapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    private ArrayList<LocalMedia> picData;
    KeyPersonPicAdapter picAdapter;

    private void getFile(int i) {

        int selectNum = 0;
        switch (i) {
            case 1:
                selectNum = 9 - picData.size();
                break;

        }
        PictureSelector.create(this).openGallery(i)
                .maxSelectNum(selectNum)
                .forResult(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (requestCode == 1) {//  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
                picData.add(PathUtil.getRealPathFromUri(this, uri));
                Log.e("aaa", "onActivityRESULT  " + PathUtil.getRealPathFromUri(this, uri));
                picAdapter.notifyDataSetChanged();
            }
        }*/
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1://tupian
                    List<LocalMedia> media = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i < media.size(); i++) {
                        media.get(i).setChecked(true);
                    }
                    picData.addAll(media);
                    picAdapter.notifyDataSetChanged();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
