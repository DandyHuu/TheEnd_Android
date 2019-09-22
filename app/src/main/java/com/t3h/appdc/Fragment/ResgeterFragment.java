package com.t3h.appdc.Fragment;

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
import com.t3h.appdc.R;

public class ResgeterFragment extends Fragment implements View.OnClickListener {

    private EditText edtUserRe, edtPassRe, edtPhoneRe;
    private Button btnRes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container ,false);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        edtUserRe = getActivity().findViewById(R.id.ed_username_re);
        edtPassRe = getActivity().findViewById(R.id.ed_pass_re);
        edtPhoneRe = getActivity().findViewById(R.id.ed_phone_re);

        btnRes = getActivity().findViewById(R.id.btn_dangky_re);
        btnRes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangky_re:
                String pass_re = edtPassRe.getText().toString();
                String phone = edtPhoneRe.getText().toString();
                String user_re = edtUserRe.getText().toString();
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
                if (phone.isEmpty()) {
                    edtPhoneRe.requestFocus();
                    edtPhoneRe.setError("Vui lòng điền số điện thoại!");
                    return;
                }
                LoginActivity login = (LoginActivity) getActivity();
                login.showFragment(login.getFrmLogin());
                break;
            default:
                break;
        }
    }
}
