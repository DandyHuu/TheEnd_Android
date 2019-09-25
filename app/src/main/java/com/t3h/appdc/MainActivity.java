package com.t3h.appdc;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.t3h.appdc.Fragment.EditorFragment;
import com.t3h.appdc.Fragment.LoginFragment;
import com.t3h.appdc.Fragment.NewsFragment;
import com.t3h.appdc.Fragment.NotifiFragment;
import com.t3h.appdc.Fragment.ResgeterFragment;
import com.t3h.appdc.Fragment.ShareFragment;
import com.t3h.appdc.Fragment.UserInfoFragment;
import com.t3h.appdc.adapter.ViewPagerAdapter;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private ArrayList<Pets> dataNews;

    private NewsFragment frmNews = new NewsFragment();
    private ShareFragment frmShare = new ShareFragment();
    private UserInfoFragment frmUser = new UserInfoFragment();
    private NotifiFragment frmNoti = new NotifiFragment();
    private EditorFragment frmEditor = new EditorFragment();

    private Fragment[] fragments;

    private ProgressDialog progressDialog;

    private ImageButton imgbtnHome, imgbtnPet, imgbtnNew, imgbtnNoti, imgbtnUser;
    private LinearLayout lnHome, lnPets, lnNotifi, lnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        drawer = findViewById(R.id.drawer_layout);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toggle = new ActionBarDrawerToggle(
//                this,
//                drawer,
//                R.string.app_name,
//                R.string.app_name);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        ==========================
        viewPager = findViewById(R.id.viewPager);
//        tabLayout = findViewById(R.id.tapPager);
        Fragment[] fms = {
                frmNews,
                frmShare,
                frmEditor,
                frmNoti,
                frmUser

        };
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fms);
        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
        getPets();


        imgbtnHome = findViewById(R.id.imgbtn_home);
        imgbtnPet = findViewById(R.id.imgbtn_pets);
        imgbtnNew = findViewById(R.id.imgbtn_new);
        imgbtnNoti = findViewById(R.id.imgbtn_notifile);
        imgbtnUser = findViewById(R.id.imgbtn_user);

        lnHome = findViewById(R.id.ln_home);
        lnPets = findViewById(R.id.ln_pets);
        lnNotifi = findViewById(R.id.ln_notifi);
        lnUser = findViewById(R.id.ln_user);

        imgbtnHome.setOnClickListener(this);
        imgbtnPet.setOnClickListener(this);
        imgbtnNew.setOnClickListener(this);
        imgbtnNoti.setOnClickListener(this);
        imgbtnUser.setOnClickListener(this);

        lnHome.setOnClickListener(this);
        lnPets.setOnClickListener(this);
        lnNotifi.setOnClickListener(this);
        lnUser.setOnClickListener(this);
    }

    private void getPets(){
        ApiBuilder.getInstance().getPets().enqueue(new Callback<ArrayList<Pets>>() {
            @Override
            public void onResponse(Call<ArrayList<Pets>> call, Response<ArrayList<Pets>> response) {
                dataNews=response.body();
                frmNews.setDataNews(dataNews);
                frmShare.setDataShare(dataNews);
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

    public void setNoActibve(){
        imgbtnHome.setImageResource(R.drawable.home);
        imgbtnUser.setImageResource(R.drawable.profiles);
        imgbtnNoti.setImageResource(R.drawable.notification);
        imgbtnNew.setImageResource(R.drawable.plus);
        imgbtnPet.setImageResource(R.drawable.pawprint);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgbtn_home:
                if (viewPager != null) {
                    viewPager.setCurrentItem(0,false);
                }
                setNoActibve();
                imgbtnHome.setImageResource(R.drawable.homeactive);
                break;
            case R.id.imgbtn_pets:
                if (viewPager != null) {
                    viewPager.setCurrentItem(1,false);
                }
                setNoActibve();
                imgbtnPet.setImageResource(R.drawable.pawprintactive);
                break;
            case R.id.imgbtn_new:
                if (viewPager != null) {
                    viewPager.setCurrentItem(2,false);
                }
            setNoActibve();
                imgbtnNew.setImageResource(R.drawable.plusactive);
                break;
            case R.id.imgbtn_notifile:
                if (viewPager != null) {
                    viewPager.setCurrentItem(3,false);
                }
            setNoActibve();
                imgbtnNoti.setImageResource(R.drawable.notificationactive);
                break;
            case R.id.imgbtn_user:
                if (viewPager != null) {
                    viewPager.setCurrentItem(4,false);
                }
                        setNoActibve();
                imgbtnUser.setImageResource(R.drawable.profilesactive);
                break;

//                Layout Draww
            case R.id.ln_user:
                viewPager.setCurrentItem(4,false);
                setNoActibve();
                imgbtnUser.setImageResource(R.drawable.profilesactive);
                break;
            case R.id.ln_notifi:
                viewPager.setCurrentItem(3,false);
                setNoActibve();
                imgbtnNoti.setImageResource(R.drawable.notificationactive);
                break;
            case R.id.ln_home:
                viewPager.setCurrentItem(0,false);
                setNoActibve();
                imgbtnHome.setImageResource(R.drawable.homeactive);
                break;
            case R.id.ln_pets:
                viewPager.setCurrentItem(1,false);
                setNoActibve();
                imgbtnPet.setImageResource(R.drawable.pawprintactive);
                break;
            default:
                break;
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        }
    }
}
