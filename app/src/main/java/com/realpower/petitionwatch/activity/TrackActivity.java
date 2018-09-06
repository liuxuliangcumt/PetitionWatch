package com.realpower.petitionwatch.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;

import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.modelcounty.adapter.TrackPersonAdapter;
import com.realpower.petitionwatch.modelcounty.bean.GpsPointBean;
import com.realpower.petitionwatch.modelcounty.bean.TrackPersonBean;
import com.realpower.petitionwatch.modelwatch.adapter.SpinnerAdapter;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.param.SearchByCriteriaParam;
import com.realpower.petitionwatch.net.param.SearchGpsByCriteriaParam;
import com.realpower.petitionwatch.net.result.GpsPointResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.net.result.TrackPersonResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SystemInfoUtils;
import com.realpower.petitionwatch.view.ClearEditText;
import com.realpower.petitionwatch.view.CustomDialog;
import com.realpower.petitionwatch.view.CustomListView;
import com.realpower.petitionwatch.view.addressview.PickAddressInterface;
import com.realpower.petitionwatch.view.addressview.PickAddressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

@EActivity
public class TrackActivity extends BaseActivity {
    @ViewById(R.id.spinner)
    AppCompatSpinner mySpinner;
    private CustomDialog setDailog;
    private CustomDialog personDailog;
    private CustomListView lv_person;
    private TrackPersonAdapter personAdapter;
    private List<TrackPersonBean> personBeanList;
    private SearchGpsByCriteriaParam gpsByCriteriaParam;
    private String timePoint = "";
    private String startTime = "";
    private String endTime = "";
    private int searchType = 3;//查询类型: 1 某时间位置  2轨迹  3 当前位置
    private int personType = 1;//查找人类型:1重点人  2 监控工作人
    private List<String> ids = new ArrayList<>();
    private String regionCode = "";
    private int isOnSuper = 2;//是否显示监控工作人员 1 显示 2不显示;
    private BaiduMap baiduMap;
    private MapView mapView;
    public PolylineOptions polylineOptions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        mapView = (MapView) findViewById(R.id.mapView);
        baiduMap = mapView.getMap();
    }

    @AfterViews
    void onInitView() {
        setTitleName("人员轨迹");
        setRightIcon(R.drawable.search);
        gpsByCriteriaParam = new SearchGpsByCriteriaParam();
        List<String> spinnerDate = new ArrayList<>();
        spinnerDate.add("全部");
        spinnerDate.add("已分配");
        spinnerDate.add("待分配");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(spinnerDate, this);
        mySpinner.setAdapter(spinnerAdapter);
        mySpinner.setSelection(0, true);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void searchByCriteria(String name, String phone, int personType) {
        Call<TrackPersonResult> call = apiService.searchByCriteria(new SearchByCriteriaParam(phone, name, regionCode, personType + ""));
        call.enqueue(new MyCallback<TrackPersonResult>() {
            @Override
            public void onSuccessRequest(TrackPersonResult result) {
                personBeanList.clear();
                ids.clear();
                personBeanList.addAll(result.getMessage());
                personAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TrackPersonResult> call, Throwable t) {

            }
        });
    }

    @Click({R.id.btn_person, R.id.btn_set})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set:
                if (setDailog == null) {
                    createSetDailog();
                } else {
                    setDailog.show();
                }
                break;
            case R.id.btn_person:
                if (personDailog == null) {
                    createPersonDailog();
                } else {
                    personDailog.show();
                    personBeanList.clear();
                    personAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void createPersonDailog() {
        personBeanList = new ArrayList<>();
        personAdapter = new TrackPersonAdapter(this, personBeanList);
        personDailog = new CustomDialog(this, R.style.customAnimationDialog, R.layout.dialog_track_person);
        personDailog.show();
        WindowManager.LayoutParams layoutParams = personDailog.getWindow().getAttributes();
        layoutParams.width = SystemInfoUtils.getWindowsWidth(this);
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        personDailog.getWindow().setAttributes(layoutParams);
        lv_person = (CustomListView) personDailog.getCustomView().findViewById(R.id.lv_person);
        lv_person.setAdapter(personAdapter);
        LinearLayout ll_region = (LinearLayout) personDailog.getCustomView().findViewById(R.id.ll_region);
        final TextView tv_region = (TextView) personDailog.getCustomView().findViewById(R.id.tv_region);
        final ClearEditText et_name = (ClearEditText) personDailog.getCustomView().findViewById(R.id.et_name);
        final ClearEditText et_phone = (ClearEditText) personDailog.getCustomView().findViewById(R.id.et_phone);
        final Switch s_show = (Switch) personDailog.getCustomView().findViewById(R.id.s_show);
        RadioButton rb_key = (RadioButton) personDailog.getCustomView().findViewById(R.id.rb_key);
        Button btn_search = (Button) personDailog.getCustomView().findViewById(R.id.btn_search);
        Button btn_location = (Button) personDailog.getCustomView().findViewById(R.id.btn_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGpsByCriteria();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByCriteria(et_name.getText().toString(), et_phone.getText().toString(), personType);
            }
        });
        rb_key.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    personType = 1;
                    s_show.setVisibility(View.VISIBLE);
                    personBeanList.clear();
                    personAdapter.notifyDataSetChanged();
                } else {
                    personBeanList.clear();
                    personAdapter.notifyDataSetChanged();
                    personType = 2;
                    s_show.setVisibility(View.GONE);
                }
            }
        });
        //区域
        ll_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressDialog(tv_region);
            }
        });

        s_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isOnSuper = 1;
                } else {
                    isOnSuper = 2;
                }
            }
        });
    }

    private void searchGpsByCriteria() {
        gpsByCriteriaParam.getTime().clear();

        if (searchType == 1) {//查询类型: 1 某时间位置  2轨迹  3 当前位置
            if (timePoint.length() != 0) {
                gpsByCriteriaParam.getTime().add(timePoint);
            } else {
                MyToastUtils.showToast("请设置查询类型");
                return;
            }
        } else if (searchType == 2) {

            if (startTime.length() != 0) {
                gpsByCriteriaParam.getTime().add(startTime);
            } else {
                MyToastUtils.showToast("请设置查询起始时间");
                return;
            }
            if (endTime.length() != 0) {
                gpsByCriteriaParam.getTime().add(endTime);
            } else {
                MyToastUtils.showToast("请设置查询结束时间");
            }
        }


        ids.clear();
        for (int i = 0; i < personAdapter.getData().size(); i++) {
            Log.e("aaa", personAdapter.getData().get(i).isChecked() +
                    "  " + personAdapter.getData().get(i).getMonitoredRealname());
            if (personAdapter.getData().get(i).isChecked()) {
                ids.add(personAdapter.getData().get(i).getMonitoredId() + "");
            }
        }
        if (ids.size() == 0) {
            MyToastUtils.showToast("请选择查询人");
            return;
        }
        if (gpsByCriteriaParam.getTime().size() == 0 && searchType != 3) {
            MyToastUtils.showToast("请设置查询类型");
            return;
        }
        personDailog.dismiss();
        gpsByCriteriaParam.setIds(ids);
        gpsByCriteriaParam.setType(personType + "");
        gpsByCriteriaParam.setOnSuper(isOnSuper + "");
        Call<GpsPointResult> call = apiService.searchGpsByCriteria(gpsByCriteriaParam);
        call.enqueue(new MyCallback<GpsPointResult>() {
            @Override
            public void onSuccessRequest(GpsPointResult result) {
                if ("1".equals(result.getStatus()))
                    setPoints(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<GpsPointResult> call, Throwable t) {

            }
        });
    }


    private void createSetDailog() {
        setDailog = new CustomDialog(this, R.style.customAnimationDialog, R.layout.dialog_track_set);
        setDailog.show();
        WindowManager.LayoutParams layoutParams = setDailog.getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        setDailog.getWindow().setAttributes(layoutParams);
        final LinearLayout ll_location = (LinearLayout) setDailog.getCustomView().findViewById(R.id.ll_location);
        final LinearLayout ll_track = (LinearLayout) setDailog.getCustomView().findViewById(R.id.ll_track);
        LinearLayout ll_timeStart = (LinearLayout) setDailog.getCustomView().findViewById(R.id.ll_timeStart);
        LinearLayout ll_timeEnd = (LinearLayout) setDailog.getCustomView().findViewById(R.id.ll_timeEnd);
        Switch s_car = (Switch) setDailog.getCustomView().findViewById(R.id.s_car);
        RadioGroup rg_set = (RadioGroup) setDailog.getCustomView().findViewById(R.id.rg_set);
        final TextView tv_timeLocation = (TextView) setDailog.getCustomView().findViewById(R.id.tv_timeLocation);
        final TextView tv_timeStart = (TextView) setDailog.getCustomView().findViewById(R.id.tv_timeStart);
        final TextView tv_timeEnd = (TextView) setDailog.getCustomView().findViewById(R.id.tv_timeEnd);
        rg_set.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        ll_location.setVisibility(View.VISIBLE);
                        ll_track.setVisibility(View.GONE);
                        searchType = 1;
                        break;
                    case R.id.rb_2:
                        ll_location.setVisibility(View.GONE);
                        ll_track.setVisibility(View.VISIBLE);
                        searchType = 2;
                        break;
                    case R.id.rb_3:
                        ll_location.setVisibility(View.GONE);
                        ll_track.setVisibility(View.GONE);
                        searchType = 3;
                        break;
                }
            }
        });
        ll_timeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(tv_timeStart, 1);
            }
        });
        ll_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(tv_timeLocation, 0);
            }
        });
        ll_timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(tv_timeEnd, 2);
            }
        });
        ImageView iv_close = (ImageView) setDailog.getCustomView().findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDailog.dismiss();
            }
        });

    }

    private void showDatePickDialog(final TextView time, final int from) {

        //from  区分来之哪个地方选择时间
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(DateType.TYPE_ALL);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm:SS");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                String format = "yyyy-MM-dd HH:mm:SS";
                String messge = "";
                try {
                    messge = new SimpleDateFormat(format).format(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                time.setText(messge);
                switch (from) {
                    case 0://时间点
                        timePoint = (String) time.getText();
                        break;
                    case 1:// 时间段 开始时间
                        startTime = time.getText().toString();
                        break;
                    case 2://时间段 结束时间
                        endTime = time.getText().toString();
                        break;
                }
            }
        });
        dialog.show();
    }

    private void showAddressDialog(final TextView tv_region) {
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
                tv_region.setText(name);
                regionCode = areaId.toString();
                dialog.dismiss();
            }

            @Override
            public void onCancelClick() {
                tv_region.setText("");
                regionCode = "";
                dialog.dismiss();
            }
        });

    }

    private void showLocationList(List<LatLng> locationList) {
        // 将地图移到到最后一个经纬度位置
        //找最南 最北 最西 最東的点
        LatLngBounds.Builder latbounds = new LatLngBounds.Builder();
        for (LatLng p : locationList) {
            latbounds = latbounds.include(p);
        }
        LatLngBounds latlngBounds = latbounds.build();
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds, mapView.getWidth(), mapView.getHeight());
        baiduMap.animateMapStatus(u);
    }

    private void setPoints(List<GpsPointBean> message) {
        //清空地图
        baiduMap.clear();
        //创建marker的显示图标

        if (searchType == 2) {//查询类型: 1 某时间位置  2轨迹  3 当前位置
            List<LatLng> locationList = new ArrayList<>();
            for (int i = 0; i < message.size(); i++) {
                LatLng lating = new LatLng(Double.parseDouble(message.get(i).getLatitude()),
                        Double.parseDouble(message.get(i).getLongitude()));
                locationList.add(lating);
            }
            polylineOptions = new PolylineOptions().width(10)
                    .color(Color.BLUE).points(locationList);
            baiduMap.addOverlay(polylineOptions);
            showLocationList(locationList);
        } else {
            LatLng latLng = null;
            Marker marker;
            OverlayOptions options;
            View view = View.inflate(this, R.layout.map_marker, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            for (GpsPointBean info : message) {
                iv_icon.setImageDrawable(getResources().getDrawable(R.drawable.monitored));
                tv_title.setText(info.getName());
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);

                //获取经纬度
                latLng = new LatLng(Double.parseDouble(info.getLatitude()), Double.parseDouble(info.getLongitude()));
                //设置marker
                options = new MarkerOptions()
                        .position(latLng)//设置位置
                        .icon(bitmap)//设置图标样式
                        .zIndex(9) // 设置marker所在层级
                        .draggable(true); // 设置手势拖拽;
                //添加marker
                marker = (Marker) baiduMap.addOverlay(options);
                //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
                Bundle bundle = new Bundle();
                //info必须实现序列化接口
                bundle.putSerializable("info", info);
                marker.setExtraInfo(bundle);

                for (GpsPointBean.SupervisorGpsListBean sbean : info.getSupervisorGpsList()) {
                    iv_icon.setImageDrawable(getResources().getDrawable(R.drawable.monitor));
                    tv_title.setText(sbean.getName());
                    BitmapDescriptor bitmapSuper = BitmapDescriptorFactory.fromView(view);

                    latLng = new LatLng(Double.parseDouble(sbean.getLatitude()), Double.parseDouble(sbean.getLongitude()));
                    //设置marker
                    options = new MarkerOptions()
                            .position(latLng)//设置位置
                            .icon(bitmapSuper)//设置图标样式
                            .zIndex(9) // 设置marker所在层级
                            .draggable(true); // 设置手势拖拽;
                    //添加marker
                    marker = (Marker) baiduMap.addOverlay(options);
                    //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
                    Bundle bundleSuper = new Bundle();
                    //info必须实现序列化接口
                    bundle.putSerializable("super", sbean);
                    marker.setExtraInfo(bundleSuper);
                    Log.e("aaa", "监控工作人坐标  " + sbean.getName());
                }
            }
            //将地图显示在最后一个marker的位置
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.setMapStatus(msu);
        }
    }
}
