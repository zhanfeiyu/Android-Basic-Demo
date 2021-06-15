package com.mike.demo.patternusage.wrapperpattern;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends RecyclerView {
    private WrapRecyclerViewAdapter wrapRecyclerViewAdapter;
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        wrapRecyclerViewAdapter = new WrapRecyclerViewAdapter(adapter);
        super.setAdapter(adapter);
    }

    public void addHeaderViews(View view) {
        if (wrapRecyclerViewAdapter != null) {
            wrapRecyclerViewAdapter.addHeaderViews(view);
        }
    }

    public void addFooterViews(View view) {
        if (wrapRecyclerViewAdapter != null) {
            wrapRecyclerViewAdapter.addFooterViews(view);
        }
    }

    public void removeHeaderViews(View view) {
        if (wrapRecyclerViewAdapter != null) {
            wrapRecyclerViewAdapter.removeHeaderViews(view);
        }
    }

    public void removeFooterViews(View view) {
        if (wrapRecyclerViewAdapter != null) {
            wrapRecyclerViewAdapter.removeFooterViews(view);
        }
    }
}
