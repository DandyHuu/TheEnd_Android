package com.t3h.appdc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;

public class EditorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_editor,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

//    @Override
//    public void onResume() {
//        MainActivity m = (MainActivity) getActivity();
//        m.setNoActibve();
//        m.getImgbtnNew().setImageResource(R.drawable.plusactive);
//        super.onResume();
//    }
}
