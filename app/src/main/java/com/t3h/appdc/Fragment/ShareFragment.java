package com.t3h.appdc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.appdc.ConstPet;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.ProfileActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.adapter.SharePetAdapter;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareFragment extends Fragment implements SharePetAdapter.OnClickPet {
    private RecyclerView rvShare;
    private ArrayList<Pets> dataShare;
    private SharePetAdapter adapterS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_share, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }
    private void initView() {
        adapterS = new SharePetAdapter(getContext());
        rvShare = getActivity().findViewById(R.id.rv_share);
        rvShare.setAdapter(adapterS);
        adapterS.setListenner(this);

    }

//    @Override
//    public void onResume() {
//        MainActivity m = (MainActivity) getActivity();
//        m.setNoActibve();
//        m.getImgbtnPet().setImageResource(R.drawable.pawprintactive);
//        super.onResume();
//    }

    private void initData() {
        ApiBuilder.getInstance().getPets().enqueue(new Callback<ArrayList<Pets>>() {
            @Override
            public void onResponse(Call<ArrayList<Pets>> call, Response<ArrayList<Pets>> response) {
                dataShare = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Pets>> call, Throwable t) {

            }
        });
        adapterS.setData(dataShare);
    }

    public void setDataShare(ArrayList<Pets> data) {
        this.adapterS.setData(data);
        this.dataShare = data;

    }

    @Override
    public void OnClickItem(int position) {
        Intent detail = new Intent(getContext(), ProfileActivity.class);
        detail.putExtra(ConstPet.EXTRA_NAME_PET, dataShare.get(position).getName());
        detail.putExtra(ConstPet.EXTRA_ABOUT_PET, dataShare.get(position).getMessage());
        detail.putExtra(ConstPet.EXTRA_BIRTH_PET, dataShare.get(position).getBirth());
        detail.putExtra(ConstPet.EXTRA_GENDER_PET, dataShare.get(position).getGender());
        detail.putExtra(ConstPet.EXTRA_IMAGE_PET, dataShare.get(position).getPicture());
        detail.putExtra(ConstPet.EXTRA_SPECIES_PET, dataShare.get(position).getSpecies());
        detail.putExtra(ConstPet.EXTRA_ID_PET, String.valueOf(dataShare.get(position).getId_pet()));
        detail.putExtra(ConstPet.EXTRA_USERNAME, dataShare.get(position).getFullname());

        startActivity(detail);
    }
}
