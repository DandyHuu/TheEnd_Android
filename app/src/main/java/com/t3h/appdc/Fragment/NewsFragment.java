package com.t3h.appdc.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.appdc.Const;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.adapter.HidingScrollListener;
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

public class NewsFragment extends Fragment implements NewsAdapter.RecyclerViewClickListener {
    private RecyclerView rvNews;
    private ArrayList<Pets> dataNews;
    private NewsAdapter adapter;
    private Api api;
    private ProgressBar progressBar;
    private NewsAdapter.RecyclerViewClickListener listener;

    private EditText edPost;
    private ImageView imUser;
    private CardView layout;
    private Handler handler;
    private Toolbar toolbar;
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

//        layout = getActivity().findViewById(R.id.layout_post);

        Glide.with(imUser)
                .load(get.getStringExtra(Const.EXTRA_AVARTAR))
                .placeholder(R.drawable.ic_adb_black_24dp)
                .error(R.drawable.avatar_dog)
                .into(imUser);

        adapter = new NewsAdapter(getContext());
        rvNews = getActivity().findViewById(R.id.rv_news);
        rvNews.setAdapter(adapter);
        adapter.setListener(this);
//        rvNews.setOnScrollListener(new HidingScrollListener() {
//            @Override
//            public void onHide() {
//                hideViews();
//            }
//
//            @Override
//            public void onShow() {
//                showViews();
//            }
//        });

    }


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

    @Override
    public void onRowClick(View view, int position) {
        Toast.makeText(getContext(),"101",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoveClick(View view, int position) {

    }

    @Override
    public void onShareClick(View view, int position) {
//            Toast.makeText(getContext(),"101",Toast.LENGTH_SHORT).show();
            DialogShareFragment frmShare = new DialogShareFragment();
            FragmentTransaction ftshare = getFragmentManager().beginTransaction();
            frmShare.show(ftshare,DialogUserFragment.TAG);
//        Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
    }
}
