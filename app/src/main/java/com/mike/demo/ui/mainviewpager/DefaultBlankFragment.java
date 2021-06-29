package com.mike.demo.ui.mainviewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mike.demo.R;
import com.mike.demo.databinding.LayoutForMainFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DefaultBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefaultBlankFragment extends Fragment {
    private String TAG = DefaultBlankFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    ViewPager viewPager;
    ViewGroup viewGroupIndicators;
    AtomicInteger index = new AtomicInteger();

    ImageView[] indicators;
    ImageView indicator;

    LayoutForMainFragmentBinding binding;
    boolean isContinue = true;

    public DefaultBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DefaultBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DefaultBlankFragment newInstance(String param1) {
        DefaultBlankFragment fragment = new DefaultBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 出错情况： binding = DataBindingUtil.setContentView(getActivity(), R.layout.layout_for_main_fragment);
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_for_main_fragment, container, false);
        View view = binding.getRoot();

        initUI();
        return view;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int curIndex = msg.what;
            binding.vpMainFragment.setCurrentItem(curIndex);
        }
    };

    private void initUI() {
        List<ImageView> viewList = new ArrayList<>();
        ImageView imageView1 = new ImageView(getActivity());

        imageView1.setImageResource(R.drawable.birthday_1);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewList.add(imageView1);

        ImageView imageView2 = new ImageView(getActivity());
        imageView2.setImageResource(R.drawable.birthday_2);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewList.add(imageView2);

        ImageView imageView3 = new ImageView(getActivity());
        imageView3.setImageResource(R.drawable.birthday_3);
        imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewList.add(imageView3);

        ImageView imageView4 = new ImageView(getActivity());
        imageView4.setImageResource(R.drawable.birthday_4);
        imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewList.add(imageView4);

        //Indicators
        indicators = new ImageView[viewList.size()];
        for (int i = 0; i < indicators.length; i++) {
            indicator = new ImageView(getActivity());
            indicator.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
            indicator.setPadding(5, 5, 5, 5);
            indicators[i] = indicator;
            if (i == 0) {
                indicators[i].setBackgroundResource(R.drawable.indicator_selected);
            } else {
                indicators[i].setBackgroundResource(R.drawable.indicator_not_selected);
            }
            binding.indicatorForVpMainFragment.addView(indicators[i]);
        }

        ViewPagerAdapterForMainFragment adapterForMainFragment = new ViewPagerAdapterForMainFragment(viewList);

        binding.viewgroupMainViewPager.setBackgroundColor(getResources().getColor(R.color.antiquewhite));
        binding.vpMainFragment.setAdapter(adapterForMainFragment);
        binding.vpMainFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < indicators.length; i++) {
                    if (i == position) {
                        indicators[i].setBackgroundResource(R.drawable.indicator_selected);
                    } else {
                        indicators[i].setBackgroundResource(R.drawable.indicator_not_selected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.vpMainFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        Message msg = handler.obtainMessage();
                        msg.what = index.get();
                        handler.sendMessage(msg);

                        index.incrementAndGet();
                        if (index.get() > viewList.size() - 1) {
                            index.getAndAdd(-viewList.size());
                        }

                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
}