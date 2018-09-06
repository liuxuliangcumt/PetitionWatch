package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.modelwatch.bean.TaskDetailPicBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 */

public class TaskDetailPicAdapter extends BaseAdapter {

    private Context context;
    private List<TaskDetailPicBean> data;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_task, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {
        TextView time;
        ImageView icon;
    }
}
