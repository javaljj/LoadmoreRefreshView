package javalj.com.tabioslibrary;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by lijie on 2016/6/17.
 */
public class TabIOS {
    private static final String TAG = "";
    private static int TAB_NUMBER = 1;
    //tab的资源id,如R.string.fuck
    private static int[] TAB_NAMES = null;
    private static String[] TAB_PAGE_NICK_NAMES = null;
    private static String[] TAB_RES_IMG = null;
    private Activity mContext;
    private TabHost mTabHost;
    private ViewPager mViewPager;
    private int defColor, pressColor;
    private int tv, img;
    private View vTab;
    private int res;

    //先
    public void initDatas(@NonNull int num,
                          @NonNull int[] tabs_names,
                          @NonNull String[] tpna,
                          @NonNull String[] tri) {
        TAB_NUMBER = num;
        TAB_NAMES = tabs_names;
        TAB_PAGE_NICK_NAMES = tpna;
        TAB_RES_IMG = tri;
    }

    //hou
    public void initTab(@NonNull Activity context, @NonNull TabHost tabHost,
                        @NonNull ViewPager v, @NonNull int defColor, @NonNull int pressColor,
                        @NonNull @LayoutRes int res, @NonNull @IdRes int imgViewId, @NonNull @IdRes int tvi) {
        if (context == null ||
                tabHost == null ||
                v == null || res == 0 || imgViewId == 0 || tvi == 0)
            throw new RuntimeException("initTab 有资源为空");
        this.mContext = context;
        this.mTabHost = tabHost;
        this.mViewPager = v;
        this.defColor = defColor;
        this.pressColor = pressColor;
        createTab(res, imgViewId, tvi);
        c();
        tabHost.setCurrentTab(0);
        firstChecked();
        tabHost.setOnTabChangedListener(new LTTabChangeListener());
        mViewPager.addOnPageChangeListener(new LTPageChangeListener());
    }

    private void c() {
        mTabHost.clearAllTabs();
        for (int i = 0; i < TAB_NUMBER; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(TAB_PAGE_NICK_NAMES[i]);
            tabSpec.setIndicator(createTabView(TAB_NAMES[i]));
            tabSpec.setContent(tabContentFactory);
            mTabHost.addTab(tabSpec);
        }
    }

    private TabHost.TabContentFactory tabContentFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            return new View(mContext);
        }
    };

    public void unBind() {
        mContext = null;
    }

    private void firstChecked() {
        for (int i = 0; i < TAB_NUMBER; ++i) {
            TextView tvTabTitle = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(tv);
            int nTitleClr = mContext.getResources().getColor(defColor);
            if (i == 0) {
                nTitleClr = mContext.getResources().getColor(pressColor);
                mViewPager.setCurrentItem(i);
            }
            tvTabTitle.setTextColor(nTitleClr);
        }
    }


    private View createTabView(int resId) {
        View vTab = mContext.getLayoutInflater().inflate(res, null);
        if (img != 0) {
            ImageView vIcon = (ImageView) vTab.findViewById(img);
            int iconResId = 0;
            for (int i = 0; i < TAB_NUMBER; i++) {
                if (resId == TAB_NAMES[i]) {
                    iconResId = mContext.getResources().getIdentifier(TAB_RES_IMG[i], "mipmap", mContext.getPackageName());
                }
            }
            vIcon.setImageResource(iconResId);
        }
        TextView tvTabName = (TextView) vTab.findViewById(tv);
        tvTabName.setText(resId);
        return vTab;
    }

    private void createTab(@LayoutRes int res, @IdRes int imgViewId, @IdRes int tvViewId) {
        this.img = imgViewId;
        this.tv = tvViewId;
        this.res = res;
    }

    private class LTPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mTabHost.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class LTTabChangeListener implements TabHost.OnTabChangeListener {
        @Override
        public void onTabChanged(String tabId) {
            Log.d(TAG, "onTabChanged.tabId: " + tabId);
            for (int i = 0; i < TAB_NUMBER; ++i) {
                if (tv == 0) throw new RuntimeException("资源文件里textView不能为空");
                TextView tvTabTitle = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(tv);
                int nColorText = mContext.getResources().getColor(defColor);
                if (TAB_PAGE_NICK_NAMES[i] == tabId) {
                    nColorText = mContext.getResources().getColor(pressColor);
                    mViewPager.setCurrentItem(i);
                }
                tvTabTitle.setTextColor(nColorText);
            }
        }
    }
}
