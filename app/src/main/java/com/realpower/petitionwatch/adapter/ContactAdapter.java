package com.realpower.petitionwatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.bean.ContactBean;

import java.util.List;

/**
 */

public class ContactAdapter extends MyBaseAdapter<ContactBean> implements SectionIndexer {
    public ContactAdapter(Context context, List<ContactBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_contact, null);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            holder.part = (TextView) view.findViewById(R.id.tv_part);
            holder.showLetter = (TextView) view.findViewById(R.id.show_letter);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(data.get(i).getSysUser().getNickname());
        holder.part.setText(data.get(i).getSysRole().getName());
        //获得当前position是属于哪个分组
        int sectionForPosition = getSectionForPosition(i);
        //获得该分组第一项的position
        int positionForSection = getPositionForSection(sectionForPosition);
        //查看当前position是不是当前item所在分组的第一个item
        //如果是，则显示showLetter，否则隐藏
        if (i == positionForSection) {
            holder.showLetter.setVisibility(View.VISIBLE);
            holder.showLetter.setText(data.get(i).getFirstLetter());
        } else {
            holder.showLetter.setVisibility(View.GONE);
        }


        return view;
    }

    private class ViewHolder {
        TextView name, part, showLetter;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    //传入一个分组值[A....Z],获得该分组的第一项的position
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getFirstLetter().charAt(0) == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    //传入一个position，获得该position所在的分组
    @Override
    public int getSectionForPosition(int position) {
        if (data.size() == 0) {
            return 0;
        }
        return data.get(position).getFirstLetter().charAt(0);
    }
}
