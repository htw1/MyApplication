package com.example.htw.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htw.myapplication.DataStorage.ApiInterfaceHero;
import com.example.htw.myapplication.DataStorage.NetworkDataConnection;
import com.example.htw.myapplication.R;
import com.example.htw.myapplication.adapter.AdapterListViewForHero;
import com.example.htw.myapplication.Model.ModelHero;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonActivity extends AppCompatActivity {

    public static TextView TextFromJson;
    AdapterListViewForHero adapterListViewForHero;
    ListView listView;
    List<ModelHero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        TextFromJson = (TextView) findViewById(R.id.textFromJson);
        listView = (ListView) findViewById(R.id.listViewHero);
        findViewById(R.id.button_json).setOnClickListener(this::jsonButton);


        // RETROFIT

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterfaceHero.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterfaceHero apiInterfaceHero = retrofit.create(ApiInterfaceHero.class);

        Call <List<ModelHero>> call = apiInterfaceHero.getHeroes();

        call.enqueue(new Callback<List<ModelHero>>() {

            @Override
            public void onResponse(Call<List<ModelHero>>  call, Response<List<ModelHero>> response) {

                List<ModelHero> heroList = response.body();

                adapterListViewForHero = new AdapterListViewForHero(JsonActivity.this,R.layout.listview_hero_row_element, heroList  );
                listView.setAdapter(adapterListViewForHero);
            }

            @Override
            public void onFailure(Call<List<ModelHero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        });

    }

    private void deteleHero(View view) {


    }

    private void jsonButton(View view) {

        NetworkDataConnection jsonDataConnection = new NetworkDataConnection();
        jsonDataConnection.execute();
    }
}
