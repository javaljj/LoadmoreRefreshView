package javalj.com.customviewlibrary.viewgroups;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lijie on 2016/6/15.
 * 自动换行的layout
 */
public class DemoLayout extends ViewGroup {
    private int mVerticalSpacing; //vertical spacing
    private int mHorizontalSpacing; //horizontal spacing

    public DemoLayout(Context context) {
        super(context);
    }

    public DemoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //保存所有child view
    private final List<List<View>> mLines = new ArrayList<>();
    //保存所有行高
    private final List<Integer> mLineHeights = new ArrayList<>();
    //保存所有行宽
    private final List<Integer> mLineWidths = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mLines.clear();
        mLineHeights.clear();
        mLineWidths.clear();
        //不解释
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int widthUsed = getPaddingLeft() + getPaddingRight() + mHorizontalSpacing;
        int lineWidth = widthUsed;
        int lineHeight = 0;

        int childCount = getChildCount();
        List<View> lineViews = new ArrayList<>();


        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            //测量每个child的宽高，每个child可用的最大宽高为sizeWidth-spacing-padding-margin
            measureChildWithMargins(child, widthMeasureSpec, mHorizontalSpacing * 2,
                    heightMeasureSpec, mVerticalSpacing * 2);

          //  int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
          //  int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //判断这一行是否还能容下这个child
//            if (lineWidth + childWidth + mHorizontalSpacing > sizeWidth) {
//                //需要换行，则记录这一行的宽度，高度，下一行的初始宽度，初始高度
//
//                mLineWidths.add(lineWidth);
//                lineWidth = widthUsed + childWidth + mHorizontalSpacing;
//
//                mLineHeights.add(lineHeight);
//                lineHeight = childHeight;
//
//                mLines.add(lineViews);
//                lineViews = new ArrayList<>();
//            } else {//容得下，则累加这一行的宽度，记录这一行的高度
//                lineWidth += childWidth + mHorizontalSpacing;
//                lineHeight = Math.max(lineHeight, childHeight);
//            }

            lineViews.add(child);


        }
        //最后一行的处理
        mLineHeights.add(lineHeight);
        mLineWidths.add(lineWidth);
        mLines.add(lineViews);

        int maxWidth = Collections.max(mLineWidths);

//        processChildHeights();//计算所有行的累积高度
//        int totalHeight = getChildHeights();
//
//        //TODO 处理getMinimumWidth/height的情况
//
//        //设置自身的测量宽高
//        setMeasuredDimension(
//                (modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : Math.min(maxWidth, sizeWidth),
//                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : Math.min(totalHeight, sizeHeight));
//
//        //重新测量child的lp.height为MATCH_PARENT时的child的尺寸
//        remeasureChild(widthMeasureSpec);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
