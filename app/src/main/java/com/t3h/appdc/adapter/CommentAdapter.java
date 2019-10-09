package com.t3h.appdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.appdc.R;
import com.t3h.appdc.model.Comment;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private LayoutInflater inflater;
    private ArrayList<Comment> data;
    private OnClickComment listenner;

    public CommentAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Comment> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListenner(OnClickComment listenner) {
        this.listenner = listenner;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_comment,parent,false);
        return new CommentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, final int position) {
        holder.bindData(data.get(position));

        if (listenner != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenner.OnClickItem(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        private CircleImageView imUser;
        private TextView tvName, tvComment, tvCate;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            imUser = itemView.findViewById(R.id.im_user_comment);
            tvName = itemView.findViewById(R.id.tv_name_comment);
            tvComment = itemView.findViewById(R.id.tv_comment);

        }

        public void bindData(Comment c){
            tvName.setText(c.getName());
            tvComment.setText(c.getComment());
            Glide.with(imUser)
                    .load(c.getAvatar())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.avatar_dog)
                    .into(imUser);
        }
    }

    public interface OnClickComment{
        void OnClickItem(int position);
    }
}