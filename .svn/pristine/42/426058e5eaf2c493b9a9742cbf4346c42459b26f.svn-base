package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelwatch.bean.StatusListBean;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;

import java.util.List;

public class TaskDetailRemarkAdapter extends MyBaseAdapter< StatusListBean> {
    public TaskDetailRemarkAdapter(Context context, List<StatusListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_taskdetail_remarks, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.content = (TextView) convertView.findViewById(R.id.tv_content);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.time.setText(data.get(position).getLasttime());
        holder.content.setText(data.get(position).getDescription());
        return convertView;
    }

    private class ViewHolder {
        TextView time, content;
    }
}
