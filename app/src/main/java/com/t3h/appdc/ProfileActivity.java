package com.t3h.appdc;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imPet;
    private TextView tvName, tvBirth, tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        imPet = findViewById(R.id.im_cat_about);
        tvName = findViewById(R.id.tv_name_about);
        tvBirth = findViewById(R.id.tv_birth_about);
        tvDetail = findViewById(R.id.tv_detail_pet);
        tvDetail.setMovementMethod(new ScrollingMovementMethod());
        Intent detail = getIntent();

        tvName.setText(detail.getStringExtra(ConstPet.EXTRA_NAME_PET));
        tvBirth.setText(detail.getStringExtra(ConstPet.EXTRA_BIRTH_PET));
        tvDetail.setText(detail.getStringExtra(ConstPet.EXTRA_ABOUT_PET));
        Glide.with(imPet)
                .load(detail.getStringExtra(ConstPet.EXTRA_IMAGE_PET))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_adb_black_24dp)
                .into(imPet);
    }


}
