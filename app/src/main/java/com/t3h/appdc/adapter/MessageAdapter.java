package com.t3h.appdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.appdc.R;
import com.t3h.appdc.model.Notifi;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private LayoutInflater inflater;
    private ArrayList<Notifi> data;

    public MessageAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Notifi> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.message_item,parent,false);
        return new MessageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvMess, tvTime;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMess = itemView.findViewById(R.id.tv_mess);
            tvTime = itemView.findViewById(R.id.tv_time);
        }

        public void bindData(Notifi c){
            tvName.setText(c.getName());
            tvMess.setText(c.getMessage());
            tvTime.setText(c.getDate());
        }
    }
}
