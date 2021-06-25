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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mike.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DefaultBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefaultBlankFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    View rootView;
    ViewPager viewPager;
    ViewGroup viewGroupIndicators;
    AtomicInteger index = new AtomicInteger();

    ImageView[] indicators;
    ImageView indicator;

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
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.layout_for_main_fragment, container, false);
        }

        initUI();
        return rootView;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int curIndex = msg.what;
            viewPager.setCurrentItem(curIndex);
        }
    };

    private void initUI() {
/*        TextView textView = rootView.findViewById(R.id.default_fragment_text);
        textView.setText(mParam1);*/
        viewPager = rootView.findViewById(R.id.vp_main_fragment);
        viewGroupIndicators = rootView.findViewById(R.id.indicator_for_vp_main_fragment);

        List<ImageView> viewList = new ArrayList<>();
        ImageView imageView1 = new ImageView(getActivity());
/*
        imageView1.setBackgroundResource(R.drawable.birthday_1);
*/
        imageView1.setImageResource(R.drawable.birthday_1);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        viewList.add(imageView1);

        ImageView imageView2 = new ImageView(getActivity());
        imageView2.setImageResource(R.drawable.birthday_2);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        viewList.add(imageView2);

        ImageView imageView3 = new ImageView(getActivity());
        imageView3.setImageResource(R.drawable.birthday_3);
        imageView3.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        viewList.add(imageView3);

        ImageView imageView4 = new ImageView(getActivity());
        imageView4.setImageResource(R.drawable.birthday_4);
        imageView4.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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
            viewGroupIndicators.addView(indicators[i]);
        }



        ViewPagerAdapterForMainFragment adapterForMainFragment = new ViewPagerAdapterForMainFragment(viewList);

        viewPager.setBackgroundColor(getResources().getColor(R.color.antiquewhite));
        viewPager.setAdapter(adapterForMainFragment);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message msg = handler.obtainMessage();
                    msg.what = index.get();
                    handler.sendMessage(msg);

                    index.incrementAndGet();
                    if (index.get() > viewList.size() - 1) {
                        index.getAndAdd(-viewList.size());
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                }


            }
        }).start();


    }
}