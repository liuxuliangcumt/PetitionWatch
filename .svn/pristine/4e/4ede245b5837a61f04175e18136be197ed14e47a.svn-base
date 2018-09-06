package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.realpower.petitionwatch.MyApplication;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.util.MyVideoApkThumbLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    public boolean isDetail = false;
    private MyVideoApkThumbLoader loader;

    public VideoAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        loader = new MyVideoApkThumbLoader(MyApplication.getInstance());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_grid_pic, null);
            holder = new ViewHolder();
            holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.e("aaa", data.get(position) + "   视频地址");
        if (isDetail) {
            holder.iv_pic.setTag(data.get(position));
            loader.showThumbByAsynctack(data.get(position), holder.iv_pic);
            holder.iv_delete.setVisibility(View.GONE);
        } else {
            Glide.with(context).load(data.get(position)).into(holder.iv_pic);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickLisnter != null) {
                        onDeleteClickLisnter.onDeleteClick(position);
                    }
                }
            });
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
