package com.t3h.appdc.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login, container, false);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangnhap:
                String username = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if (username.isEmpty()) {
                    edUser.requestFocus();
                    edUser.setError("Vui lòng điền tên đăng nhập!");
                    return;
                }
                if (pass.isEmpty()) {
                    edPass.requestFocus();
                    edPass.setError("Vui lòng điền mật khẩu!");
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Wating...");
                progressDialog.show();
                ApiBuilder.getInstance().login(username,pass).enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                        userData = response.body();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                        Toast.makeText(getContext(),"Sai tên tài khoản hoặc mật khẩu!(001)",Toast.LENGTH_SHORT).show();
                        return;
                    }
                });


                if (userData != null && userData.size()>0 ) {
                    Intent main = new Intent(getActivity(), MainActivity.class);
                    main.putExtra(Const.EXTRA_USERNAME,userData.get(0).getUsername());
                    main.putExtra(Const.EXTRA_EMAIL,userData.get(0).getEmail());
                    main.putExtra(Const.EXTRA_ADDRESS,userData.get(0).getAddress());
                    main.putExtra(Const.EXTRA_BIRTH,userData.get(0).getBirth());
                    main.putExtra(Const.EXTRA_PHONE,userData.get(0).getPhone());
                    main.putExtra(Const.EXTRA_FACE,userData.get(0).getFace());
                    main.putExtra(Const.EXTRA_PASS,userData.get(0).getPass());
                    main.putExtra(Const.EXTRA_AVARTAR,userData.get(0).getAvatar());
                    startActivity(main);

                    LoginActivity loginFinish = new LoginActivity();
                    loginFinish.finish();
                }
                else {
                    Toast.makeText(getContext(),"Sai tên tài khoản hoặc mật khẩu!(003)",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.btn_dangky:
                LoginActivity login = (LoginActivity) getActivity();
                login.showFragment(login.getFrmResgeter());
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
}
