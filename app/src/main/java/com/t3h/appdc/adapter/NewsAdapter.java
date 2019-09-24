package com.t3h.appdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.appdc.R;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{
    private LayoutInflater inflater;
    private List<Pets> data;
    private OnClickPet listener;

    public void setListener(OnClickPet listener) {
        this.listener = listener;
    }

    public void setData(List<Pets> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context){inflater = LayoutInflater.from(context);}
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.items_new , parent , false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imPet;
        private TextView tvBirth, tvName, tvGener;
        private Button btnDetail;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            imPet = itemView.findViewById(R.id.im_picture_news);
            tvBirth = itemView.findViewById(R.id.tv_age_news);
            tvName = itemView.findViewById(R.id.tv_namePets_news);

//            btnDetail = itemView.findViewById(R.id.);
//            btnDetail.setOnClickListener(this);
        }
        public void bindData(Pets p){
            tvName.setText(p.getName());
            tvBirth.setText(String.valueOf(p.getBirh()));
//            String gen = "Thái Hậu";
//            if (p.isGender() == true) {
//                gen = "Hoàng Thượng";
//            }
//            tvGener.setText(gen);
            Glide.with(imPet)
                    .load(p.getPicture())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imPet);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface OnClickPet{
         void OnClickItem(int position);
    }
}
