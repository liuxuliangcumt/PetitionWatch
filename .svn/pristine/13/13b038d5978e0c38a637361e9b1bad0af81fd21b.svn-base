package com.realpower.petitionwatch.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.io.UnsupportedEncodingException;
import java.util.UUID;


/**
 * Copyright:   Copyright (c) 2015年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.<br>
 * Version: V1.0<br>
 * Author:  何 毅 <br>
 * Date:    2015-05-11 19:57 <br>
 * Desc:    系统信息工具类 <br>
 * Edit History: <br>
 */
public class SystemInfoUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public final static String tag = "MeiCai_SystemInfo";

    /**
     * 获取屏幕的宽度
     */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的宽度
     */
    public final static int getWindowsHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * Desc: 获取app版本名 <br>
     *
     * @param context
     * @return 返回manifest文件中versionName的value. if catch NameNotFoundException，return ""
     */
    public static String getAppVersionName(Context context) {
        String versionName;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
        return versionName;
    }


    /**
     * 获取当前程序版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;

        } catch (Exception e) {
        }
        return versionCode;
    }

    /**
     * 获取当前系统版本号
     *
     * @param context
     * @return
     */
    public static String getCurrentSystemVersion(Context context) {
        return "android" + "^" + Build.VERSION.RELEASE;
    }


    /**
     * @param context
     * @return
     */
    public static String getPhoneNumber(Context context) {

        if (null == context) {
            return "";
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = tm.getLine1Number();
        if (!TextUtils.isEmpty(phoneNumber)) {
            return phoneNumber;
        } else {
            return "";
        }

    }

    /**
     * @param context
     * @return 手机IMEI号
     */
    public static String getDeviceId(Context context) {

        if (null == context) {
            return "";
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        } else {
            return "";
        }

    }

    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static UUID uuid;

    /**
     * 生成设备唯一标识。生成策略：先根据android_id生成uuid，如果为空，再根据deviceId
     * 生成uuid，如果再为空，则使用随机的uuid，保存到SharedPreferences，下次直接取。
     * 一般android_id和deviceId足够判断，怕不保险还可以加上wifi mac地址、Build.SERIAL等，但都不能保证绝对可以获取到。
     *
     * @param context
     * @return
     */
    public static UUID getDeviceUuid(Context context) {
        if (uuid == null) {
            synchronized (SystemInfoUtils.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);

                    if (id != null) {
                        uuid = UUID.fromString(id);
                    } else {

                        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

                        try {
                            if (!"9774d56d682e549c".equals(androidId) && !TextUtils.isEmpty(androidId)) {//有些机器会返回“9774d56d682e549c”
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                final String deviceId = ((TelephonyManager) context
                                        .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                                uuid = !TextUtils.isEmpty(deviceId) ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8"))
                                        : UUID.randomUUID();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }

                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();

                    }

                }
            }
        }
        return uuid;
    }

    /**
     * 获取设备硬件序列号（自2.3以上推荐用此方法作为唯一标识。只有数字字母，并且不区分大小写。）
     *
     * @return 设备硬件序列号
     * @author 何毅
     */
    public static String getSerial() {
        return getNotNullString(Build.SERIAL);
    }


    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    /**
     * 将dp 转为px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 获取非Null字符串，若为Null则新建一串空字符串返回
     *
     * @param content 应用上下文
     * @return 字符串
     * @author何毅
     */
    public static String getNotNullString(String content) {
        return content == null ? "" : content;
    }


    /**
     * 获取设备品牌
     *
     * @return 设备品牌
     * @author 何毅
     */
    public static String getBrand() {
        return getNotNullString(Build.BRAND);
    }

    /**
     * 获取设备模组号（即一般来说的型号）
     *
     * @return 设备模组号
     * @author 何毅
     */
    public static String getModel() {
        return getNotNullString(Build.MODEL);
    }

    /**
     * 获取系统版本号
     *
     * @return 系统版本号
     * @author 何毅
     */
    public static String getSDKInt() {
        return Integer.toString(Build.VERSION.SDK_INT);
    }


    /**
     * 判断网络 提示
     *
     * @param mActivity
     * @return
     */
    public static boolean validateNetWork(final Context mActivity) {
        boolean isGoON = true;
        if (!isNetworkAvailable(mActivity)) {
            isGoON = false;
            AlertDialog.Builder builders = new AlertDialog.Builder(mActivity);
            builders.setTitle("抱歉，网络连接失败，是否进行网络设置？");
            builders.setCancelable(false);
            builders.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 进入无线网络配置界面
                            try {
                                mActivity.startActivity(new Intent(
                                        Settings.ACTION_WIRELESS_SETTINGS));
                            } catch (ActivityNotFoundException e) {
                                MyToastUtils.showToast( "抱歉，无法进入网络设置，请您手动前往设置");
                            }
                        }
                    });
            builders.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            // 关闭当前activity
                            ((Activity) mActivity).finish();
                        }
                    });
            builders.show();
        }
        return isGoON;
    }


    /**
     * 获取网络连接状态 NET_NO：没有网络 NET_2G:2g网络 NET_3G：3g网络 NET_4G：4g网络 NET_WIFI：wifi
     * NET_UNKNOWN：未知网络
     *
     * @param context
     * @return 状态码
     */
    public static String getNetWorkType(Context context) {
        String stateCode = "NET_NO";
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            switch (ni.getType()) {

                case ConnectivityManager.TYPE_WIFI:
                    stateCode = "NET_WIFI";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    switch (ni.getSubtype()) {
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            stateCode = "NET_2G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            stateCode = "NET_3G";
                           // LogUtils.v(tag, "当前网络为3G网络");
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            stateCode = "NET_4G";
                           // LogUtils.v(tag, "当前网络为4G网络");
                            break;
                        default:
                            stateCode = "NET_UNKNOWN";
                            //LogUtils.v(tag, "当前网络未知");
                    }
                    break;
                default:
                    stateCode = "NET_UNKNOWN";
                   // LogUtils.v(tag, "当前网络未知");
            }

        }
        return stateCode;
    }

    /**
     * 获取网络是否连接 isNetworkAvailable(Context context)，context中传入上下文对象 返回值为
     * boolean类型 1）true：网络已连接 2）false 网络未连接 需添加权限
     * <uses-permissionandroid:name="android.permission.ACCESS_NETWORK_STATE"/>
     */

    public static boolean isNetworkAvailable(Context context) {
        boolean available = false;
        if (null != context) {
            // ConnectivityManager主要管理和网络连接相关的操作。
            ConnectivityManager connManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (null != networkInfo) {
                // 获取当前的网络连接是否可用
                available = networkInfo.isAvailable();
                if (available) {
                   // LogUtils.v(tag, "当前的网络连接可用");
                } else {
                   // LogUtils.v(tag, "当前的网络连接不可用");
                }
            } else {
               // LogUtils.v(tag, "上下文对象不能为空");
            }
        }
        return available;
    }

}
