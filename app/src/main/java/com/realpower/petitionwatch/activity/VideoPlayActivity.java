package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.realpower.petitionwatch.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EActivity
public class VideoPlayActivity extends BaseActivity {

    MediaController controller;
    @ViewById(R.id.vv)
    VideoView vv;
    @Extra
    String path;
    private static int vvSeekPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        if (savedInstanceState == null) {
            vvSeekPosition = 0;
        }
        controller = new MediaController(this);
        vv.setMediaController(controller);
        String videoName = path.substring(path.lastIndexOf("/") + 1, path.length());
        final String videoLocalPath = FileDownloadUtils.getDefaultSaveRootPath() + "/video/" + videoName;

        if (new File(videoLocalPath).exists()) {
            vv.setVideoPath(videoLocalPath);
            vv.start();
        } else {
            showMyDialog("正在下载");
            FileDownloader.getImpl().create(path)
                    .setPath(videoLocalPath)
                    .setListener(new FileDownloadListener() {
                        @Override
                        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                        }

                        @Override
                        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                        }

                        @Override
                        protected void blockComplete(BaseDownloadTask task) {

                        }

                        @Override
                        protected void completed(BaseDownloadTask task) {
                            hideDialog();
                            vv.setVideoPath(videoLocalPath);
                            vv.start();
                        }

                        @Override
                        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                        }

                        @Override
                        protected void error(BaseDownloadTask task, Throwable e) {

                        }

                        @Override
                        protected void warn(BaseDownloadTask task) {

                        }
                    })
                    .start();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean("isPort", true);
    }
}
