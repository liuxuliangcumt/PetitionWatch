package com.realpower.petitionwatch.modelwatch.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.util.IdCardVerifyUtil;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.CustomDialog;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.view.addressview.PickAddressInterface;
import com.realpower.petitionwatch.view.addressview.PickAddressView;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class KeypersonAlarmNewActivity extends BaseActivity {
    private List<MonitoredBean.RelativesBean> relativesBeans;
    private RelativeAdapter relativeAdapter;

    @ViewById(R.id.lv_kins)
    CustomListView lv_kins;
    @ViewById
    ClearEditText et_name, et_idCard, et_phone, et_carType, et_carNum, et_carColor, et_addressDetail;
    @ViewById
    TextView tv_address;
    private MonitoredBean bean = new MonitoredBean();

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
    }

    @Click({R.id.ll_addkins, R.id.btn_ok, R.id.ll_address})
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
        if (idCard.length() == 0) {
            MyToastUtils.showToast("请输入身份证号");
            return;
        }
        if (phone.length() == 0) {
            MyToastUtils.showToast("请输入手机号");
            return;
        }


        bean.setMonitoredRealname(name);
        bean.setMonitoredIdcard(idCard);
        venifyId(name, idCard);

    }

    private void addMonitored() {
        bean.setRelatives(relativesBeans);
        Call<StringResult> call = apiService.addAlarmMonitored(bean);
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("添加成功!");
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

    @Background
    public void venifyId(String name, String idCard) {

        IdCardVerifyUtil idCardVerifyUtil = new IdCardVerifyUtil();
        String js = idCardVerifyUtil.idcard_verify(name, idCard);
        //{"data":{"code":"1108","message":"异常"},"status":"2005"}
        try {
            JSONObject jsonObject = new JSONObject(js);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String status = jsonObject.getString("status");
            String code = dataObject.getString("code");
            if ("1000".equals(code)) {
                addMonitored();
            } else  {
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
}
