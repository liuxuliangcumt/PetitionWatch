package com.realpower.petitionwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.CameraBean;

import java.util.List;

/**
 * Created by ruipu on 2018/7/17.
 */

public class CamerasAdapter extends MyBaseAdapter<CameraBean.ListBean> {
    public CamerasAdapter(Context context, List<CameraBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_camera, null);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(data.get(i).getName());
        holder.tv_content.setText("地址："+data.get(i).getAddress());
        return view;
    }

    private class ViewHolder {
        TextView title, tv_content;
    }
}
