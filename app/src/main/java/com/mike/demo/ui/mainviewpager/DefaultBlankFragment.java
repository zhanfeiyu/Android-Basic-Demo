package com.mike.demo.ui.mainviewpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mike.demo.R;

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
            rootView = inflater.inflate(R.layout.fragment_default_blank, container, false);
        }

        initUI();

        return rootView;
    }

    private void initUI() {
        TextView textView = rootView.findViewById(R.id.default_fragment_text);
        textView.setText(mParam1);
    }
}