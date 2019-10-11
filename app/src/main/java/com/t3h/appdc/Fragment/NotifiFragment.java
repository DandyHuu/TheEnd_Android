package com.t3h.appdc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.appdc.Const;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;
import com.t3h.appdc.adapter.NotiAdapter;
import com.t3h.appdc.api.ApiBuilder;
import com.t3h.appdc.model.Notifi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifiFragment extends Fragment {
    private RecyclerView rvNotifi;
    private NotiAdapter adapter;
    private ArrayList<Notifi> dataNotifi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifi,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        rvNotifi = getActivity().findViewById(R.id.rv_notifi);
        adapter = new NotiAdapter(getContext());
        rvNotifi.setAdapter(adapter);
        Intent user = getActivity().getIntent();
        String user_id = user.getStringExtra(Const.EXTRA_USERNAME);
        ApiBuilder.getInstance().getNotifi(user_id).enqueue(new Callback<ArrayList<Notifi>>() {
            @Override
            public void onResponse(Call<ArrayList<Notifi>> call, Response<ArrayList<Notifi>> response) {
                dataNotifi = response.body();
                setNotifiData(dataNotifi);
            }

            @Override
            public void onFailure(Call<ArrayList<Notifi>> call, Throwable t) {

            }
        });
    }
    public void setNotifiData(ArrayList<Notifi> data){
        adapter.setData(data);
    }

//    @Override
//    public void onResume() {
//        MainActivity m = (MainActivity) getActivity();
//        m.setNoActibve();
//        m.getImgbtnNoti().setImageResource(R.drawable.notificationactive);
//        super.onResume();
//    }
}
