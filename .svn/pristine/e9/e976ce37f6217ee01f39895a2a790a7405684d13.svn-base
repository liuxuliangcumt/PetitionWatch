package com.realpower.petitionwatch.modelwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.modelwatch.bean.WatcherTaskBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<WatcherTaskBean> data;

    public TaskAdapter(Context context, List<WatcherTaskBean> data) {
        this.context = context;
        this.data = data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_task, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
      /*  SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String startTime = format.format(data.get(position).getStarttime());
        String ednTime = format.format(data.get(position).getEndtime());*/
        holder.tv_name.setText(data.get(position).getMonitored().getMonitoredRealname()+"");
        holder.tv_time.setText(data.get(position).getStarttime()+"  "+data.get(position).getEndtime());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name, tv_time;

    }
}
