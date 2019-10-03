package com.t3h.appdc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    private Button view;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.items_new);
//        view = findViewById(R.id.btn_profile);
//        view.setOnClickListener(this);
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.anh);
//        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
//
//        ImageView circularImageView = (ImageView) findViewById(R.id.im_picture);
//        circularImageView.setImageBitmap(circularBitmap);
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_new, menu);
//
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_profile:
//                Intent intent = new Intent(this, ProfileActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }
}
