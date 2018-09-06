package com.realpower.petitionwatch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class CustomListView extends ListView {

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (height != 0) {
            super.onSizeChanged(w, height, oldw, oldh);
        } else {
            super.onSizeChanged(w, h, oldw, oldh);
        }
    }

    int height = 0;

    public void setHeight(int height) {
        this.height = height;
    }

}
