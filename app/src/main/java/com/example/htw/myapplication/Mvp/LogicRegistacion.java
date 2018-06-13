package com.example.htw.myapplication.Mvp;

import android.text.TextUtils;

public class LogicRegistacion  implements RegisterView {



    RegMethodView regMethodView;

    public LogicRegistacion(RegMethodView registerView) {
        this.regMethodView = registerView;
    }

    @Override
    public void registerIn(String name, String surname) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)){
            regMethodView.showError();
        }else

            if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(surname))  {
            regMethodView.regSuccess();
        }



    }
}
