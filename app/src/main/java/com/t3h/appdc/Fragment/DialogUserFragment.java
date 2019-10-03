package com.t3h.appdc.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.t3h.appdc.Const;
import com.t3h.appdc.R;

public class DialogUserFragment extends DialogFragment implements View.OnClickListener {
    public static String TAG = "FullScreenDialog";
    private Toolbar toolbar;
    private Button btnSave, btnCancle;
    private EditText edtName, edtPhone, edtEmail, edtAddress, edtBirth, edtFace;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_frm_userinfo,container,false);

        edtName = v.findViewById(R.id.edt_frm_username);
        edtEmail = v.findViewById(R.id.edt_frm_email);
        edtPhone = v.findViewById(R.id.edt_frm_phone);
        edtAddress = v.findViewById(R.id.edt_frm_address);
        edtBirth = v.findViewById(R.id.edt_frm_birth);
        edtFace = v.findViewById(R.id.edt_frm_face);

        Intent get = getActivity().getIntent();
        edtName.setText(get.getStringExtra(Const.EXTRA_USERNAME));
        edtEmail.setText(get.getStringExtra(Const.EXTRA_EMAIL));
        edtPhone.setText(get.getStringExtra(Const.EXTRA_PHONE));
        edtAddress.setText(get.getStringExtra(Const.EXTRA_ADDRESS));
        edtBirth.setText(get.getStringExtra(Const.EXTRA_BIRTH));
        edtFace.setText(get.getStringExtra(Const.EXTRA_PHONE));

        btnSave = v.findViewById(R.id.btn_frm_save);
        btnCancle = v.findViewById(R.id.btn_frm_cancel);

        btnSave.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        toolbar = v.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setTitle("Cập nhật tài khoản");

       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dismiss();
           }
       });


        return v;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_frm_save:
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String address= edtAddress.getText().toString();
                String birth = edtBirth.getText().toString();
                String face = edtFace.getText().toString();

                if (name.isEmpty() == true) {
                    edtName.setFocusable(true);
                    edtName.setError("Vui lòng nhập tên!");
                    return;
                }

                Intent up = new Intent();
                up.putExtra(Const.EXTRA_USERNAME,name);
                up.putExtra(Const.EXTRA_EMAIL,email);
                up.putExtra(Const.EXTRA_PHONE,phone);
                up.putExtra(Const.EXTRA_ADDRESS,address);
                up.putExtra(Const.EXTRA_BIRTH ,birth);
                up.putExtra(Const.EXTRA_FACE,face);

                Toast.makeText(getContext(),name,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_frm_cancel:

                break;
            default:
                break;
        }
        dismiss();

    }
}
