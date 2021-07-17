package com.mike.demo.ui.mainviewpager.familyfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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

public class FamilyFragment extends Fragment {
    String TAG = FamilyFragment.class.getSimpleName();
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

    FragmentFamilyBinding binding;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_family, container, false);
        View view = binding.getRoot();
        initGridView();
        return view;
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


}