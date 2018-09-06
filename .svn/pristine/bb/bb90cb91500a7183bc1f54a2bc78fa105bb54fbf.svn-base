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

/**
 * Created by Administrator on 2017/12/4.
 */

public class TaskDetailStateAdpter extends MyBaseAdapter<StatusListBean> {
    public boolean isSafeLeve;

    public TaskDetailStateAdpter(Context context, List<StatusListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_taskdetail, null);
            holder = new ViewHolder();
            holder.state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.time.setText(data.get(position).getLasttime());
        if (isSafeLeve) {
            if (data.get(position).getLevel() == 1) {
                holder.state.setText("安全");
                holder.state.setTextColor(context.getResources().getColor(R.color.c909090));
            } else {
                holder.state.setText("危险");
                holder.state.setTextColor(context.getResources().getColor(R.color.ce1a93c));
            }

        } else {
            if (data.get(position).getStatus() == 1) {
                holder.state.setText("外出");
                holder.state.setTextColor(context.getResources().getColor(R.color.ce1a93c));
            } else {
                holder.state.setText("在家");
                holder.state.setTextColor(context.getResources().getColor(R.color.c909090));
            }

        }
        return convertView;
    }

    private class ViewHolder {
        TextView time, state;
    }
}
