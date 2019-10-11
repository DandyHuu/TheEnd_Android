package com.t3h.appdc.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.t3h.appdc.Const;
import com.t3h.appdc.ConstPet;
import com.t3h.appdc.DetailPetActivity;
import com.t3h.appdc.Fragment.DialogPassFragment;
import com.t3h.appdc.Fragment.DialogShareFragment;
import com.t3h.appdc.Fragment.NewsFragment;
import com.t3h.appdc.Fragment.NotifiFragment;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.api.Api;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Comment;
import com.t3h.appdc.model.Notifi;
import com.t3h.appdc.model.Pets;
import com.t3h.appdc.model.User;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{
    private LayoutInflater inflater;
    private ArrayList<Pets> data;
    private RecyclerViewClickListener listener;
    private Context context;

    public void setListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<Pets> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NewsAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_main , parent , false);
        return new NewsHolder(v, listener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        holder.bindData(data.get(position));
//        if (listener != null) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onShareClick(v,position);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imPet, imLove , imUser, imComment, imbtnLike;
        private EditText edComment;
        private TextView  tvUserName, tvTime, tvDes, tvMore , tvID, tvComment, tvUserPost;
        private ImageButton  imbtnCommnet, imbtnShare, imbtnSend;
        private RecyclerView rvComment;
        private CommentAdapter adapter;
        private ArrayList<Comment> listCommnet;
        private LinearLayout layout;
        private RelativeLayout  like_ll,ll_comment, ll_share;;
        private Api api;
        private RecyclerViewClickListener listener;

        public NewsHolder(@NonNull View itemView,RecyclerViewClickListener mlistener) {
            super(itemView);
            listener = mlistener;
            imPet = itemView.findViewById(R.id.im_picture_news);
            imComment = itemView.findViewById(R.id.im_user_comment_new);
            edComment = itemView.findViewById(R.id.ed_text_comment);
            layout = itemView.findViewById(R.id.layout_comment);
            like_ll = itemView.findViewById(R.id.ll_like);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            ll_share = itemView.findViewById(R.id.ll_share);

            rvComment = itemView.findViewById(R.id.rv_comment);
            adapter = new CommentAdapter(itemView.getContext());
            rvComment.setAdapter(adapter);

            imUser =itemView.findViewById(R.id.im_avatar_bv);
            tvUserName =itemView.findViewById(R.id.tv_user_bv);
            tvDes =itemView.findViewById(R.id.tv_description);
            tvMore =itemView.findViewById(R.id.tv_more);
            tvID =itemView.findViewById(R.id.tv_id_baiviet);
            tvUserPost =itemView.findViewById(R.id.tv_username_post);


            tvTime =itemView.findViewById(R.id.tv_date_bv);
            imLove = itemView.findViewById(R.id.imgbtn_like_news);

            imbtnLike = itemView.findViewById(R.id.imgbtn_like_news);
            imbtnCommnet = itemView.findViewById(R.id.imgbtn_comment_news);
            imbtnShare = itemView.findViewById(R.id.imgbtn_share_news);
            imbtnSend = itemView.findViewById(R.id.imbtn_send);

            imbtnLike.setOnClickListener(this);
            imbtnCommnet.setOnClickListener(this);
            imbtnShare.setOnClickListener(this);
            imbtnSend.setOnClickListener(this);
            tvMore.setOnClickListener(this);
            like_ll.setOnClickListener(this);
            ll_comment.setOnClickListener(this);
            ll_share.setOnClickListener(this);
            imPet.setOnClickListener(this);
        }
        public void bindData(Pets p){
            tvDes.setText(p.getTitle());
            tvUserName.setText(p.getFullname());
            tvTime.setText(p.getTimeup());
            tvUserPost.setText(p.getUser_id()+"");
            tvID.setText(p.getId()+"");
            if (p.getLove() == true) {
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
                    .load(p.getPicture())
                    .placeholder(R.drawable.ic_adb_black_24dp)
                    .error(R.mipmap.ic_launcher)
                    .into(imPet);
            Glide.with(imUser)
                    .load(p.getAvatar())
                    .placeholder(R.drawable.ic_adb_black_24dp)
                    .error(R.drawable.avatar_dog)
                    .into(imUser);
            Glide.with(imComment)
                    .load(p.getAvatar())
                    .placeholder(R.drawable.ic_adb_black_24dp)
                    .error(R.drawable.avatar_dog)
                    .into(imComment);

            String idPost = String.valueOf(p.getId());
            ApiBuilder.getInstance().getComment(idPost).enqueue(new Callback<ArrayList<Comment>>() {
                @Override
                public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                    listCommnet = response.body();
                    adapter.setData(listCommnet);
//                    if (listCommnet.size() > 0) {
//                        tvComment.setText(String.valueOf(listCommnet.size()));
//                    }
//                    else {
//                        tvComment.setVisibility(View.GONE);
//                        imbtnCommnet.setPaddingRelative(0,7,0,7);
//                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
//                    tvComment.setVisibility(View.GONE);
////                    imbtnCommnet.setPaddingRelative(0,7,0,7);
                }
            });
        }



        @Override
        public void onClick(final View view) {
            final String id = tvID.getText().toString();
            final String id_user = tvUserPost.getText().toString();
            switch (view.getId()) {
                case R.id.tv_more:
                    tvDes.setMaxLines(10);
                    tvMore.setVisibility(View.GONE);
                    break;
                case R.id.im_picture_news:
                    listener.onRowClick(imPet,getAdapterPosition());

                    break;
                case R.id.ll_comment:
                    if (rvComment.getVisibility() == View.GONE) {
                        rvComment.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                    }else{
                        rvComment.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }

                    break;
                case R.id.ll_share:
                    listener.onShareClick(ll_share, getAdapterPosition());
//                    NewsFragment nf = new NewsFragment();
//                    nf.showDialogShare();

                    break;
                case R.id.imbtn_send:
                    final String bl = edComment.getText().toString();
                    final Intent intent = ((Activity) context).getIntent();
                    final String user_tac_dong = intent.getStringExtra(Const.EXTRA_USERNAME);
//                    Toast.makeText(view.getContext(), "200", Toast.LENGTH_SHORT).show();
                    if (bl.isEmpty()==false && bl != " ") {

                        ApiBuilder.getInstance().addComment(user_tac_dong,bl,id).enqueue(new Callback<ArrayList<Comment>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                                adapter.showDialog(view.getContext());
                                adapter.setData(response.body());
//                                Toast.makeText(view.getContext(), id_user +"-"+bl+"-"+id, Toast.LENGTH_SHORT).show();
//                                if (listCommnet.size() > 0) {
//                                    tvComment.setText(String.valueOf(listCommnet.size()));
//                                    tvComment.setVisibility(View.VISIBLE);
//                                }
//                                else {
//                                    tvComment.setVisibility(View.GONE);
//                                    imbtnCommnet.setPaddingRelative(0,7,0,7);
//                                }
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
                                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
//
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                case R.id.ll_like:
                    listener.onLoveClick(like_ll, getAdapterPosition());
                     break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
        void onShareClick(View view, int position);
    }
}
