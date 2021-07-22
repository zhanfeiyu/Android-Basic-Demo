package com.mike.demo.ui.mainviewpager.familyfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.mike.demo.R;
import com.mike.demo.databinding.FragmentFamilyBinding;
import com.mike.demo.ui.mainviewpager.StudyFragment;

import java.util.ArrayList;
import java.util.List;

public class FamilyFragment extends Fragment {
    String TAG = FamilyFragment.class.getSimpleName();

    public FamilyFragment() {
        // Required empty public constructor
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static FamilyFragment newInstance(String param1, String param2) {
        FamilyFragment fragment = new FamilyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    FragmentFamilyBinding binding;
    List<Classifications> classificationsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_family, container, false);
        View view = binding.getRoot();

        initItemClassifications();
        initRecyclerView();

        initGridView();
        return view;
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerviewFragmentFamily.setLayoutManager(linearLayoutManager);
        FamilyRecyclerAdapter adapter = new FamilyRecyclerAdapter(getActivity(), classificationsList, R.layout.classification_item);
        binding.recyclerviewFragmentFamily.setAdapter(adapter);
    }

    private void initGridView() {
        GridviewAdapter adapter = new GridviewAdapter(getActivity(), R.layout.grid_view_item, names, icons);
        binding.gridviewFamilyFragment.setAdapter(adapter);

        binding.gridviewFamilyFragment.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final EditText editText = new EditText(getActivity());
                final TextView textView = view.findViewById(R.id.tw_grid_view);
                editText.setText(textView.getText().toString());
                new AlertDialog.Builder(getActivity())
                        .setTitle("修改最新商品价格")
                        .setMessage("修改价格")
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String string = editText.getText().toString();
                                Log.d(TAG, "string = " + string);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(string);
                                    }
                                });
                            }
                        })
                        .show();
                return true;
            }
        });
    }


    String[] names = new String[] {
            "卷心菜/2", "茄子/3", "橙子/6", "黄椒/7", "梨子/6.9", "西红柿/3", "红苹果/5", "南瓜/3", "葡萄/6", "菠萝/3.98",
            "草莓/15", "红萝卜/2", "玉米2.5", "土豆/1.7", "辣椒/2.98"
    };

    int[] icons = new int[] {
            R.drawable.carbbage, R.drawable.eggplant, R.drawable.orange, R.drawable.orangepepper,
            R.drawable.pear,R.drawable.potato, R.drawable.redapple, R.drawable.pumpkin, R.drawable.grape
            ,R.drawable.pineapple, R.drawable.strawberry, R.drawable.radish, R.drawable.corn, R.drawable.tomato
            , R.drawable.chilli
    };

    private void initItemClassifications() {
        Classifications c1 = new Classifications(R.drawable.price_coin_2, "菜价助手");
        Classifications c2 = new Classifications(R.drawable.dailypay, "生活缴费");
        Classifications c3 = new Classifications(R.drawable.jingju, "文娱");
        Classifications c4 = new Classifications(R.drawable.photo, "照片");
        Classifications c5 = new Classifications(R.drawable.vedio4, "视频");
        Classifications c6 = new Classifications(R.drawable.music1, "音乐");
        Classifications c7 = new Classifications(R.drawable.notification2, "提醒");
        //Classifications c8 = new Classifications(R.drawable.phone, "手机");
        Classifications c9 = new Classifications(R.drawable.carbmw1, "汽车");

        classificationsList.add(c1);
        classificationsList.add(c2);
        classificationsList.add(c3);
        classificationsList.add(c9);
        classificationsList.add(c4);
        classificationsList.add(c5);
        classificationsList.add(c6);
        classificationsList.add(c7);
        //classificationsList.add(c8);

    }
}