package com.realpower.petitionwatch.server;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.realpower.petitionwatch.bean.AddGpsBean;
import com.realpower.petitionwatch.keepalive.AbsWorkService;
import com.realpower.petitionwatch.modelwatch.activity.WatchMainActivity;
import com.realpower.petitionwatch.net.ApiService;
import com.realpower.petitionwatch.net.Mate;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.OkHttpClientUtil;
import com.realpower.petitionwatch.net.param.AddAlarmParam;
import com.realpower.petitionwatch.net.param.AddGpsParam;
import com.realpower.petitionwatch.net.result.AddGpsResult;
import com.realpower.petitionwatch.net.result.StringResult;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SharedPreferencesHelper;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */
public class LocationServerAlive extends AbsWorkService {
    public static boolean sShouldStopService;
    private LatLng localLatlng;
    public static Retrofit retrofit;
    /*= new Retrofit.Builder()
            .baseUrl(Mate.API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClientUtil.getOkHttpClient((String) SharedPreferencesHelper.getInstance().getData("token", "d")))
            .build();*/
    public static ApiService apiService;
    //= retrofit.create(ApiService.class);
    protected NotificationManager notificationManager = null;
    private BaiduSDKReceiver mBaiduReceiver;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("aaa", "LocationServerAlive  Handler msg====1");
                    if (mLocClient != null) {
                        mLocClient.requestLocation();
                    }
                    handler.sendEmptyMessageDelayed(1, 1000 * 60 * 5);
                    break;
            }
            return false;
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void addClient(String token) {
        OkHttpClientUtil okHttpClientUtil = new OkHttpClientUtil();
        // OkHttpClientUtil.getOkHttpClient(token)
        retrofit = new Retrofit.Builder()
                .baseUrl(Mate.API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientUtil.getOkHttpClient(token))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        Log.e("aaa", "LocationServerAlive onCreate");
        addClient((String) SharedPreferencesHelper.getInstance().getData("token", "d"));
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        isStartUpdate = false;
        //  addClient();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mBaiduReceiver = new BaiduSDKReceiver();
        registerReceiver(mBaiduReceiver, iFilter);
        initLocation();

    }

    @Override
    public void onDestroy() {
        isStartUpdate = false;
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return null;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {

    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {

    }

    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        return null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent, Void alwaysNull) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("aaa", "LocationServerAlive onStartCommand  ");
        if (sShouldStopService) {
            isStartUpdate = true;
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessage(1);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private static boolean isStartUpdate;

    public LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();

    private void initLocation() {
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// open gps
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
        Log.e("aaa", "LocationServerAlive   initLocation");
    }

    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || !sShouldStopService) {
                return;
            }
            localLatlng = new LatLng(location.getLatitude(), location.getLongitude());
            Log.e("aaa", "LocationServerAlive  定位成功  "
                    + location.getLatitude() + "   " +
                    location.getLongitude());
            if (localLatlng.latitude != 4.9E-324D) {
                upDataGps(localLatlng.latitude, localLatlng.longitude);
            } else {
                Log.e("aaa", "定位失败");
            }
        }
    }

    /**
     * 更新自己的经纬度到服务器 并获取被监控人经纬度
     *
     * @param latitude
     * @param longitude
     */
    private void upDataGps(double latitude, double longitude) {
        Log.e("aaa", "upDataGps   upDataGps");

        Call<AddGpsResult> call = apiService.addGps(new AddGpsParam(latitude + "", longitude + ""));
        call.enqueue(new MyCallback<AddGpsResult>() {

            @Override
            public void onSuccessRequest(AddGpsResult result) {
                Log.e("aaa", "upDataGps   " + result.toString());
                if ("1".equals(result.getStatus())) {
                    if (result.getMessage().getQueues().size() != 0) {

                    }
                    //  notification(result.getMessage().getQueues());
                    computeRange(result.getMessage());

                }
            }

            @Override
            public void afterRequest() {
                Log.e("aaa", "upDataGps   afterRequest");

            }

            @Override
            public void onFailureRequest(Call<AddGpsResult> call, Throwable t) {
                Log.e("aaa", "upDataGps  onFailureRequest " + t.toString());

            }
        });

    }

    /**
     * ji计算与被监控人距离
     *
     * @param message
     */
    private void computeRange(AddGpsBean message) {
        if (message.getLatitude().length() == 0 || "4.9E-324".equals(message.getLatitude())) {
            return;
        }
        LatLng monitorLatLng = new LatLng(Double.parseDouble(message.getLatitude()), Double.parseDouble(message.getLongitude()));
        int distance = (int) DistanceUtil.getDistance(localLatlng, monitorLatLng);
        Log.e("aaa", "计算距离  " + distance);

        if (distance > 500) {
            Call<StringResult> call = apiService.aotuAddAlarm(new AddAlarmParam(message.getMonitoredId()));
            call.enqueue(new Callback<StringResult>() {
                @Override
                public void onResponse(Call<StringResult> call, Response<StringResult> response) {
                    notificationAlarm();
                }

                @Override
                public void onFailure(Call<StringResult> call, Throwable t) {

                }
            });
        }
    }

    private void notificationAlarm() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle("距离提示");
        builder.setContentText("您距离重点人超过500了");
        builder.setAutoCancel(true);

        //系统状态栏显示的小图标
        builder.setSmallIcon(getApplicationInfo().icon);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), getApplicationInfo().icon));

        Intent intent = new Intent(getApplicationContext(), WatchMainActivity.class);

        // intent.putExtras(bundle);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //点击跳转的intent
        builder.setContentIntent(pIntent);

        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_ALL;
        notificationManager.notify(1, notification);
    }

    private void notification(List<AddGpsBean.QueuesBean> queues) {
        Log.e("aaa", "notification  ");
        for (int i = 0; i < queues.size(); i++) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setContentTitle(queues.get(i).getTitle());
            builder.setContentText(queues.get(i).getInfo());
            builder.setAutoCancel(true);

            //系统状态栏显示的小图标
            builder.setSmallIcon(getApplicationInfo().icon);
            //下拉显示的大图标
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), getApplicationInfo().icon));

            Intent intent = new Intent(getApplicationContext(), WatchMainActivity.class);

            // intent.putExtras(bundle);
            PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //点击跳转的intent
            builder.setContentIntent(pIntent);

            Notification notification = builder.build();
            notification.defaults = Notification.DEFAULT_ALL;
            notificationManager.notify(1, notification);
        }

    }


    public class BaiduSDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            String st1 = "网络出错";
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {

                String st2 = "检查网络错误";
                // MyToastUtils.showToast(st2);
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {

                Log.e("aaa", "百度地图 " + st1);
            }
        }
    }

}
