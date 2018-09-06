package com.realpower.petitionwatch.modelcounty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcounty.bean.MyTaskBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class MyTaskAdapter extends MyBaseAdapter<MyTaskBean.ListBean> {
    public MyTaskAdapter(Context context, List<MyTaskBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_mytask, null);
            holder = new ViewHolder();
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_content.setText("任务内容: " + data.get(position).getInfo());
        holder.tv_time.setText("任务日期: " + data.get(position).getStarttime().substring(0, 10)
                + "----" + data.get(position).getEndtime().substring(0, 10));
        return convertView;
    }

    private class ViewHolder {
        TextView tv_time, tv_content;
    }
}
