package com.realpower.petitionwatch.modelcounty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcounty.bean.AlarmBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class AlarmListAdapter extends MyBaseAdapter<AlarmBean.ListBean> {
    public AlarmListAdapter(Context context, List<AlarmBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_alarm, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_category = (TextView) convertView.findViewById(R.id.tv_category);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.iv_state = (ImageView) convertView.findViewById(R.id.iv_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_time.setText(data.get(position).getTime());
        holder.tv_name.setText(data.get(position).getMonitoredNames());
        holder.tv_category.setText(data.get(position).getCategory());
        if ("一键报警".equals(data.get(position).getCategory())) {
            holder.tv_category.setText("一键报警");
            holder.tv_category.setTextColor(context.getResources().getColor(R.color.cdb0000));

        } else {
            holder.tv_category.setText("自动报警");
            holder.tv_category.setTextColor(context.getResources().getColor(R.color.ce1a93c));
        }
        if (data.get(position).getCommitStatus() == 1) {
            holder.tv_state.setText("已上报");
            holder.iv_state.setImageDrawable(context.getResources().getDrawable(R.drawable.report_ok));
        } else {
            holder.iv_state.setImageDrawable(context.getResources().getDrawable(R.drawable.no_report));
            holder.tv_state.setText("未上报");
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name, tv_time, tv_state, tv_category;
        ImageView iv_state;
    }
}
