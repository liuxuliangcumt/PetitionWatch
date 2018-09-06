package com.realpower.petitionwatch.modelwatch.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;
import com.realpower.petitionwatch.modelwatch.adapter.TaskAdapter;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.result.TasksResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class TaskActivity extends BaseActivity {
    @ViewById(R.id.lv_task)
    ListView lv_task;
    TaskAdapter adapter;
    List<WatcherTaskBean> data;

    MapView mapview;
    BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(this.getApplicationContext());
        setContentView(R.layout.activity_task);
    }
    @AfterViews
    void initViews() {
        mapview = (MapView)  findViewById(R.id.mapview);
        mBaiduMap = mapview.getMap();
        initLocation();
        setTitleName("任务列表");
        data = new ArrayList<>();
        adapter = new TaskAdapter(this, data);
        lv_task.setAdapter(adapter);
        lv_task.setDivider(getResources().getDrawable(R.drawable.task_listview_divider1));
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskDetailActivity_.intent(TaskActivity.this).bean(data.get(position)).taskId(data.get(position).getTaskId()).start();
            }
        });
        //  locaton();
        int count = mapview.getChildCount();
        mapview.getChildAt(1).setVisibility(View.GONE);//隐藏百度logo
        mapview.getChildAt(2).setVisibility(View.GONE);//y隐藏缩放
        View emptyView = View.inflate(this, R.layout.petition_emptyview, null);
        ViewGroup parentView = (ViewGroup) lv_task.getParent();
        parentView.addView(emptyView);
        lv_task.setEmptyView(emptyView);
        getData();
    }


    private void getData() {
        Call<TasksResult> call = apiService.getAllTask();
        call.enqueue(new MyCallback<TasksResult>() {
            @Override
            public void onSuccessRequest(TasksResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<TasksResult> call, Throwable t) {

            }
        });

    }

    private void setData(List<WatcherTaskBean> message) {
        data.clear();
        data.addAll(message);
        adapter.notifyDataSetChanged();
        //添加覆盖物
        if (data.size() == 0)
            return;
        if (data.get(0).getMonitored().getLatitude().length() != 0
                && !data.get(0).getMonitored().getLatitude().equals("4.9E-324")) {

            LatLng latLng = new LatLng(Double.parseDouble(data.get(0).getMonitored().getLatitude() + ""),
                    Double.parseDouble(data.get(0).getMonitored().getLongitude() + ""));
          /*  BitmapDescriptor bmStart = BitmapDescriptorFactory.fromResource(R.drawable.icon_z_r);
            MarkerOptions startMarker = new MarkerOptions().position(latLng)
                    .icon(bmStart).zIndex(9).draggable(true);*/
            OverlayOptions ooA = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location))
                    .zIndex(4).draggable(false);
            mBaiduMap.addOverlay(ooA);
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(latLng, 17.0f);
            mBaiduMap.animateMapStatus(u);
        }

        Log.e("aaa", message.size() + "   任务列表数量 ");
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        mapview.onResume();
        if (mLocClient != null) {
            mLocClient.requestLocation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

    BitmapDescriptor mCurrentMarker;

    private void locaton() {
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                // .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360  116.398807,40.016465
                .direction(100).latitude(40.016465)
                .longitude(116.398807).build();

// 设置定位数据
        mBaiduMap.setMyLocationData(locData);
        LatLng mlatlng = new LatLng(40.016465, 116.398807);
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.avatar);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);

        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(mlatlng, 17.0f);
        mBaiduMap.animateMapStatus(u);

    }

    public LocationClient mLocClient;
    public  MyLocationListenner myListener = new  MyLocationListenner();

    private void initLocation() {
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// open gps
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
        Log.e("aaa", "LocationServerAlive   initLocation");
    }
    private LatLng localLatlng;

    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            localLatlng = new LatLng(location.getLatitude(), location.getLongitude());
            Log.e("aaa", "LocationServerAlive  定位成功  "
                    + location.getLatitude() + "   " +
                    location.getLongitude());
            if (localLatlng.latitude != 4.9E-324D) {
                OverlayOptions ooA = new MarkerOptions().position(localLatlng).icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.location))
                        .zIndex(4).draggable(false);
                mBaiduMap.addOverlay(ooA);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(localLatlng, 17.0f);
                mBaiduMap.animateMapStatus(u);
            } else {
                Log.e("aaa", "定位失败");
            }
        }
    }
}
