package com.mike.demo.ui.mainviewpager.familyfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.mike.demo.base.BaseActivity;
import com.mike.demo.databinding.GridViewItemBinding;

public class GridviewAdapter extends BaseAdapter {
    Context context;
    int resource;
    String[] names;
    int[] icons;

    public GridviewAdapter(Context context, int resource, String[] names, int[] icons) {
        this.context = context;
        this.resource = resource;
        this.names = names;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
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

        viewHolder.imageView.setImageResource(icons[position]);
        viewHolder.textView.setText(names[position]);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
