package com.realpower.petitionwatch;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.liulishuo.filedownloader.FileDownloader;
import com.realpower.petitionwatch.chatui.model.FriendshipInfo;
import com.realpower.petitionwatch.keepalive.DaemonEnv;
import com.realpower.petitionwatch.server.LocationServerAlive;
import com.realpower.petitionwatch.util.MyToastUtils;
import com.realpower.petitionwatch.util.SharedPreferencesHelper;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMOfflinePushSettings;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.xiaomi.mipush.sdk.HWPushHelper;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;
import java.util.Locale;

/**
 *
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static Context applicationContext;
    private String tag = "TengXun";

    public static final String APP_ID = "2882303761517805324";//xiaomi
    public static final String APP_KEY = "5761780525324";
    public static final String TAG = "com.realpower.petitionwatch";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext=getApplicationContext();
     /* CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/
        FileDownloader.init(this);//文件下载帮助类
        CrashHandler.getInstance().init(this);
        SharedPreferencesHelper.init(this);
        SDKInitializer.initialize(getApplicationContext());
        DaemonEnv.initialize(this, LocationServerAlive.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        LocationServerAlive.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(LocationServerAlive.class);
        setTengXun();
        if(MsfSdkUtils.isMainProcess(this)) {
            registerPush();
        }
      // HMSAgent.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getmContext() {
        return applicationContext;
    }

    private void setTengXun() {
        // 只能在主进程进行离线推送监听器注册    云推送配置
        if (MsfSdkUtils.isMainProcess(this)) {
            Log.e("MyApplication", "main process");

            // 设置离线推送监听器
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    Log.e("MyApplication", "recv offline push离线推送" + notification.getContent());

                    // 这里的 doNotify 是 ImSDK 内置的通知栏提醒，应用也可以选择自己利用回调参数 notification 来构造自己的通知栏提醒
                    notification.doNotify(getApplicationContext(), R.drawable.iv_arrow);
                }
            });
        }
        TIMSdkConfig config = new TIMSdkConfig(1400091300)
                .enableCrashReport(false)
                .enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG)
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");

        TIMManager.getInstance().init(getApplicationContext(), config);


        //基本用户配置
        TIMUserConfig userConfig = new TIMUserConfig()
               /* //设置群组资料拉取字段
                .setGroupSettings(initGroupSettings())
                //设置资料关系链拉取字段
                .setFriendshipSettings(initFriendshipSettings())
                //设置用户状态变更事件监听器*/
                .setUserStatusListener(new TIMUserStatusListener() {
                    @Override
                    public void onForceOffline() {
                        //被其他终端踢下线
                        Log.i(tag, "onForceOffline");
                    }

                    @Override
                    public void onUserSigExpired() {
                        //用户签名过期了，需要刷新 userSig 重新登录 SDK
                        Log.i(tag, "onUserSigExpired");
                    }
                })
                //设置连接状态事件监听器
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        Log.i(tag, "onConnected");
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.i(tag, "onDisconnected");
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Log.i(tag, "onWifiNeedAuth");
                    }
                });


        //消息扩展用户配置
        userConfig = new TIMUserConfigMsgExt(userConfig)
                //禁用消息存储
                .enableStorage(false)
                //开启消息已读回执
                .enableReadReceipt(true);


        //将用户配置与通讯管理器进行绑定
        TIMManager.getInstance().setUserConfig(userConfig);
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                Log.e("aaa", "application  " + list.get(0).getMsg().toString());
                // MyToastUtils.showToast(list.get(0).getPriority().toString());
                return false;
            }
        });
        TIMOfflinePushSettings pushSettings = new TIMOfflinePushSettings();
        pushSettings.setEnabled(true);
        TIMManager.getInstance().setOfflinePushSettings(pushSettings);

    }


    public void registerPush(){
        String vendor = Build.MANUFACTURER;
        if(vendor.toLowerCase(Locale.ENGLISH).contains("xiaomi")) {
            //注册小米推送服务
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }else if(vendor.toLowerCase(Locale.ENGLISH).contains("huawei")) {
            //请求华为推送设备 token
            HWPushHelper.shouldGetToken(this);
        }
    }
}
