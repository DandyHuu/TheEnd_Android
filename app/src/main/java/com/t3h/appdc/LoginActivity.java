package com.t3h.appdc;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.t3h.appdc.Fragment.LoginFragment;
import com.t3h.appdc.Fragment.ResgeterFragment;

public class LoginActivity extends AppCompatActivity {
    private LoginFragment frmLogin = new LoginFragment();
    private ResgeterFragment frmResgeter = new ResgeterFragment();
    private Fragment[] fragments = {frmLogin,frmResgeter};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dangnhap);
        initFragment();
        showFragment(frmLogin);
    }

    private void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel,frmResgeter);
        transaction.add(R.id.panel,frmLogin);
        transaction.commit();
    }

    public void showFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//        fragmentTransaction.replace(R.id.panel,fragment);
        //hide all fragment
        fragmentTransaction.hide(frmLogin);
        fragmentTransaction.hide(frmResgeter);
        //show fragment need display
        fragmentTransaction.show(fragment);

        fragmentTransaction.commit();
    }

    public LoginFragment getFrmLogin() {
        return frmLogin;
    }

    public ResgeterFragment getFrmResgeter() {
        return frmResgeter;
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
//        for(Fragment f : fragments){
//            if(f != null && f instanceof ResgeterFragment) {
//                ((ResgeterFragment) f).onBackPressed();
//                return;
//            }
//        }
        super.onBackPressed();

    }

    //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onClick(View view) {
//        if (view == btnRegister) {
//            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//            Pair[] pairs = new Pair[1];
//            pairs[0] = new Pair<View,String> (tvLogin,"login");
//            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
//            startActivity(intent,activityOptions.toBundle());
//        }
//    }
}
