package com.t3h.appdc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.appdc.R;

public class UserInfoFragment extends Fragment implements View.OnClickListener {
    private TextView tvNameUser, tvEmailUser, tvPhoneUser, tvBirthUser, tvFaceUser;
    private Button btnUpdateEmail, btnUpdatePhone, btnUpdateBirht, btnUpdateFace;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_info,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        tvNameUser = getActivity().findViewById(R.id.tv_name_user);
        tvEmailUser = getActivity().findViewById(R.id.tv_email_user);
        tvPhoneUser = getActivity().findViewById(R.id.tv_phone_user);
        tvBirthUser = getActivity().findViewById(R.id.tv_birth_user);
        tvFaceUser = getActivity().findViewById(R.id.tv_facebook_user);

        btnUpdateEmail = getActivity().findViewById(R.id.btn_updateEmail);
        btnUpdatePhone = getActivity().findViewById(R.id.btn_updatePhone);
        btnUpdateBirht = getActivity().findViewById(R.id.btn_updateBirth);
        btnUpdateFace = getActivity().findViewById(R.id.btn_updateFace);

        btnUpdateEmail.setOnClickListener(this);
        btnUpdatePhone.setOnClickListener(this);
        btnUpdateBirht.setOnClickListener(this);
        btnUpdateFace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_updateEmail:
                break;
            case R.id.btn_updatePhone:
                break;
            case R.id.btn_updateBirth:
                break;
            case R.id.btn_updateFace:
                break;
            default:
                break;
        }
    }
}
