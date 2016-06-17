package com.javalj.RefreshLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by lijie on 2016/6/14.
 */
public class LGridView extends GridView implements AbsListView.OnScrollListener {
    public LGridView(Context context) {
        super(context);
    }

    public LGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
