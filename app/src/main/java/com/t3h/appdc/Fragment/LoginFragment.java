package com.t3h.appdc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.appdc.LoginActivity;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.slide.HomeActivity;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText edUser, edPass;
    private Button btnLogin, btnResgeter;

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
        edUser = getActivity().findViewById(R.id.ed_username);
        edPass = getActivity().findViewById(R.id.ed_pass);

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


                Intent main = new Intent(getActivity(), HomeActivity.class);
                startActivity(main);

                LoginActivity loginFinish = new LoginActivity();
                loginFinish.finish();

                break;
            case R.id.btn_dangky:
                LoginActivity login = (LoginActivity) getActivity();
                login.showFragment(login.getFrmResgeter());
                break;
            default:
                break;
        }
    }
}
