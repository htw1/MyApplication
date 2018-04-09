package com.example.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by htw on 21.12.2017.
 */

public class FragmentA extends Fragment implements View.OnClickListener {

    Communicator communicator;
    int counter=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_a,container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        communicator= (Communicator) getActivity();
        Button button =  (Button) getActivity().findViewById(R.id.buttonB);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        counter++;
        communicator.respond("guzik naciśnięty" + counter+"razy");
    }
}
