package com.mike.demo.patternusage.wrapperpattern;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WrapRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mRealAdapter;
    ArrayList<View> headerViews;
    ArrayList<View> footerViews;

    public WrapRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRealAdapter = adapter;
        headerViews = new ArrayList<>();
        footerViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) { //int viewType
        int headerCount = headerViews.size();
        if (position < headerCount) {
            return new RecyclerView.ViewHolder(headerViews.get(position)) {
            };
        }

        int adjustPosition = position - headerCount;
        int realCount = mRealAdapter.getItemCount();
        if (adjustPosition < realCount) {
            return mRealAdapter.onCreateViewHolder(parent, adjustPosition);
        }

        return new RecyclerView.ViewHolder(footerViews.get(adjustPosition - realCount)) {
        };
    }

    @Override
    //因为上面的onCreateViewHolder我们需要根据位置来判断，所以这里重写这个方法，直接返回位置作为上面的参数viewType
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int headerCount = headerViews.size();
        if (position < headerCount) {
            return;
        }

        int adjustPosition = position - headerCount;
        int realCount = mRealAdapter.getItemCount();
        if (adjustPosition < realCount) {
            mRealAdapter.onBindViewHolder(holder, adjustPosition);
        }
    }

    @Override
    public int getItemCount() {
        return headerViews.size() + footerViews.size() + mRealAdapter.getItemCount();
    }
}
