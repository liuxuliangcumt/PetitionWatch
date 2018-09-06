package com.realpower.petitionwatch.modelcity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.MyBaseAdapter;
import com.realpower.petitionwatch.modelcity.bean.TaskBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class CityTaskAdapter extends MyBaseAdapter<TaskBean.ListBean> {

    public CityTaskAdapter(Context context, List<TaskBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolde holde = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_citytask, null);
            holde = new ViewHolde();
            convertView.setTag(holde);
            holde.name = (TextView) convertView.findViewById(R.id.tv_name);
            holde.time = (TextView) convertView.findViewById(R.id.tv_time);
            holde.content = (TextView) convertView.findViewById(R.id.tv_content);
            holde.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        } else {
            holde = (ViewHolde) convertView.getTag();
        }
        holde.name.setText("执行人:" + data.get(position).getDistrict().getDistrictRealname());
        holde.tv_title.setText("任务标题:"+data.get(position).getTitle());
        holde.content.setText("工作内容:" + data.get(position).getInfo());
        holde.time.setText(data.get(position).getStarttime() + "---" + data.get(position).getEndtime());
        return convertView;
    }

    private class ViewHolde {
        TextView name, time, content, tv_title;

    }
}
