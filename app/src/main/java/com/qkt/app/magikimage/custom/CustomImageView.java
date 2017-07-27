package com.qkt.app.magikimage.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by qkt on 26/07/2017.
 */

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {
    public CustomImageView(Context context){
        super(context);
    }

    public CustomImageView(Context context, AttributeSet set){
        super(context,set);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,(int)(widthMeasureSpec*3f/4f));
    }
}
