package com.example.htw.myapplication.DataStorage;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Date;

public class ViewModelData extends ViewModel {

    private MutableLiveData<String> currentValue ;
    private static MutableLiveData<Long> data = new MutableLiveData<Long>();

    public MutableLiveData<String> getCurrentName() {

        if (currentValue == null) {
            currentValue = new MutableLiveData<String>();
        }
        return currentValue;
    }
    public static LiveData<Long> getData()
    {
        data.setValue(new Date().getTime());
        return data;
    }


}


