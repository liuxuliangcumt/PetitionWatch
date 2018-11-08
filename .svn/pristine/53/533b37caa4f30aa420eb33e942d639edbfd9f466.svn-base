package com.realpower.petitionwatch.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.photoview.OnPhotoTapListener;
import com.luck.picture.lib.photoview.PhotoView;
import com.realpower.petitionwatch.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity
public class ImageVideoActivity extends BaseActivity {

    @ViewById
    ViewPager vp_video;

    List<View> viewList = new ArrayList<>();

    @Extra
    ArrayList<LocalMedia> mediaList;

    @Extra
    int position;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_video);
        adapter = new MyAdapter();
        vp_video.setAdapter(adapter);
        viewList = new ArrayList<>();
        for (int i = 0; i < mediaList.size(); i++) {
            if (mediaList.get(i).getMimeType() == 1) {
                PhotoView photoView = new PhotoView(this);
                viewList.add(photoView);
            } else {
                View view = View.inflate(this, R.layout.video_viewpager, null);
                viewList.add(view);
            }
        }
        initEvent();
    }

    public void initEvent() {
        //给viewPager设置监听
        vp_video.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < mediaList.size(); i++) {
                    if (mediaList.get(i).getMimeType() == 2) {
                        VideoView videoView = viewList.get(i).findViewById(R.id.video_view);
                        videoView.pause();
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        if (position < mediaList.size())
            vp_video.setCurrentItem(position);
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mediaList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int pos) {
            Log.i("TAG", "pos=" + pos);
            if (mediaList.get(pos).getMimeType() == 1) {//图片
                PhotoView photoView = (PhotoView) viewList.get(pos);
                container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                Glide.with(ImageVideoActivity.this).load(mediaList.get(pos).getPath()).into(photoView);
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);//设置图片显示为充满全屏
                photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(ImageView imageView, float v, float v1) {


                    }


                });
                //            photoView.setOnLongClickListener(longClickListener);


            } else {
                View view1 = viewList.get(pos);
                VideoView videoView = view1.findViewById(R.id.video_view);
                /*int screenWidth = getScreenWidth((Activity) context);
                int videoHight = (int) (screenWidth / (MediaRecorderBase.SMALL_VIDEO_WIDTH / (MediaRecorderBase.SMALL_VIDEO_HEIGHT * 1.0f)));
//                int videoHight = (int) (screenWidth)*4/3;
                videoView.getLayoutParams().height = videoHight;
                videoView.requestLayout();
                View PlayerStatus = view1.findViewById(R.id.play_status);
                View Loading = view1.findViewById(R.id.loading);
                videoView.setOnPreparedListener(ShowBigPictureActivity.this);
                videoView.setOnPlayStateListener(ShowBigPictureActivity.this);
                videoView.setOnErrorListener(ShowBigPictureActivity.this);
                videoView.setOnClickListener(ShowBigPictureActivity.this);
                videoView.setOnInfoListener(ShowBigPictureActivity.this);
                videoView.setOnCompletionListener(ShowBigPictureActivity.this);*/
                container.addView(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

               /* String body = ChatApplication.dbHelper.getVideoSdPath(ImageUrl.get(pos));
                String url[] = ImageUrl.get(pos).substring(ChatUtil.TAG_VIDEO.length()).split("&&");
                //找到是点击聊天界面哪个视频进来的，将局部成员，付给类成员
                if (pos == position) {
                    mVideoView = VideoView;
                    mPlayerStatus = PlayerStatus;
                    mLoading = Loading;
                    setVideoUrl(body, url);
                }*/

                videoView.setMediaController(new MediaController(ImageVideoActivity.this));
                Uri uri = Uri.parse(mediaList.get(pos).getPath());
                videoView.setVideoURI(uri);
            }
            return viewList.get(pos);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
