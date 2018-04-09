package com.example.htw.myapplication;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkDataConnection extends AsyncTask <Void, Void,Void>{

    String savedDataFromJson = "";
    String parsedDataFromJson = "";
    String parsedSingleDataFromJson = "";
    //
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://api.myjson.com/bins/1h9imj");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream =httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String tempLineForJson = "";
            while (tempLineForJson != null)
            {
                tempLineForJson = bufferedReader.readLine();
                savedDataFromJson = savedDataFromJson + tempLineForJson;
            }

            JSONArray jsonArray = new JSONArray(savedDataFromJson);
            for (int i=0; i<jsonArray.length();i++ ){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                parsedSingleDataFromJson =  "Name: " + jsonObject.get("name") + "\n" +
                                            "Surname: " + jsonObject.get("surname") + "\n"+
                                            "Punkty: " + jsonObject.get("points") + "\n";

                parsedDataFromJson = parsedDataFromJson + parsedSingleDataFromJson;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    // WĄTEK UI

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        JsonActivity.TextFromJson.setText(parsedDataFromJson);



    }
}
