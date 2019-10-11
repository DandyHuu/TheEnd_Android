package com.t3h.appdc.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.appdc.R;
import com.t3h.appdc.model.Notifi;

import java.util.ArrayList;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiHolder> {
    private LayoutInflater inflater;
    private ArrayList<Notifi> data;
    private OnClickComment listenner;

    public NotiAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Notifi> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListenner(OnClickComment listenner) {
        this.listenner = listenner;
    }

    @NonNull
    @Override
    public NotiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_notifi,parent,false);
        return new NotiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiHolder holder, final int position) {
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

    class NotiHolder extends RecyclerView.ViewHolder {
        private TextView tvDate, tvNotifi, tvName;

        public NotiHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date_notification);
            tvNotifi = itemView.findViewById(R.id.tv_notification);
//            tvName = itemView.findViewById(R.id.tv_fullname_notification);


        }

        public void bindData(Notifi c){
            if (tvDate != null) {
                tvDate.setText(c.getDate().toString());
            }
//            tvName.setText(c.getFullname()+" ");
            String name = "<strong>"+c.getFullname()+"</strong> "+c.getMessage();
            tvNotifi.setText(Html.fromHtml(name));
        }
    }

    public interface OnClickComment{
        void OnClickItem(int position);
    }
}