package com.t3h.appdc.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.appdc.R;
import com.t3h.appdc.model.Pets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShareAdapter {

    class ShareHolder extends RecyclerView.ViewHolder {
        private ImageView imPetShare, imGender;
        private TextView tvNamePet, tvIDPet, tvMaster, tvAgePet;

        public ShareHolder(@NonNull View itemView) {
            super(itemView);
            imPetShare = itemView.findViewById(R.id.im_share);
            imGender = itemView.findViewById(R.id.im_gender_share);
            tvNamePet = itemView.findViewById(R.id.tv_namePets);
            tvIDPet = itemView.findViewById(R.id.tv_id_share);
            tvMaster = itemView.findViewById(R.id.tv_user_share);
            tvAgePet = itemView.findViewById(R.id.tv_age_share);
        }
        public void bindData(Pets pet){
            tvNamePet.setText(pet.getName());
            tvAgePet.setText(getAge(pet.getBirh()) +" tuổi");
            tvIDPet.setText(pet.getId()+"");
            tvMaster.setText(pet.getMessage());
            if (pet.isGender() == true) {
                Glide.with(imGender)
                        .load(R.drawable.man)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(android.R.drawable.ic_delete)
                        .into(imGender);
            }
            else{
                Glide.with(imGender)
                        .load(R.drawable.female)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(android.R.drawable.ic_delete)
                        .into(imGender);
            }
            Glide.with(imPetShare)
                    .load(pet.getPicture())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imPetShare);
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
}
