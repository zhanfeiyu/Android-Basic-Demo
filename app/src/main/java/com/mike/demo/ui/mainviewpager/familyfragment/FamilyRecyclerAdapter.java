package com.mike.demo.ui.mainviewpager.familyfragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.demo.R;
import com.mike.demo.databinding.ClassificationItemBinding;

import java.util.List;

public class FamilyRecyclerAdapter extends RecyclerView.Adapter<FamilyRecyclerAdapter.ViewHolder> {
    Context context;
    List<Classifications> classificationsList;
    int layoutId;

    ClassificationItemBinding binding;
    int indicatorTag = 0;  //指示当前显示的是哪一个Recycler item

    public FamilyRecyclerAdapter(Context context, List<Classifications> classificationsList, int layoutId) {
        this.context = context;
        this.classificationsList = classificationsList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);

        View view = LayoutInflater.from(context).inflate(layoutId, parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classifications classification = classificationsList.get(position);
        holder.imageViewIcon.setImageResource(classification.imageId);
        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicatorTag = position;
                notifyDataSetChanged();
                if (itemClickListener != null) {
                    itemClickListener.onClick(indicatorTag);
                }
            }
        });

        holder.textView.setText(classification.getDescription());
        if (position == indicatorTag) {
            holder.textView.setTypeface(Typeface.DEFAULT_BOLD);
            holder.textView.setTextColor(Color.BLUE);
            holder.textView.setTextColor(context.getResources().getColor(R.color.blue_dark_indicator));
            holder.imageViewIndicator.setVisibility(View.VISIBLE);
        } else {
            holder.textView.setTypeface(Typeface.DEFAULT);
            holder.textView.setTextColor(Color.BLACK);
            holder.imageViewIndicator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return classificationsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIcon;
        TextView textView;
        ImageView imageViewIndicator;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewIcon = binding.ivClassification;
            textView = binding.tvClassfication;
            imageViewIndicator = binding.ivClassificationIndicator;
        }
    }

    public interface ItemClickListener {
        public void onClick(int position);
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
