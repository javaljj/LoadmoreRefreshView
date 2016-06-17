package com.javalj.RefreshLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.javalj.R;

/**
 * Created by lijie on 2016/6/14.
 */
public class EmptyView extends FrameLayout {
    private View view;


    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.empty_view, null);
        addView(view);
    }
}
