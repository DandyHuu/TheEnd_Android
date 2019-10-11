package com.t3h.appdc.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.t3h.appdc.Const;
import com.t3h.appdc.LoginActivity;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoFragment extends Fragment implements View.OnClickListener {
    private EditText tvNameUser, tvEmailUser, tvPhoneUser, tvBirthUser, tvFaceUser , tvAddress, tvName;
    private Button  btnUpdate, btnLogOut;
    private ProgressDialog progressDialog;
    private ImageButton imgbtnChange;
    private RelativeLayout rvImage;
    private Bitmap bitmap;
    private CircleImageView imUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_info,container,false);
        return v;
    }

    public CircleImageView getImUser() {
        return imUser;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewInfo();
    }

    public void initViewInfo() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wating...");

        rvImage = getActivity().findViewById(R.id.bg_imguser);
        rvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });
        imUser = getActivity().findViewById(R.id.imgUser);

        tvNameUser = getActivity().findViewById(R.id.tv_name_user);
        tvName = getActivity().findViewById(R.id.tv_name);
        tvEmailUser = getActivity().findViewById(R.id.tv_email_user);
        tvPhoneUser = getActivity().findViewById(R.id.tv_phone_user);
        tvBirthUser = getActivity().findViewById(R.id.tv_birth_user);
        tvFaceUser = getActivity().findViewById(R.id.tv_facebook_user);
        tvAddress = getActivity().findViewById(R.id.tv_address_user);

        imgbtnChange = getActivity().findViewById(R.id.imgbtn_change_pass);

        Intent get = getActivity().getIntent();
        tvNameUser.setText(get.getStringExtra(Const.EXTRA_USERNAME));
        String fullname = get.getStringExtra(Const.EXTRA_FULLNAME);
        tvName.setText(fullname+"");
        tvEmailUser.setText(get.getStringExtra(Const.EXTRA_EMAIL));
        tvPhoneUser.setText(get.getStringExtra(Const.EXTRA_PHONE));
        tvAddress.setText(get.getStringExtra(Const.EXTRA_ADDRESS));
        tvBirthUser.setText(get.getStringExtra(Const.EXTRA_BIRTH));
        tvFaceUser.setText(get.getStringExtra(Const.EXTRA_PHONE));

        btnUpdate = getActivity().findViewById(R.id.btn_updateFace);
        btnLogOut = getActivity().findViewById(R.id.btn_logOut);


        btnUpdate.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        imgbtnChange.setOnClickListener(this);



    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), 1001);

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
            case R.id.imgbtn_change_pass:
                DialogPassFragment frm2 = new DialogPassFragment();
                FragmentTransaction ftr = getFragmentManager().beginTransaction();
                frm2.show(ftr,DialogPassFragment.TAG);

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
