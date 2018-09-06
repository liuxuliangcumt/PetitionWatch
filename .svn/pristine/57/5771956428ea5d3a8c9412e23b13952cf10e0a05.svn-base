package com.realpower.petitionwatch.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.realpower.petitionwatch.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import io.vov.vitamio.widget.VideoView;

@EActivity
public class CamerasLiveActivity extends BaseActivity {

    @Extra
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        setContentView(R.layout.activity_cameras_live);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        VideoView mVideoView = (VideoView) findViewById(R.id.surface_view);
        mVideoView.setVideoURI(Uri.parse(path));
        Log.e("aaa", path + "   path");
        //mVideoView.setMediaController(new MediaController(this));

    }
}
