package com.javalj.RefreshLayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by lijie on 2016/6/14.
 */
public class LListView extends ListView implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private boolean isRefresh = false;
    private OnLoadMoreLinstener onLoadMoreLinstener;
    private LBaseSwipeRefreshLayout lBaseSwipeRefreshLayout;

    public LListView(Context context) {
        this(context, null);
    }

    public LListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public LBaseSwipeRefreshLayout getlBaseSwipeRefreshLayout() {
        return lBaseSwipeRefreshLayout;
    }

    public void setlBaseSwipeRefreshLayout(LBaseSwipeRefreshLayout lBaseSwipeRefreshLayout) {
        this.lBaseSwipeRefreshLayout = lBaseSwipeRefreshLayout;
        lBaseSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
        lBaseSwipeRefreshLayout.setRefreshing(refresh);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount > 0 && firstVisibleItem + visibleItemCount == totalItemCount) {
            if (isRefresh == false) {
                isRefresh = true;
                lBaseSwipeRefreshLayout.setRefreshing(true);
                onLoadMoreLinstener.loadMore(isRefresh);
            }
        }
    }

    public void setOnLoadMoreLinstener(OnLoadMoreLinstener onLoadMoreLinstener) {
        this.onLoadMoreLinstener = onLoadMoreLinstener;
    }

    @Override
    public void onRefresh() {
        lBaseSwipeRefreshLayout.setRefreshing(true);
        onLoadMoreLinstener.onRefresh();

    }

    public interface OnLoadMoreLinstener {
        void loadMore(boolean isrefresh);

        void onRefresh();
    }
}
