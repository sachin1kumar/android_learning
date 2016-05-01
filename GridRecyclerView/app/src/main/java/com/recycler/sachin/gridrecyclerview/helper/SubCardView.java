package com.recycler.sachin.gridrecyclerview.helper;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by SACHIN on 01-May-16.
 */
public class SubCardView extends android.support.v7.widget.CardView {

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
    }

    public SubCardView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public SubCardView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

    }

    public SubCardView(Context context)
    {
        super(context);
    }
}
