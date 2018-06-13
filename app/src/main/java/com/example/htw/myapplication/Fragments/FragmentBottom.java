package com.example.htw.myapplication.Fragments;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.htw.myapplication.DataStorage.ViewModelData;
import com.example.htw.myapplication.R;


public class FragmentBottom extends Fragment {

    Button btnsentDataToFragment;
    EditText editPutDataToSent;

    ViewModelData model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom,container,false);

        btnsentDataToFragment = view.findViewById(R.id.btnSendData);
        editPutDataToSent = view.findViewById(R.id.putDataToSent);

        //EVENTBUSS

/*        btnsentDataToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editPutDataToSent.getText().toString();
*//*                CustomMessageEvent event = new CustomMessageEvent(value);
                event.setCustomMessage(value);*//*
                EventBus.getDefault().post(new CustomMessageEvent(value));
            }
        });*/

        // LIVEDATA !

        model = ViewModelProviders.of(getActivity()).get(ViewModelData.class);

        btnsentDataToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getCurrentName().setValue(editPutDataToSent.getText().toString());
                Toast.makeText(getActivity(), "Supported by LiveData", Toast.LENGTH_LONG).show();
            }
        });

        return view;
        }

}
