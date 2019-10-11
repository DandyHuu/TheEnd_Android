package com.t3h.appdc.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.t3h.appdc.Const;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.api.ApiBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogUserFragment extends DialogFragment implements View.OnClickListener {
    public static String TAG = "FullScreenDialog";
    private Toolbar toolbar;
    private Button btnSave, btnCancle;
    private EditText edtName, edtPhone, edtEmail, edtAddress, edtBirth, edtFace, edFullName;
    Calendar myCalendar = Calendar.getInstance();
    private Activity activity;
    private ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_frm_userinfo,container,false);

        edtName = v.findViewById(R.id.edt_frm_username);
        edFullName = v.findViewById(R.id.edt_frm_fullname);
        edtEmail = v.findViewById(R.id.edt_frm_email);
        edtPhone = v.findViewById(R.id.edt_frm_phone);
        edtAddress = v.findViewById(R.id.edt_frm_address);
        edtBirth = v.findViewById(R.id.edt_frm_birth);
        edtFace = v.findViewById(R.id.edt_frm_face);

        Intent get = getActivity().getIntent();
        edtName.setText(get.getStringExtra(Const.EXTRA_USERNAME));
        edFullName.setText(get.getStringExtra(Const.EXTRA_FULLNAME));
        edtEmail.setText(get.getStringExtra(Const.EXTRA_EMAIL));
        edtPhone.setText(get.getStringExtra(Const.EXTRA_PHONE));
        edtAddress.setText(get.getStringExtra(Const.EXTRA_ADDRESS));
        edtBirth.setText(get.getStringExtra(Const.EXTRA_BIRTH));
        edtFace.setText(get.getStringExtra(Const.EXTRA_FACE));

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
       edtBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        return v;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }
    };

    private void setBirth(){
        String myFormat = "dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtBirth.setText(sdf.format(myCalendar.getTime()));
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
                String fullname = edFullName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String address= edtAddress.getText().toString();
                String birth = edtBirth.getText().toString();
                String face = edtFace.getText().toString();
                String avartar = "";

                if (fullname.isEmpty() == true) {
                    edFullName.setFocusable(true);
                    edFullName.setError("Vui lòng nhập tên!");
                    return;
                }
                if (email.isEmpty() == true) {
                    edtEmail.setFocusable(true);
                    edtEmail.setError("Vui lòng nhập email!");
                    return;
                }
                if (phone.isEmpty() == true) {
                    edtPhone.setFocusable(true);
                    edtPhone.setError("Vui lòng nhập số điện thoại!");
                    return;
                }
                if (address.isEmpty() == true) {
                    edtAddress.setFocusable(true);
                    edtAddress.setError("Vui lòng nhập địa chỉ!");
                    return;
                }
                if (birth.isEmpty() == true) {
                    edtBirth.setFocusable(true);
                    edtBirth.setError("Vui lòng nhập ngày sinh!");
                    return;
                }
                if (face.isEmpty() == true) {
                    edtFace.setFocusable(true);
                    edtFace.setError("Vui lòng nhập facebook!");
                    return;
                }
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Searching...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2000);

                Intent up = new Intent();
                up.putExtra(Const.EXTRA_USERNAME,name);
                up.putExtra(Const.EXTRA_FULLNAME,fullname);
                up.putExtra(Const.EXTRA_EMAIL,email);
                up.putExtra(Const.EXTRA_PHONE,phone);
                up.putExtra(Const.EXTRA_ADDRESS,address);
                up.putExtra(Const.EXTRA_BIRTH ,birth);
                up.putExtra(Const.EXTRA_FACE,face);

                ApiBuilder.getInstance().updateUser(name,fullname,email,phone,address,face,birth).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (activity != null) {
                            Toast.makeText(activity, "Thông tin của bạn đã được đổi!", Toast.LENGTH_SHORT).show();
//                            MainActivity a = (MainActivity) getActivity();
//                            a.getFrmUser().initViewInfo();
//                            a.replaceFragment(UserInfoFragment.class);
//                            a.replaceFragment();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (activity != null) {
                            Toast.makeText(activity, "Có lỗi khi lưu thông tin!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.btn_frm_cancel:

                break;
            default:
                break;
        }
        dismiss();

    }
}
