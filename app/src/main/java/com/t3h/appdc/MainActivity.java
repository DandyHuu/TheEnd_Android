package com.t3h.appdc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.t3h.appdc.Fragment.LoginFragment;
import com.t3h.appdc.Fragment.NewsFragment;
import com.t3h.appdc.Fragment.ResgeterFragment;
import com.t3h.appdc.Fragment.ShareFragment;
import com.t3h.appdc.adapter.ViewPagerAdapter;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private ArrayList<Pets> dataNews;

    private NewsFragment frmNews = new NewsFragment();
    private ShareFragment frmShare = new ShareFragment();
    private Fragment[] fragments;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tapPager);
        Fragment[] fms = {
                frmNews,
                frmShare,
        };
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fms);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        getPets();
//        dialog = new Dialog(this,
//                android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
//        dialog.setContentView(R.layout.dialog_progress);
//        dialog.setCancelable(false);
    }

    private void getPets(){
        ApiBuilder.getInstance().getPets().enqueue(new Callback<ArrayList<Pets>>() {
            @Override
            public void onResponse(Call<ArrayList<Pets>> call, Response<ArrayList<Pets>> response) {
                dataNews=response.body();
                frmNews.setData(dataNews);
            }

            @Override
            public void onFailure(Call<ArrayList<Pets>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//        SearchView search = (SearchView) menu.findItem(R.id.menu_search)
//                .getActionView();
//        search.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }
}
