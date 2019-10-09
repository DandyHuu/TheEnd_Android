package com.t3h.appdc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.t3h.appdc.Fragment.NewsFragment;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Comment;
import com.t3h.appdc.model.Pets;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{
    private LayoutInflater inflater;
    private ArrayList<Pets> data;
    private RecyclerViewClickListener listener;

    public void setListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<Pets> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context){inflater = LayoutInflater.from(context);}
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_main , parent , false);
        return new NewsHolder(v);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imPet, imLove , imUser, imComment;
        private EditText edComment;
        private TextView  tvUserName, tvTime, tvDes, tvMore , tvID, tvComment;
        private ImageButton imbtnLike, imbtnCommnet, imbtnShare, imbtnSend;
        private RecyclerView rvComment;
        private CommentAdapter adapter;
        private ArrayList<Comment> listCommnet;
        private LinearLayout layout;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            imPet = itemView.findViewById(R.id.im_picture_news);
            imComment = itemView.findViewById(R.id.im_user_comment_new);
            edComment = itemView.findViewById(R.id.ed_text_comment);
            layout = itemView.findViewById(R.id.layout_comment);

            rvComment = itemView.findViewById(R.id.rv_comment);
            adapter = new CommentAdapter(itemView.getContext());
            rvComment.setAdapter(adapter);

            imUser =itemView.findViewById(R.id.im_avatar_bv);
            tvUserName =itemView.findViewById(R.id.tv_user_bv);
            tvDes =itemView.findViewById(R.id.tv_description);
            tvMore =itemView.findViewById(R.id.tv_more);
            tvID =itemView.findViewById(R.id.tv_id_post);
            tvComment =itemView.findViewById(R.id.tv_commnet_count);

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
        }
        public void bindData(Pets p){
            tvDes.setText(p.getDescription());
            tvUserName.setText(p.getFullname());
            tvTime.setText(p.getTimeup());

            if (p.isLove() == true) {
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
                    if (listCommnet.size() > 0) {
                        tvComment.setText(String.valueOf(listCommnet.size()));
                    }
                    else {
                        tvComment.setVisibility(View.GONE);
                        imbtnCommnet.setPaddingRelative(0,7,0,7);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                    tvComment.setVisibility(View.GONE);
                    imbtnCommnet.setPaddingRelative(0,7,0,7);
                }
            });
        }



        @Override
        public void onClick(View view) {
            String id = tvID.getText().toString();
            switch (view.getId()) {
                case R.id.tv_more:
                    tvDes.setMaxLines(10);
                    tvMore.setVisibility(View.GONE);
                    break;
                case R.id.imgbtn_like_news:
                    break;
                case R.id.imgbtn_comment_news:
                    if (rvComment.getVisibility() == View.GONE) {
                        rvComment.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                    }else{
                        rvComment.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }

                    break;
                case R.id.imgbtn_share_news:
                    break;
                case R.id.imbtn_send:
                    String bl = edComment.getText().toString();
                    if (bl.isEmpty() && bl != " ") {

                    }
                    break;
                default:
                    break;
            }
        }
//        public void bindData(Pets p){
//            tvName.setText(p.getName());
//            tvBirth.setText(String.valueOf(p.getBirh()));
////            String gen = "Thái Hậu";
////            if (p.isGender() == true) {
////                gen = "Hoàng Thượng";
////            }
////            tvGener.setText(gen);
//            Glide.with(imPet)
//                    .load(p.getPicture())
//                    .placeholder(R.mipmap.ic_launcher)
//                    .error(R.drawable.ic_adb_black_24dp)
//                    .into(imPet);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }
    }
//
//    public interface OnClickPet{
//         void OnClickItem(int position);
//    }




//    List<Pets> pets, petsFilter;
//    private Context context;
//    private RecyclerViewClickListener mListener;
//
//    public NewsAdapter(List<Pets> pets, Context context, RecyclerViewClickListener listener) {
//        this.pets = pets;
//        this.context = context;
//        this.mListener = listener;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_new, parent, false);
//        return new MyViewHolder(view, mListener);
//    }
//
//    @SuppressLint("CheckResult")
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, int position) {
//
//        holder.tvName.setText(pets.get(position).getName());
//        holder.tvBirth.setText(pets.get(position).getBirh());
//        String gen = "Thái Hậu";
//            if (pets.get(position).isGender() == true) {
//                gen = "Hoàng Thượng";
//            }
//            holder.tvGener.setText(gen);
//
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.skipMemoryCache(true);
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
//        requestOptions.placeholder(R.drawable.logo);
//        requestOptions.error(R.drawable.logo);
//
//        Glide.with(context)
//                .load(pets.get(position).getPicture())
//                .apply(requestOptions)
//                .into(holder.imPet);
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return pets.size();
//    }
//
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private ImageView imPet;
//        private TextView tvBirth, tvName, tvGener;
//        private Button btnDetail;
//
//        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
//            super(itemView);
//            imPet = itemView.findViewById(R.id.im_picture);
//            tvBirth = itemView.findViewById(R.id.tv_birth);
//            tvName = itemView.findViewById(R.id.tv_name);
//            tvGener = itemView.findViewById(R.id.tv_gener);
//
//            btnDetail = itemView.findViewById(R.id.btn_profile);
//            btnDetail.setOnClickListener(this);
////        }
////
//        }
//
//        @Override
//        public void onClick(View v) {
////            switch (v.getId()) {
////                case R.id.row_container:
////                    mListener.onRowClick(mRowContainer, getAdapterPosition());
////                    break;
////                case R.id.love:
////                    mListener.onLoveClick(mLove, getAdapterPosition());
////                    break;
////                default:
////                    break;
////            }
//        }
//    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }
}
