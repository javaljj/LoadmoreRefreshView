package javalj.com.ljcustomview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

import java.util.ArrayList;

import butterknife.BindView;
import javalj.com.tabioslibrary.TabIOS;

/**
 * Created by lijie on 2016/6/17.
 */
public class TabIOSsimples extends AppCompatActivity {


    ViewPager customViewpager;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    //    @BindView(R.id.tabhost)
    TabHost tabHost;
    private static final int TAB_NUMBER = 3;
    private static final String[] TAB_PAGE_NICK_NAMES =
            new String[]{"page1", "page2", "page3"};
    private static final int[] TAB_NAMES =
            new int[]{
                    R.string.salary_superman,
                    R.string.peekup_my_salary,
                    R.string.life_service};
    private static String[] TAB_RES_IMG = {"ic_launcher", "ic_launcher", "ic_launcher"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tb_ios);
        butterknife.ButterKnife.bind(this);
        customViewpager = (ViewPager) findViewById(R.id.custom_viewpager);
        customViewpager.setAdapter(new my());
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        TabIOS tabIOS = new TabIOS();
        tabIOS.initDatas(TAB_NUMBER, TAB_NAMES, TAB_PAGE_NICK_NAMES, TAB_RES_IMG);
        tabIOS.initTab(this, tabHost, customViewpager,
                R.color.colorPrimary, R.color.colorAccent, R.layout.tab_item_view,
                R.id.tab_item_icon, R.id.tab_text);


    }

    private class my extends PagerAdapter {
        private ArrayList<ImageView> imageViews = new ArrayList<>();

        public my() {
            for (int i = 0; i < 3; i++) {
                ImageView im = new ImageView(TabIOSsimples.this);

                im.setImageResource(R.mipmap.ic_launcher);
                imageViews.add(im);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }
    }
}
