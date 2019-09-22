package com.t3h.appdc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.appdc.R;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;

public class ShareFragment extends Fragment {
    private RecyclerView rvShare;
    private ArrayList<Pets> dataShare;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_share, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    private void initView() {
//        adapter = new NewsAdapter(getContext());
//        rvNews = getActivity().findViewById(R.id.rv_news);
//        rvNews.setAdapter(adapter);
    }
}
