package com.mike.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mike.demo.navigatorbuilder.DefaultNavigator;
import com.mike.demo.navigatorbuilder.Navigator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter realAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        testRecyclerView();
    }

    private void testRecyclerView() {
        recyclerView = findViewById(R.id.test_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        realAdapter = new MyAdapter();
        recyclerView.setAdapter(realAdapter);
    }

    private void initUI() {
        ViewGroup root = findViewById(R.id.view_root);
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
            holder.textView.setText("position = " + position);
        }

        @Override
        public int getItemCount() {
            return 10;
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