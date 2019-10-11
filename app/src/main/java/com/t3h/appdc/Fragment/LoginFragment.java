package com.t3h.appdc.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.appdc.Const;
import com.t3h.appdc.LoginActivity;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText edUser, edPass;
    private Button btnLogin, btnResgeter;
    private ArrayList<User> userData;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private CheckBox saveLoginCheckBox;
    private Boolean saveLogin;
    private InputMethodManager imm;
    private ProgressDialog progressDialogLg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        edUser = getActivity().findViewById(R.id.ed_username_lg);
        edPass = getActivity().findViewById(R.id.ed_password_lg);

        btnLogin =  getActivity().findViewById(R.id.btn_dangnhap);
        btnResgeter = getActivity().findViewById(R.id.btn_dangky);

        btnLogin.setOnClickListener(this);
        btnResgeter.setOnClickListener(this);
        saveLoginCheckBox = (CheckBox)getActivity().findViewById(R.id.saveLoginCheckBox);
        LoginActivity login = (LoginActivity) getActivity();
        loginPreferences = login.getLoginPreferences();
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            edUser.setText(loginPreferences.getString("username", ""));
            edPass.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangnhap:
                boolean check = true;
                LoginActivity login = (LoginActivity) getActivity();
                imm = login.getImm();
                imm.hideSoftInputFromWindow(edUser.getWindowToken(), 0);

                String username = edUser.getText().toString().trim();
                String password = edPass.getText().toString().trim();

                if (saveLoginCheckBox.isChecked()) {
                    edUser.setFocusable(false);
                    edPass.setFocusable(false);
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();

                    login(username,password);
                    return;

                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }



//                username.trim();
//                password.trim();
                if (username.isEmpty()) {
                    edUser.requestFocus();
                    edUser.setError("Vui lòng điền tên đăng nhập!");
                    return;
                }
                if (password.isEmpty()) {
                    edPass.requestFocus();
                    edPass.setError("Vui lòng điền mật khẩu!");
                    return;
                }

                login(username,password);
                break;
            case R.id.btn_dangky:
                LoginActivity log = (LoginActivity) getActivity();
                log.showFragment(log.getFrmResgeter());
                break;
            default:
                break;
        }
    }

    public EditText getEdUser() {
        return edUser;
    }

    public EditText getEdPass() {
        return edPass;
    }

    public void login(String username,String password) {
        progressDialogLg = new ProgressDialog(getContext());
        progressDialogLg.setMessage("Wating...");
        progressDialogLg.show();
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialogLg.cancel();
            }
        };

        Handler pdCancel = new Handler();
        pdCancel.postDelayed(progressRunnable, 3000);
        ApiBuilder.getInstance().login(username,password).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userData = response.body();
                if (userData != null && userData.size()> 0 ) {
                    Intent main = new Intent(getActivity(), MainActivity.class);
                    main.putExtra(Const.EXTRA_USERNAME,userData.get(0).getUsername());
                    String fullname = userData.get(0).getFullname();
                    main.putExtra(Const.EXTRA_FULLNAME,fullname);
                    main.putExtra(Const.EXTRA_EMAIL,userData.get(0).getEmail());
                    main.putExtra(Const.EXTRA_ADDRESS,userData.get(0).getAddress());
                    main.putExtra(Const.EXTRA_BIRTH,userData.get(0).getBirth());
                    main.putExtra(Const.EXTRA_PHONE,userData.get(0).getPhone());
                    main.putExtra(Const.EXTRA_FACE,userData.get(0).getFace());
                    main.putExtra(Const.EXTRA_PASS,userData.get(0).getPass());
                    main.putExtra(Const.EXTRA_AVARTAR,userData.get(0).getAvatar());
                    startActivity(main);

                    LoginActivity loginFinish = (LoginActivity) getActivity();
                    loginFinish.finish();
                }
                else {
                    Toast.makeText(getContext(),"Sai tên tài khoản hoặc mật khẩu!(003)",Toast.LENGTH_SHORT).show();
                }
                progressDialogLg.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getContext(),"Sai tên tài khoản hoặc mật khẩu!(001)",Toast.LENGTH_SHORT).show();
                progressDialogLg.dismiss();
                return;
            }
        });
    }
}
