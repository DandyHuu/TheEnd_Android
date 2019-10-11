package com.t3h.appdc.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.t3h.appdc.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DialogShareFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG ="Dialog Share" ;
    private CircleImageView imFace, imGG, imTwitter, imReddit, imBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialogshare,container,false);
        imFace = v.findViewById(R.id.imbtn_face);
        imGG = v.findViewById(R.id.imbtn_google);
        imTwitter = v.findViewById(R.id.imbtn_twtter);
        imReddit = v.findViewById(R.id.imbtn_reddit);

        imFace.setOnClickListener(this);
        imGG.setOnClickListener(this);
        imTwitter.setOnClickListener(this);
        imReddit.setOnClickListener(this);
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
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;

            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imbtn_face:
                break;
            case R.id.imbtn_google:
                break;
            case R.id.imbtn_twtter:
                break;
            case R.id.imbtn_reddit:
                break;

            default:
                break;

        }
        dismiss();
    }
}
