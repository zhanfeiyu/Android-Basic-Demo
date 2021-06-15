package com.mike.demo.patternusage.wrapperpattern;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WrapRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = WrapRecyclerViewAdapter.class.getSimpleName();
    private RecyclerView.Adapter mRealAdapter;
    ArrayList<View> headerViews;
    ArrayList<View> footerViews;

    public WrapRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRealAdapter = adapter;
        mRealAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
        headerViews = new ArrayList<>();
        footerViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) { //int viewType
        Log.d(TAG, "onCreateViewHolder, position = " + position);
        int headerCount = headerViews.size();
        if (position < headerCount) {
            return new RecyclerView.ViewHolder(headerViews.get(position)) {
            };
        }
        // mRealAdapter 返回 mRealAdapter的ViewHolder
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
        Log.d(TAG, "onBindViewHolder, position = " + position);
        int headerCount = headerViews.size();
        //头和底部不需处理，mRealAdapter需要处理
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

    public void addHeaderViews(View view) {
        if (!headerViews.contains(view)) {
            headerViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFooterViews(View view) {
        if (!footerViews.contains(view)) {
            footerViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderViews(View view) {
        if (headerViews.contains(view)) {
            headerViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public void removeFooterViews(View view) {
        if (footerViews.contains(view)) {
            footerViews.remove(view);
            notifyDataSetChanged();
        }
    }
}
