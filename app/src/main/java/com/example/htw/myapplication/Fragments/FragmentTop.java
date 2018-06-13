package com.example.htw.myapplication.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.htw.myapplication.DataStorage.CustomMessageEvent;
import com.example.htw.myapplication.DataStorage.ViewModelData;
import com.example.htw.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentTop extends Fragment {

     TextView textView;
     ViewModelData model;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container,false );

        textView = view.findViewById(R.id.showDataFromBottom);

        model = ViewModelProviders.of(getActivity()).get(ViewModelData.class);
        EventBus.getDefault().register(this);

          //  Nasłuchuje na zmiany
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {

                textView.setText(newName);
            }
        };

        model.getCurrentName().observe(this,nameObserver );

        return view;
    };

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEvent( CustomMessageEvent event  ){
        textView.setText(event.getCustomMessage());
    }

}
