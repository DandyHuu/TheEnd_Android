package com.t3h.appdc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    Firebase message
    private ArrayList<String> arrToken = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("Notification");

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
        imgbtnHome.setImageResource(R.drawable.homeactive);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Searching...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        getPets();


    }

    private void getPets(){
        ApiBuilder.getInstance().getPets().enqueue(new Callback<ArrayList<Pets>>() {
            @Override
            public void onResponse(Call<ArrayList<Pets>> call, Response<ArrayList<Pets>> response) {
                dataNews=response.body();
                frmNews.setDataNews(dataNews);
                frmShare.setDataShare(dataNews);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<Pets>> call, Throwable t) {
                progressDialog.dismiss();

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
//                if (viewPager != null) {
//                    viewPager.setCurrentItem(2,false);
//                }
                Intent edit = new Intent(this, EditorActivity.class);
                startActivity(edit);
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

    public ImageButton getImgbtnHome() {
        return imgbtnHome;
    }

    public ImageButton getImgbtnPet() {
        return imgbtnPet;
    }

    public ImageButton getImgbtnNew() {
        return imgbtnNew;
    }

    public ImageButton getImgbtnNoti() {
        return imgbtnNoti;
    }

    public ImageButton getImgbtnUser() {
        return imgbtnUser;
    }

    public void getToken(){
        database.getReference("Token").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrToken.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String token = snapshot.getValue(String.class);
                    arrToken.add(token);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void sendFCM(String user, String message){

        try {
            final JSONObject object = new JSONObject();
            JSONArray arr = new JSONArray(arrToken);
            object.put("registration_ids", arr);
            JSONObject notification = new JSONObject();
            notification.put("body", message);
            notification.put("title", user);
            object.put("notification", notification);

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    "https://fcm.googleapis.com/fcm/send",
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String err = new String(error.networkResponse.data);
                            int a = 3;
                        }
                    })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "key=AIzaSyAsjZ8nC459jb-4GNma2StMDTJXOe_KFe8");
                    return headers;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    String s = object.toString();
                    return s.getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
