package com.t3h.appdc.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.t3h.appdc.Const;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogPassFragment extends DialogFragment implements View.OnClickListener {
    public static String TAG= "Dialog Change Password";
    private Toolbar toolbar;
    private Button btnSave, btnCancel;
    private EditText edPassOld, edPass, edComfirm;
    private String truePass;
    private String userid;
    private ProgressDialog progressDialog;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_changepass,container,false);
        edPassOld = v.findViewById(R.id.edt_frm_passold);
        edPass = v.findViewById(R.id.edt_frm_pass);
        edComfirm = v.findViewById(R.id.edt_frm_comfirm);
        Intent get = getActivity().getIntent();
        truePass = get.getStringExtra(Const.EXTRA_PASS);
        userid = get.getStringExtra(Const.EXTRA_USERNAME);

        btnSave = v.findViewById(R.id.btn_frm_savepass);
        btnCancel = v.findViewById(R.id.btn_frm_cancelpass);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        toolbar = v.findViewById(R.id.toolbar_pass);
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setTitle("Cập nhật mật khẩu");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return v;

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_frm_savepass:
                String passold = edPassOld.getText().toString();
                String pass = edPass.getText().toString();
                String comfirm = edComfirm.getText().toString();
                if (passold.isEmpty()) {
                    edPassOld.setFocusable(true);
                    edPassOld.setError("Vui lòng nhập mật khẩu!");
                    return;
                }else{
                    if (passold.equals(truePass) == false) {
                        edPassOld.setFocusable(true);
                        edPassOld.setError("Mật khẩu của bạn không đúng!");
                        return;
                    }
                }

                if (pass.isEmpty()) {
                    edPass.setFocusable(true);
                    edPass.setError("Vui lòng nhập mật khẩu mới!");
                    return;
                }

                if (comfirm.isEmpty()) {
                    edComfirm.setFocusable(true);
                    edComfirm.setError("Vui lòng nhập mật khẩu mới!");
                    return;
                }else{
                    if (pass.equals(comfirm) == false) {
                        edComfirm.setFocusable(true);
                        edComfirm.setError("Mật khẩu mới của bạn không đúng!");
                        return;
                    }
                }
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Đăng tiến hành...");
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
                ApiBuilder.getInstance().changePass(pass,userid).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (activity != null) {
                            Toast.makeText(activity, "Mật khẩu của bạn đã được đổi!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (activity != null) {
                            Toast.makeText(activity,"Lỗi khi đổi mật khẩu!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                break;
            case R.id.btn_frm_cancelpass:

                break;
            default:
                break;
        }
        dismiss();
    }
}
