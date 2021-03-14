package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private final String[] mStrings = new String[]{"518021910001", "518021910002", "518021910003", "518021910004","518021910005","518021910006","518021910007"};
    public ListView listView;
    public LottieAnimationView animationView;
    public RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View res = inflater.inflate(R.layout.fragment_placeholder, container, false);
        listView = res.findViewById(R.id.listview);
        animationView = res.findViewById(R.id.animation_view);
        listView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mStrings));
        return res;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                 //TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animationView,
                        "alpha", 1, (float) 0.0);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listView,
                        "alpha", (float) 0.0,1 );
                animator1.setDuration(1000).start();
                animator2.setDuration(1000).start();
            }
        }, 5000);
    }
}
