package com.t3h.appdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.appdc.R;
import com.t3h.appdc.model.Pets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class SharePetAdapter extends RecyclerView.Adapter<SharePetAdapter.SharePetHolder> {
    private LayoutInflater inflater;
    private ArrayList<Pets> data;
    private OnClickPet listenner;

    public SharePetAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Pets> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListenner(OnClickPet listenner) {
        this.listenner = listenner;
    }

    @NonNull
    @Override
    public SharePetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_petshare,parent,false);
        return new SharePetHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SharePetHolder holder, final int position) {
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

    class SharePetHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgPet;
        private TextView tvNamePet, tvAge, tvCate, tvGender;

        public SharePetHolder(@NonNull View itemView) {
            super(itemView);
            imgPet = itemView.findViewById(R.id.im_file);
            tvNamePet = itemView.findViewById(R.id.tv_name_pet);
            tvAge = itemView.findViewById(R.id.tv_age_pet);
            tvGender = itemView.findViewById(R.id.tv_gender);
            tvCate = itemView.findViewById(R.id.tv_cate_pet);
        }

        public void bindData(Pets p){
            tvNamePet.setText(p.getName());
            tvAge.setText(p.getBirth());
            String gender = "Cái";
            if (p.getGender() == 1) {
                gender = "Đực";

            }
            tvGender.setText(gender);
            tvCate.setText(p.getSpecies());
            Glide.with(imgPet)
                    .load(p.getPicture())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.ic_adb_black_24dp)
                    .into(imgPet);
        }
        public int getAge(String dateOfBirth) {

            Calendar today = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();

            int age = 0;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date convertedDate = new Date();
            try {
                convertedDate = dateFormat.parse(dateOfBirth);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            birthDate.setTime(convertedDate);
            if (birthDate.after(today)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }

            age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

            // If birth date is greater than todays date (after 2 days adjustment of
            // leap year) then decrement age one year
            if ((birthDate.get(Calendar.DAY_OF_YEAR)
                    - today.get(Calendar.DAY_OF_YEAR) > 3)
                    || (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
                age--;

                // If birth date and todays date are of same month and birth day of
                // month is greater than todays day of month then decrement age
            } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
                    && (birthDate.get(Calendar.DAY_OF_MONTH) > today
                    .get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            return age;
        }
    }

    public interface OnClickPet{
        void OnClickItem(int position);
    }
}
