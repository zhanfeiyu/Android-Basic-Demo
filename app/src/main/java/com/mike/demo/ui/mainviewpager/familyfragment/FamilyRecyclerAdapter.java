package com.mike.demo.ui.mainviewpager.familyfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.demo.databinding.ClassificationItemBinding;

import java.util.List;

public class FamilyRecyclerAdapter extends RecyclerView.Adapter<FamilyRecyclerAdapter.ViewHolder> {
    Context context;
    List<Classifications> classificationsList;
    int layoutId;

    ClassificationItemBinding binding;

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
        holder.imageView.setImageResource(classification.imageId);
        holder.textView.setText(classification.getDescription());
    }

    @Override
    public int getItemCount() {
        return classificationsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = binding.ivClassification;
            textView = binding.tvClassfication;
        }
    }
}
