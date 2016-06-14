package com.javalj.RefreshLayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by lijie on 2016/6/14.
 */
public class LBaseSwipeRefreshLayout extends SwipeRefreshLayout {

    private int mTouchSlop;
    private float mPrevX;
    // Indicate if we've already declined the move event
    private boolean mDeclined;

    public LBaseSwipeRefreshLayout(Context context) {
        super(context);
    }

    public LBaseSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColor();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 判断当前是的动作是horiz,
     *
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = MotionEvent.obtain(event).getX();
                mDeclined = false; // New action
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevX);

                if (mDeclined || xDiff > mTouchSlop) {
                    mDeclined = true; // Memorize
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }

    public void setColor() {
        setColorSchemeResources(
                android.R.color.holo_green_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_purple,
                android.R.color.holo_blue_dark
        );

    }
}
