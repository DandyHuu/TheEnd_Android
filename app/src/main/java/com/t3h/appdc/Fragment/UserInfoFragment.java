package com.t3h.appdc.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.t3h.appdc.Const;
import com.t3h.appdc.LoginActivity;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;

public class UserInfoFragment extends Fragment implements View.OnClickListener {
    private EditText tvNameUser, tvEmailUser, tvPhoneUser, tvBirthUser, tvFaceUser , tvAddress;
    private Button  btnUpdate, btnLogOut;
    private ProgressDialog progressDialog;
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
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wating...");

        tvNameUser = getActivity().findViewById(R.id.tv_name_user);
        tvEmailUser = getActivity().findViewById(R.id.tv_email_user);
        tvPhoneUser = getActivity().findViewById(R.id.tv_phone_user);
        tvBirthUser = getActivity().findViewById(R.id.tv_birth_user);
        tvFaceUser = getActivity().findViewById(R.id.tv_facebook_user);
        tvAddress = getActivity().findViewById(R.id.tv_address_user);

        Intent get = getActivity().getIntent();
        tvNameUser.setText(get.getStringExtra(Const.EXTRA_USERNAME));
        tvEmailUser.setText(get.getStringExtra(Const.EXTRA_EMAIL));
        tvPhoneUser.setText(get.getStringExtra(Const.EXTRA_PHONE));
        tvAddress.setText(get.getStringExtra(Const.EXTRA_ADDRESS));
        tvBirthUser.setText(get.getStringExtra(Const.EXTRA_BIRTH));
        tvFaceUser.setText(get.getStringExtra(Const.EXTRA_PHONE));

        btnUpdate = getActivity().findViewById(R.id.btn_updateFace);
        btnLogOut = getActivity().findViewById(R.id.btn_logOut);


        btnUpdate.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);



    }

//    @Override
//    public void onResume() {
//        MainActivity m = (MainActivity) getActivity();
//        m.setNoActibve();
//        m.getImgbtnUser().setImageResource(R.drawable.profilesactive);
//        super.onResume();
//    }

    @Override
    public void onClick(View view) {
        progressDialog.show();

        switch (view.getId()){

            case R.id.btn_updateFace:
                DialogUserFragment frm = new DialogUserFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                frm.show(ft,DialogUserFragment.TAG);

                break;
            case R.id.btn_logOut:

                Intent login = new Intent(getActivity(),LoginActivity.class);
                login.putExtra(Const.EXTRA_PASS," ");
                startActivity(login);

                MainActivity mainActivity = new MainActivity();
                mainActivity.finish();
                break;
            default:
                break;
        }
        progressDialog.dismiss();

    }
}
