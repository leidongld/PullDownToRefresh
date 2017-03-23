package com.example.leidong.pulldowntorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json",
            "HTML"));

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what){
                case REFRESH_COMPLETE:
                    mDatas.addAll(Arrays.asList("123", "456", "789"));
                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();

        mSwipeLayout.setOnRefreshListener(this);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        mListView = (ListView) findViewById(R.id.id_listview);
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
}
