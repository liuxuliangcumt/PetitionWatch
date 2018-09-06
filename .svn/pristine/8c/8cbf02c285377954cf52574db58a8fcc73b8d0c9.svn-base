package com.realpower.petitionwatch.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class MyVideoApkThumbLoader {

    //创建cache
    private LruCache<String, Bitmap> lruCache;
    private LruCache<String, Drawable> drawableLruCache;
    private Context context;

    @SuppressLint("NewApi")
    public MyVideoApkThumbLoader(Context context) {
        this.context = context;
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取最大的运行内存
        int maxSize = maxMemory / 4;//拿到缓存的内存大小 35
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //这个方法会在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };

        drawableLruCache = new LruCache<String, Drawable>(maxSize) {

            @Override
            protected int sizeOf(String key, Drawable value) {
                return super.sizeOf(key, value);

            }
        };

    }


    public void addVideoThumbToCache(String path, Bitmap bitmap) {
        if (getVideoThumbToCache(path) == null) {
            //当前地址没有缓存时，就添加
            lruCache.put(path, bitmap);
        }
    }

    public void addApkThumbToCache(String path, Drawable drawable) {
        if (getApkThumbToCache(path) == null) {
            //当前地址没有缓存时，就添加
            drawableLruCache.put(path, drawable);
        }
    }

    public Drawable getApkThumbToCache(String path) {

        return drawableLruCache.get(path);

    }


    public Bitmap getVideoThumbToCache(String path) {

        return lruCache.get(path);

    }

    public void showThumbByAsynctack(String path, ImageView imgview) {

        if (getVideoThumbToCache(path) == null) {
            //异步加载
            new MyBitmapAsynctack(imgview, path).execute(path);
        } else {
            imgview.setImageBitmap(getVideoThumbToCache(path));
        }

    }

    public void showApkThumbByAsynctack(String path, ImageView imgview) {

        if (getApkThumbToCache(path) == null) {
            //异步加载
            new MyDrawableAsynctask(imgview, path).execute(path);
        } else {
            imgview.setImageDrawable(getApkThumbToCache(path));
        }

    }


    class MyDrawableAsynctask extends AsyncTask<String, Void, Drawable> {
        private ImageView imgView;
        private String path;

        public MyDrawableAsynctask(ImageView imgView, String path) {
            this.imgView = imgView;
            this.path = path;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(params[0],
                    PackageManager.GET_ACTIVITIES);
            if (info != null) {
                ApplicationInfo appInfo = info.applicationInfo;
                appInfo.sourceDir = params[0];
                appInfo.publicSourceDir = params[0];
                try {
                    //加入缓存中
                    if (getApkThumbToCache(params[0]) == null) {
                        addApkThumbToCache(path, appInfo.loadIcon(pm));
                    }
                    return appInfo.loadIcon(pm);

                } catch (OutOfMemoryError e) {
                    Log.e("aaa", e.toString());
                }
            }


            return null;

        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (imgView.getTag().equals(path)) {//通过 Tag可以绑定 图片地址和 imageView，这是解决Listview加载图片错位的解决办法之一
                imgView.setImageDrawable(drawable);
            }
        }
    }

    class MyBitmapAsynctack extends AsyncTask<String, Void, Bitmap> {
        private ImageView imgView;
        private String path;

        public MyBitmapAsynctack(ImageView imageView, String path) {
            this.imgView = imageView;
            this.path = path;
            Log.e("aaa", " MyBitmapAsynctack   " + path);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //这里的创建缩略图方法是调用VideoUtil类的方法，也是通过 android中提供的 ThumbnailUtils.createVideoThumbnail(vidioPath, kind);
            // Bitmap bitmap = VideoUtil.createVideoThumbnail(params[0], 70, 50, MediaStore.Video.Thumbnails.MICRO_KIND);
            Bitmap bitmap = null;
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            int kind = MediaStore.Video.Thumbnails.MINI_KIND;
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    retriever.setDataSource(path, new HashMap<String, String>());
                } else {
                    retriever.setDataSource(path);
                }
                bitmap = retriever.getFrameAtTime();
            } catch (IllegalArgumentException ex) {
                // Assume this is a corrupt video file
            } catch (RuntimeException ex) {
                // Assume this is a corrupt video file.
            } finally {
                try {
                    retriever.release();
                } catch (RuntimeException ex) {
                    // Ignore failures while cleaning up.
                }
            }
            if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, 400, 400,
                        ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            }

            //加入缓存中
            if (getVideoThumbToCache(params[0]) == null) {
                 addVideoThumbToCache(path, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imgView.getTag().equals(path)) {//通过 Tag可以绑定 图片地址和 imageView，这是解决Listview加载图片错位的解决办法之一
                imgView.setImageBitmap(bitmap);
            }
        }
    }
}


