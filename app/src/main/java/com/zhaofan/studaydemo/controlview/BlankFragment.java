package com.zhaofan.studaydemo.controlview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhaofan.studaydemo.R;


public class BlankFragment extends Fragment {

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(int num){
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num_key",num);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.tv_fragment);
        Bundle bundle = getArguments();
        if (bundle!=null){
            int num = bundle.getInt("num_key");
            textView.setText("fragment"+num);
        }

    }
}
