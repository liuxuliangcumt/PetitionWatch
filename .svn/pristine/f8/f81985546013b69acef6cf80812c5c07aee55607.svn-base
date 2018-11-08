package com.realpower.petition.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petition.R;
import com.realpower.petition.util.GlideRoundTransform;
import com.realpower.petition.util.GlideUtil;
import com.realpower.petition.util.MyVideoApkThumbLoader;

import java.security.MessageDigest;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

/**
 * Created by Administrator on 2017/11/21.
 */

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private List<LocalMedia> data;
    public boolean isDetail = false;

    public VideoAdapter(Context context, List<LocalMedia> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (isDetail) {
            return data.size();
        } else {

            if (data.size() >= 3) {
                return 3;
            } else {
                return data.size() + 1;
            }
        }
    }

    @Override
    public Object getItem(int position) {
        if (data.size() == 0) {
            return null;
        } else {
            return data.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_grid_video, null);
            holder = new ViewHolder();
            holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RequestOptions options = new RequestOptions();
        options.transform(new GlideRoundTransform(20));

        if (position + 1 > data.size() & !isDetail) {
            holder.iv_delete.setVisibility(View.GONE);
            options.fitCenter();
            Glide.with(context).load(R.mipmap.add_video_normal).apply(options).into(holder.iv_pic);
        } else {
            options.centerCrop();

            if (isDetail) {
                holder.iv_pic.setImageDrawable(context.getDrawable(R.drawable.iv_loading));
                GlideUtil.loadVideoScreenshot(context, data.get(position).getPath(), holder.iv_pic);
                holder.iv_delete.setVisibility(View.GONE);
            } else {
                holder.iv_delete.setVisibility(View.VISIBLE);
                Glide.with(context).load(data.get(position).getPath()).into(holder.iv_pic);
                holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onDeleteClickLisnter != null) {
                            onDeleteClickLisnter.onDeleteClick(position);
                        }
                    }
                });
            }
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_delete, iv_pic;
    }

    public void setOnDeleteClickLisnter(OnDeleteClickLisnter lisnter) {
        onDeleteClickLisnter = lisnter;
    }

    private OnDeleteClickLisnter onDeleteClickLisnter;

    public interface OnDeleteClickLisnter {
        void onDeleteClick(int position);
    }


}
