package com.t3h.appdc.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.adapter.NewsAdapter;
import com.t3h.appdc.api.Api;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.api.AppData;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment{
    private RecyclerView rvNews;
    private List<Pets> dataNews;
    private NewsAdapter adapter;
    private Api api;
    private ProgressBar progressBar;
    private NewsAdapter.OnClickPet listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        adapter = new NewsAdapter(getContext());
        rvNews = getActivity().findViewById(R.id.rv_news);
        rvNews.setAdapter(adapter);
        adapter.setData(dataNews);
    }

    public void setData(ArrayList<Pets> data) {
        this.dataNews = data;
        adapter.setData(data);
    }

    public void getPets(){

    }
}
