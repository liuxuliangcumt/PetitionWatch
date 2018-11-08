package com.realpower.petition.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petition.R;
import com.realpower.petition.util.GlideCircleTransform;
import com.realpower.petition.util.GlideRoundTransform;

import java.util.List;

/**
 */

public class PicAdapter extends BaseAdapter {
    private Context context;
    private List<LocalMedia> data;
    public boolean isDetail = false;

    public PicAdapter(Context context, List<LocalMedia> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (isDetail) {
            return data.size();
        } else {

            if (data.size() >= 9) {
                return 9;
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
            convertView = View.inflate(context, R.layout.item_grid_pic, null);
            holder = new ViewHolder();
            holder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RequestOptions options = new RequestOptions();
        options.transform(new GlideRoundTransform(20));
        if (position + 1 > data.size() & !isDetail) {
            holder.iv_delete.setVisibility(View.GONE);

            Glide.with(context).load(R.mipmap.add_image_normal).apply(options).into(holder.iv_pic);


        } else {

            Glide.with(context).load(data.get(position).getPath()).apply(options).into(holder.iv_pic);
            if (isDetail) {
                holder.iv_delete.setVisibility(View.GONE);
            } else {
                options.transform(new GlideCircleTransform(context));
                Glide.with(context).load(R.mipmap.del_icon).apply(options).into(holder.iv_delete);
                holder.iv_delete.setVisibility(View.VISIBLE);
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

    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
    }
}
