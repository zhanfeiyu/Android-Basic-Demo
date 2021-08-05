package com.mike.demo.classification_fragments.marketfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.mike.demo.database.Commodity;
import com.mike.demo.databinding.GridViewItemBinding;

import java.util.ArrayList;
import java.util.List;

public class GridviewAdapter extends BaseAdapter {
    Context context;
    int resource;
    List<Commodity> commodities = new ArrayList<>();

    public GridviewAdapter(Context context, int resource, List<Commodity> commodities) {
        this.context = context;
        this.resource = resource;
        this.commodities.addAll(commodities);
    }

    public void updateCommodities(List<Commodity> commodities) {
        this.commodities.clear();
        this.commodities.addAll(commodities);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return commodities.size();
    }

    @Override
    public Object getItem(int position) {
        return commodities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    GridViewItemBinding binding;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            //convertView = LayoutInflater.from(context).inflate(resource, parent, false);

            binding = DataBindingUtil.inflate(LayoutInflater.from(context), resource, parent, false);
            convertView = binding.getRoot();
            viewHolder.imageView = binding.ivGridImage;
            viewHolder.textView = binding.twGridView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Commodity commodity = commodities.get(position);
        viewHolder.imageView.setImageResource(commodity.getImageId());
        viewHolder.textView.setText(commodity.getName() + "/" + commodity.getPrice());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
