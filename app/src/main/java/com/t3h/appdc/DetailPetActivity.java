package com.t3h.appdc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.t3h.appdc.adapter.CommentAdapter;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Comment;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPetActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imPet, imLove , imUser, imComment, imbtnLike;
    private EditText edComment;
    private TextView tvUserName, tvTime, tvDes, tvMore , tvID, tvComment, tvUserPost;
    private ImageButton imbtnCommnet, imbtnShare, imbtnSend;
    private RecyclerView rvComment;
    private CommentAdapter adapter;
    private ArrayList<Comment> listCommnet;
    private LinearLayout layout;
    private RelativeLayout like_ll,ll_comment, ll_share;;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pet_activity);
        initView();
        initData();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }
    private void initView() {
        imPet = findViewById(R.id.im_picture_news);
        imComment = findViewById(R.id.im_user_comment_new);
        edComment = findViewById(R.id.ed_text_comment);
        layout = findViewById(R.id.layout_comment);
        like_ll = findViewById(R.id.ll_like);
        ll_comment = findViewById(R.id.ll_comment);
        ll_share = findViewById(R.id.ll_share);

        rvComment = findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(this);
        rvComment.setAdapter(adapter);

        imUser =findViewById(R.id.im_avatar_bv);
        tvUserName =findViewById(R.id.tv_user_bv);
        tvDes =findViewById(R.id.tv_description);
        tvID =findViewById(R.id.tv_id_baiviet);
        tvUserPost =findViewById(R.id.tv_username_post);


        tvTime =findViewById(R.id.tv_date_bv);
        imLove = findViewById(R.id.imgbtn_like_news);

        imbtnLike = findViewById(R.id.imgbtn_like_news);
        imbtnCommnet = findViewById(R.id.imgbtn_comment_news);
        imbtnShare = findViewById(R.id.imgbtn_share_news);
        imbtnSend = findViewById(R.id.imbtn_send);

        imbtnLike.setOnClickListener(this);
        imbtnCommnet.setOnClickListener(this);
        imbtnShare.setOnClickListener(this);
        imbtnSend.setOnClickListener(this);
        like_ll.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_share.setOnClickListener(this);
    }
    private void initData() {
        Intent pet = getIntent();
        tvDes.setText(pet.getStringExtra(ConstPet.EXTRA_TITLE));
        tvUserName.setText(pet.getStringExtra(ConstPet.EXTRA_USERNAME));
        tvTime.setText(pet.getStringExtra(ConstPet.EXTRA_TIME));
        tvUserPost.setText(pet.getStringExtra(ConstPet.EXTRA_USERPOST));
        tvID.setText(pet.getStringExtra(ConstPet.EXTRA_ID_PET));
        if (pet.getStringExtra(ConstPet.EXTRA_LOVE).equals("1") == true) {
            imLove.setImageResource(R.drawable.heart_1);
        }else {
            imLove.setImageResource(R.drawable.heart);
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.anh);
        requestOptions.error(R.drawable.logo);

        Glide.with(imPet)
                .load(pet.getStringExtra(ConstPet.EXTRA_IMAGE_PET))
                .placeholder(R.drawable.ic_adb_black_24dp)
                .error(R.mipmap.ic_launcher)
                .into(imPet);
        Glide.with(imUser)
                .load(pet.getStringExtra(ConstPet.EXTRA_AVARTAr))
                .placeholder(R.drawable.ic_adb_black_24dp)
                .error(R.drawable.avatar_dog)
                .into(imUser);
        Glide.with(imComment)
                .load(pet.getStringExtra(ConstPet.EXTRA_AVARTAr_LOGIN))
                .placeholder(R.drawable.ic_adb_black_24dp)
                .error(R.drawable.avatar_dog)
                .into(imComment);

        String idPost = String.valueOf(pet.getStringExtra(ConstPet.EXTRA_ID));
        ApiBuilder.getInstance().getComment(idPost).enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                listCommnet = response.body();
                adapter.setData(listCommnet);
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
            }
        });
    }



    @Override
    public void onClick(View v) {
        final String id = tvID.getText().toString();
        final String id_user = tvUserPost.getText().toString();
        switch (v.getId()){
            case R.id.imbtn_send:
                final String bl = edComment.getText().toString();
                final Intent intent = getIntent();
                final String user_tac_dong = intent.getStringExtra(Const.EXTRA_USERNAME);
//                    Toast.makeText(view.getContext(), "200", Toast.LENGTH_SHORT).show();
                if (bl.isEmpty()==false && bl != " ") {

                    ApiBuilder.getInstance().addComment(user_tac_dong,bl,id+"").enqueue(new Callback<ArrayList<Comment>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                            adapter.showDialog(DetailPetActivity.this);
                            adapter.setData(response.body());
                            String mess = "đã comment bài viết của bạn!";
//                                Toast.makeText(view.getContext(), mess+" "+user_tac_dong+" "+id+" "+id_user, Toast.LENGTH_LONG).show();
                            ApiBuilder.getInstance().addNotifi(mess,user_tac_dong,id,id_user).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful()) {
//                                            Toast.makeText(context, "OK Men", Toast.LENGTH_SHORT).show();
                                        edComment.setText("");
                                    }
                                    else {
//                                            Toast.makeText(context, "Chưa thêm đâu!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(DetailPetActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
//
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                            Toast.makeText(DetailPetActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
