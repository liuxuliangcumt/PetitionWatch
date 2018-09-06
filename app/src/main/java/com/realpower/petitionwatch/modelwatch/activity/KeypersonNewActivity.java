package com.realpower.petitionwatch.modelwatch.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.realpower.petitionwatch.modelwatch.adapter.PicAdapter;
import com.realpower.petitionwatch.modelwatch.adapter.RelativeAdapter;
import com.realpower.petitionwatch.modelwatch.bean.MonitoredBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class KeypersonNewActivity extends BaseActivity {
    private List<MonitoredBean.RelativesBean> relativesBeans;
    private RelativeAdapter relativeAdapter;

    @ViewById(R.id.lv_kins)
    CustomListView lv_kins;
    @ViewById
    ClearEditText et_name, et_idCard, et_phone, et_carType, et_carNum, et_carColor, et_addressDetail;
    @ViewById
    TextView tv_address;
    private MonitoredBean bean = new MonitoredBean();
    private List<String> picData;
    PicAdapter picAdapter;
    @ViewById
    CustomGridVeiw GV_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyperson_new);
    }

    @AfterViews
    void initViews() {
        setTitleName("新建重点人");
        relativesBeans = new ArrayList<>();
        relativeAdapter = new RelativeAdapter(this, relativesBeans);
        lv_kins.setAdapter(relativeAdapter);
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        GV_pic.setAdapter(picAdapter);
    }

    @Click({R.id.ll_addkins, R.id.btn_ok, R.id.ll_address, R.id.btn_pic})
    void onVeiwClick(View view) {
        switch (view.getId()) {
            case R.id.ll_addkins:
                showAddKinsDialog();
                break;
            case R.id.btn_ok:
                beforCreate();
                break;
            case R.id.ll_address:
                showAddressDialog();
                break;
            case R.id.btn_pic:
                Intent intent = new Intent();
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
                break;
        }
    }

    private void beforCreate() {
        String name = et_name.getText().toString().trim();
        String idCard = et_idCard.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String carType = et_carType.getText().toString().trim();
        String carColor = et_carColor.getText().toString().trim();
        String carNum = et_carNum.getText().toString().trim();
        String address = et_addressDetail.getText().toString().trim();
        bean.setPhone(phone);
        bean.setMonitoredIdcard(idCard);
        bean.setMonitoredRealname(name);
        if (carNum != null || carColor != null || carType != null) {
            MonitoredBean.CarsBean carsBean = new MonitoredBean.CarsBean();
            carsBean.setColor(carColor);
            carsBean.setModel(carType);
            carsBean.setNum(carNum);
            List<MonitoredBean.CarsBean> carsBeans = new ArrayList<>();
            carsBeans.add(carsBean);
            bean.setCars(carsBeans);
        }
        bean.setMonitoredAddress(address);

        if (name.length() == 0) {
            MyToastUtils.showToast("请输入姓名");
            return;
        }
        if (idCard.length() != 18) {
            MyToastUtils.showToast("请输入18位身份证号");
            return;
        }
        if (phone.length() == 0) {
            MyToastUtils.showToast("请输入手机号");
            return;
        }

        bean.setMonitoredRealname(name);
        bean.setMonitoredIdcard(idCard);
        int lastTow = Integer.parseInt(idCard.substring(16, 17));
        Log.e("aaa", "性别  " + lastTow);
        if (lastTow % 2 == 0) {
            bean.setMonitoredSex(2);
        } else {
            bean.setMonitoredSex(1);
        }
        venifyId(name, idCard);
    }

    private boolean picUpload;

    private void needUploadFile() {
        if (picData.size() != 0 && picUpload) {
            showMyDialog("正在上传文件");
            upLoadFile(picData, 1);
            return;
        }

        if (!picUpload) {
            addMonitored();
        }
    }

    void afterVenifyIdCard() {
        picUpload = picData.size() == 0 ? false : true;
        needUploadFile();
    }

    private void addMonitored() {
        Log.e("aaa", "xinjain 新建重点人");
        bean.setRelatives(relativesBeans);
        bean.setMonitoredLastimage(picPath);
        Call<StringResult> call = apiService.addMonitored(bean);
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("添加成功!");
                    findViewById(R.id.btn_ok).setClickable(false);
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

    private String picPath = "";

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
                hideDialog();
                Gson gson = new Gson();
                UpLoadResutlText result = gson.fromJson(responseInfo.result, UpLoadResutlText.class);
                if ("1".equals(result.getDesc().getCode())) {
                    switch (type) {
                        case 1:
                            picPath = result.getMessage().getUrl();
                            bean.setMonitoredLastimage(picPath);
                            Log.e("aaa", "图片上传成功  " + picPath);
                            picUpload = false;
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

    @Background
    public void venifyId(String name, String idCard) {

        IdCardVerifyUtil idCardVerifyUtil = new IdCardVerifyUtil();
        String js = idCardVerifyUtil.idcard_verify(name, idCard);
        //{"data":{"code":"1108","message":"异常"},"status":"2005"}
        Log.e("aaa", "身份验证  " + js);
        try {
            JSONObject jsonObject = new JSONObject(js);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String status = jsonObject.getString("status");
            String code = dataObject.getString("code");
            if ("1000".equals(code)) {
                afterVenifyIdCard();
            } else if ("1001".equals(code)) {
                MyToastUtils.showToast("身份号码验证未通过");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
            } else if ("1001".equals(code)) {
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
                dialog.dismiss();
                bean.setAreaId(Integer.parseInt(areaId));
            }

            @Override
            public void onCancelClick() {
                dialog.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (requestCode == 1) {//  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
                picData.add(PathUtil.getRealPathFromUri(this, uri));
                Log.e("aaa", "onActivityRESULT  " + PathUtil.getRealPathFromUri(this, uri));
                picAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
