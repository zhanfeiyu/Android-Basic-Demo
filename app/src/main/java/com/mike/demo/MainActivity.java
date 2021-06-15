package com.mike.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mike.demo.navigatorbuilder.DefaultNavigator;
import com.mike.demo.navigatorbuilder.Navigator;
import com.mike.demo.patternusage.wrapperpattern.MyRecyclerView;
import com.mike.demo.patternusage.wrapperpattern.WrapRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MyAdapter realAdapter;
    ViewGroup root;
    View headerView;
    View footerView;
    List<Integer> mItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mItems.add(i);
        }
        initUI();
        //testRecyclerView();
        //testWrappedRecyclerView();
        testMyRecyclerView();
    }

    private void initUI() {
        root = findViewById(R.id.view_root);
        //使用Navigator: 需要知道布局资源的详细信息，id以及资源名
/*        Navigator navigator = new Navigator.Builder(this,
                R.layout.my_navigation_header, root)
                .setText(R.id.tw_back, "回退")
                .setOnClickListener(R.id.tw_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).create();*/

        //如果有属性设置的接口没有提供，可以使用Navigator的findViewById API.
        //TextView textView = navigator.findViewById(R.id.tw_back);
        //textView.setVisibility(View.INVISIBLE);
        //textView.setTextSize((float) 12.0);

        DefaultNavigator defaultNavigator = new DefaultNavigator.Builder(this, root)
                .setLeftText("默认回退")
                .setLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                //.hideLeftText()
                .create();

    }

    //测试系统自带的adapter
/*    private void testRecyclerView() {
        recyclerView = findViewById(R.id.test_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        realAdapter = new MyAdapter();
        recyclerView.setAdapter(realAdapter);
    }*/

    //测试封装好的adapter
/*    private void testWrappedRecyclerView() {
        recyclerView = findViewById(R.id.test_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        realAdapter = new MyAdapter();
        WrapRecyclerViewAdapter adapter = new WrapRecyclerViewAdapter(realAdapter);
        headerView = LayoutInflater.from(this).inflate(R.layout.test_for_header_views, root, false);
        footerView = LayoutInflater.from(this).inflate(R.layout.test_for_footer_views, root, false);

        recyclerView.setAdapter(adapter);
        adapter.addHeaderViews(headerView);
        // 这里如果添加的是使用之前的headerView，会crash：？？，需要重新inflate一个view，
        //java.lang.IllegalStateException: ViewHolder views must not be attached when created.
        // Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)
        adapter.addFooterViews(footerView);

    }*/

    private void testMyRecyclerView() {
        MyRecyclerView myRecyclerView = findViewById(R.id.my_recycler_view);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        realAdapter = new MyAdapter();
        myRecyclerView.setAdapter(realAdapter);
        headerView = LayoutInflater.from(this).inflate(R.layout.test_for_header_views, root, false);
        footerView = LayoutInflater.from(this).inflate(R.layout.test_for_footer_views, root, false);
        myRecyclerView.addFooterViews(footerView);
        myRecyclerView.addHeaderViews(headerView);
    }

    //layout_for_viewholder 如果layout_height 是match_parent， 则listview显示不正常，只显示一个
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.layout_for_viewholder, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder, position = " + position);
            holder.textView.setText("position = " + mItems.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tv_recyclerview);
            }
        }
    }
}