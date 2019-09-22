package com.t3h.appdc;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.t3h.appdc.Fragment.LoginFragment;
import com.t3h.appdc.Fragment.ResgeterFragment;

public class LoginActivity extends AppCompatActivity {
    private LoginFragment frmLogin = new LoginFragment();
    private ResgeterFragment frmResgeter = new ResgeterFragment();

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
}
