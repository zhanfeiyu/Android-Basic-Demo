package com.mike.demo.classification_fragments.marketfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mike.demo.R;
import com.mike.demo.database.DBEngine;
import com.mike.demo.database.DBQueryFinishListener;
import com.mike.demo.databinding.LayoutMarketPriceFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import com.mike.demo.database.Commodity;

public class MarketPriceFragment extends Fragment {
    private final static String TAG = MarketPriceFragment.class.getSimpleName();
    View root;
    LayoutMarketPriceFragmentBinding binding;
    List<Commodity> commodities = new ArrayList<>();
    GridviewAdapter adapter;
    DBEngine dbEngine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbEngine = new DBEngine(getContext());
        dbEngine.setQueryFinishListener(new DBQueryFinishListener() {
            @Override
            public void queryFinishSuccessfully(Object data) {
                commodities = (List<Commodity>) data;

                // 如果第一次查询，数据库是空的，initCommodities给它赋初值
                if (commodities.size() == 0) {
                    Log.d(TAG, "data base is empty");
                    initCommodities();
                } else {
                    // 查询到的就是最新的
                }

                initGridView();  //使用查询到的或初始值初始化UI
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_market_price_fragment, container, false);
        root = binding.getRoot();
        //initCommodities();
        dbEngine.queryCommodities();
        return root;
    }

    private void initGridView() {
        adapter = new GridviewAdapter(getActivity(), R.layout.grid_view_item, commodities);
        binding.familyFragmentGridview.setAdapter(adapter);

        binding.familyFragmentGridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final EditText editText = new EditText(getActivity());
                final TextView textView = view.findViewById(R.id.tw_grid_view);
                String text = textView.getText().toString();
                String price = text.split("/")[1];
                Log.d(TAG, "previous price: " + price + " ,position = " + position);
                editText.setText(price);
                new AlertDialog.Builder(getActivity())
                        .setTitle("修改最新商品价格")
                        .setMessage("修改价格, 只能是数值形式")
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String string = editText.getText().toString();
                                Log.d(TAG, "new price string = " + string);
                                float newPrice = Float.parseFloat(string);
                                Log.d(TAG, "new price value = " + newPrice);
                                /*getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(string);

                                    }
                                });*/
                                updateCommodities(position, newPrice);
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    private void initCommodities() {
        /*commodities.clear();*/
        commodities.add(new Commodity(R.drawable.carbbage, "卷心菜", 2.5f));
        commodities.add(new Commodity(R.drawable.eggplant, "茄子", 3.8f));
        commodities.add(new Commodity(R.drawable.orange, "橙子", 5f));
        commodities.add(new Commodity(R.drawable.pear, "梨子", 4.8f));
        commodities.add(new Commodity(R.drawable.potato, "西红柿", 3.98f));
        commodities.add(new Commodity(R.drawable.redapple, "红苹果", 4.98f));
        commodities.add(new Commodity(R.drawable.pumpkin, "南瓜", 3.98f));
        commodities.add(new Commodity(R.drawable.grape, "葡萄", 7.98f));
        commodities.add(new Commodity(R.drawable.pineapple, "菠萝", 3.5f));
        commodities.add(new Commodity(R.drawable.strawberry, "草莓", 15f));
        commodities.add(new Commodity(R.drawable.corn, "玉米", 2.5f));
        commodities.add(new Commodity(R.drawable.tomato, "土豆", 2.98f));
        commodities.add(new Commodity(R.drawable.chilli, "辣椒", 15f));
        commodities.add(new Commodity(R.drawable.xigua, "西瓜", 2.5f));

        for (Commodity commodity : commodities) {
            dbEngine.insertCommodities(commodity);
        }
    }

    private void updateCommodities(int position, float newPrice) {
        Log.d(TAG, "position = " + position + " ,newPrice = " + newPrice);

        Commodity updatedCommodity = commodities.get(position);
        updatedCommodity.setPrice(newPrice);
        commodities.set(position, updatedCommodity);

        if (adapter != null) {
            adapter.updateCommodities(commodities);
            adapter.notifyDataSetChanged();
        }

        dbEngine.updateCommodities(updatedCommodity);
    }
}
