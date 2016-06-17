package javalj.com.ljcustomview;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.javalj.RefreshLayout.LBaseSwipeRefreshLayout;
import com.javalj.RefreshLayout.LListView;
import com.javalj.RefreshLayout.impl.OnLoadMoreLinstener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LListView lListView;
    private LBaseSwipeRefreshLayout baseSwipeRefreshLayout;
    private List<String> mlist = new ArrayList<>();
    ArrayAdapter<String> stringArrayAdapter;
    private android.os.Handler h = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lListView = (LListView) findViewById(R.id.list);
        baseSwipeRefreshLayout = (LBaseSwipeRefreshLayout) findViewById(R.id.swp);
        LinearLayout v = (LinearLayout) findViewById(R.id.ept);
        ViewGroup parent = (ViewGroup) lListView.getParent();
        parent.addView(v, 2);
        lListView.setEmptyView(v);
        lListView.setlBaseSwipeRefreshLayout(baseSwipeRefreshLayout);
        lListView.setOnLoadMoreLinstener(new OnLoadMoreLinstener() {
            @Override
            public void loadMore(boolean isrefresh) {
                laodMoreData();

                Log.d("xxxx", "loadMore" + isrefresh);
            }

            @Override
            public void onRefresh() {
                mlist.clear();
                stringArrayAdapter.notifyDataSetChanged();
                Log.d("xxxx", "onRefresh");
                laodMoreData();
            }
        });
        //  laodMoreData();
        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mlist);
        lListView.setAdapter(stringArrayAdapter);
        stringArrayAdapter.notifyDataSetChanged();
    }

    private void laodMoreData() {
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    list.add(i + "......." + String.valueOf((new Random()).nextInt()));
                }
                mlist.addAll(list);
                stringArrayAdapter.notifyDataSetChanged();
                lListView.setRefresh(false);
            }
        }, 3000);

    }

}
