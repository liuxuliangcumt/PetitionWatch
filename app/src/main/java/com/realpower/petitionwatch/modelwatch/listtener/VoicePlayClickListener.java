package com.realpower.petitionwatch.modelwatch.listtener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.MyLocalMedia;
import com.realpower.petitionwatch.modelwatch.bean.VoiceBean;
import com.realpower.petitionwatch.net.Mate;

import java.io.File;

/**
 * Created by Administrator on 2017/12/14.
 */

public class VoicePlayClickListener implements View.OnClickListener {
    ImageView voiceIconView;
    public static boolean isPlaying = false;
    private AnimationDrawable voiceAnimation = null;
    MediaPlayer mediaPlayer = null;
    Activity activity;
    public static VoicePlayClickListener currentPlayListener = null;
    private MyLocalMedia voiceBean;

    public VoicePlayClickListener(MyLocalMedia voiceBean, ImageView voiceIconView, Activity activity) {
        this.voiceIconView = voiceIconView;
        this.activity = activity;
        this.voiceBean = voiceBean;
    }

    @Override
    public void onClick(View v) {

        if (voiceBean.isDetail()) {//详情需下载
            if ((new File(FileDownloadUtils.getDefaultSaveRootPath() + voiceBean.getLoadPath()).exists())) {
                //已经下载
                if (isPlaying) {
                    currentPlayListener.stopPlayVoice();
                } else {
                    playVoice(FileDownloadUtils.getDefaultSaveRootPath() + voiceBean.getLoadPath());
                }

            } else {//去下载
                loadVoiceFile();
            }
        } else {//本地录音
            if (isPlaying) {
                currentPlayListener.stopPlayVoice();
            } else {
                playVoice(voiceBean.getPath());
            }
        }


    }

    private void loadVoiceFile() {
        Log.e("aaa", "下载loadVoiceFile  " + FileDownloadUtils.getDefaultSaveRootPath());
        Log.e("aaa", "下载lvoiceBean.getPath()  " + voiceBean.getPath());

        FileDownloader.getImpl().create(Mate.PIC_PATH + voiceBean.getLoadPath())
                .setPath(FileDownloadUtils.getDefaultSaveRootPath() + voiceBean.getLoadPath())
                .setFinishListener(new BaseDownloadTask.FinishListener() {
                    @Override
                    public void over() {

                        voiceBean.setPath(FileDownloadUtils.getDefaultSaveRootPath() + voiceBean.getLoadPath());
                        playVoice(FileDownloadUtils.getDefaultSaveRootPath() + voiceBean.getLoadPath());
                    }
                }).start();
    }

    public void playVoice(String filePath) {
        if (!(new File(filePath).exists())) {

            return;
        }
        Log.e("aaa", "播放点击了   " + voiceBean.getPath());

        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);

        mediaPlayer = new MediaPlayer();
        // if (EaseUI.getInstance().getSettingsProvider().isSpeakerOpened()) {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
        /*} else {
            audioManager.setSpeakerphoneOn(false);// 关闭扬声器
            // 把声音设定成Earpiece（听筒）出来，设定为正在通话中
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }*/
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mediaPlayer.release();
                    mediaPlayer = null;
                    stopPlayVoice(); // stop animation
                }

            });
            isPlaying = true;
            currentPlayListener = this;
            mediaPlayer.start();
            showAnimation();

        } catch (Exception e) {
            System.out.println();
        }
    }

    // show the voice playing animation
    private void showAnimation() {
        // play voice, and start animation
        voiceIconView.setImageResource(R.drawable.voice_from_icon);
        //  voiceIconView.setImageResource(R.anim.voice_to_icon);
        voiceAnimation = (AnimationDrawable) voiceIconView.getDrawable();
        voiceAnimation.start();
    }

    public void stopPlayVoice() {
        if (voiceAnimation != null)
            voiceAnimation.stop();
        voiceIconView.setImageResource(R.drawable.ease_chatfrom_voice_playing);
       /* } else {
            voiceIconView.setImageResource(R.drawable.ease_chatto_voice_playing);
        }*/
        // stop play voice
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        isPlaying = false;
    }
}
