package com.t3h.appdc.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.appdc.LoginActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResgeterFragment extends Fragment implements View.OnClickListener, onBackPress {

    private EditText edtUserRe, edtPassRe, edtPhoneRe, edMail;
    private Button btnRes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register, container ,false);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        edtUserRe = getActivity().findViewById(R.id.ed_username_rg);
        edtPassRe = getActivity().findViewById(R.id.ed_password_rg);
        edtPhoneRe = getActivity().findViewById(R.id.ed_phone_rg);
        edMail = getActivity().findViewById(R.id.ed_email_rg);
        btnRes = getActivity().findViewById(R.id.btn_singup_rg);
        btnRes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_singup_rg:
                final String email = edMail.getText().toString();
                final String pass_re = edtPassRe.getText().toString();
                final String phone = edtPhoneRe.getText().toString();
                final String user_re = edtUserRe.getText().toString();
                if (user_re.isEmpty()) {
                    edtUserRe.requestFocus();
                    edtUserRe.setError("Vui lòng điền tên đăng nhập!");
                    return;
                }
                if (pass_re.isEmpty()) {
                    edtPassRe.requestFocus();
                    edtPassRe.setError("Vui lòng điền mật khẩu!");
                    return;
                }
                if (email.isEmpty()) {
                    edMail.requestFocus();
                    edMail.setError("Vui lòng điền email của bạn!");
                    return;
                }
                if (phone.isEmpty()) {
                    edtPhoneRe.requestFocus();
                    edtPhoneRe.setError("Vui lòng điền số điện thoại!");
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Wating...");
                progressDialog.show();
                ApiBuilder.getInstance().regester(user_re,pass_re,email,phone).enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() == true) {
                            Log.i(ResgeterFragment.class.getSimpleName(), response.toString());
                            Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                            LoginActivity login = (LoginActivity) getActivity();
                            login.showFragment(login.getFrmLogin());
                            LoginFragment frmLogin = login.getFrmLogin();
                            frmLogin.getEdUser().setText(user_re);
                            frmLogin.getEdPass().setText(pass_re);
                        }
                        else {
                            Toast.makeText(getContext(),"Lỗi đăng ký tài khoản!(001)",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Lỗi đăng ký tài khoản!(002)",Toast.LENGTH_SHORT).show();
                        return;
                    }

                });



                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        LoginActivity login = (LoginActivity) getActivity();
        login.showFragment(login.getFrmLogin());
    }

}
