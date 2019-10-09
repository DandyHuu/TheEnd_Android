package com.t3h.appdc.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.appdc.Const;
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
    private ArrayList<Pets> dataNews;
    private NewsAdapter adapter;
    private Api api;
    private ProgressBar progressBar;
    private NewsAdapter.RecyclerViewClickListener listener;

    private EditText edPost;
    private ImageView imUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        edPost = getActivity().findViewById(R.id.ed_post);
        imUser = getActivity().findViewById(R.id.img_user_post);
        Intent get = getActivity().getIntent();
        edPost.setFocusable(false);

        Glide.with(imUser)
                .load(get.getStringExtra(Const.EXTRA_AVARTAR))
                .placeholder(R.drawable.ic_adb_black_24dp)
                .error(R.drawable.avatar_dog)
                .into(imUser);

        adapter = new NewsAdapter(getContext());
        rvNews = getActivity().findViewById(R.id.rv_news);
        rvNews.setAdapter(adapter);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        MainActivity m = (MainActivity) getActivity();
//        m.setNoActibve();
//        m.getImgbtnHome().setImageResource(R.drawable.homeactive);
//    }

    private void initData() {
        ApiBuilder.getInstance().getPost().enqueue(new Callback<ArrayList<Pets>>() {
            @Override
            public void onResponse(Call<ArrayList<Pets>> call, Response<ArrayList<Pets>> response) {
                dataNews = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Pets>> call, Throwable t) {

            }
        });
        adapter.setData(dataNews);
    }

    public void setDataNews(ArrayList<Pets> data) {
        this.adapter.setData(data);
        this.dataNews = data;

    }

    public void getPets(){

    }
}
