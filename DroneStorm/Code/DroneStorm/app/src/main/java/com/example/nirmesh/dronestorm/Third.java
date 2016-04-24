package com.example.nirmesh.dronestorm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Third extends AppCompatActivity {


    ListView lv;
    DroneDataAdapter adapter;
    ArrayList<DroneData> droneDatasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        lv = (ListView)findViewById(R.id.lv);
        droneDatasList = new ArrayList<DroneData>();
    }
    public class DroneAsyncTask extends AsyncTask<String, Void, Boolean>{


        @Override
        protected Boolean doInBackground(String... params) {
           String response = getDataFromUrl("http://54.201.123.98:3000/getData");


            try {
                JSONObject jObj = new JSONObject(response);
                JSONArray jArray = jObj.getJSONArray("DroneData");
                for(int i=0; i<jArray.length(); i++)
                {
                    DroneData droneData = new DroneData();
                    JSONObject jRealObject = jArray.getJSONObject(i);
                    droneData.setIcon(jRealObject.getString("icon"));
                    droneData.setClearDay(jRealObject.getString("clearDay"));
                    droneData.setProbability(jRealObject.getString("probability"));
                    droneData.setSummary(jRealObject.getString("summary"));
                    droneData.setTemperature(jRealObject.getString("temperature"));
                    droneData.setVisibility(jRealObject.getString("visibility"));
                    droneData.setWind(jRealObject.getString("wind"));
                    droneData.setBearing(jRealObject.getString("bearing"));
                    droneData.setIntensity(jRealObject.getString("intensity"));

                    droneDatasList.add(droneData);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            DroneDataAdapter adapter = new DroneDataAdapter(getApplicationContext(),R.layout.row,droneDatasList);

            lv.setAdapter(adapter);
        }
    }
    public static String getDataFromUrl(String url) {
        String result = null;
//        System.out.println("URL comes in jsonparser class is:  " + url);
        try {
            URL myurl=new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) myurl
                    .openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream is=urlConnection.getInputStream();
            if (is != null) {
                StringBuilder sb = new StringBuilder();
                String line;
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(is));
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                } finally {
                    is.close();
                }
                result = sb.toString();
            }
        }catch (Exception e){
            result=null;
        }
        return result;
    }

    public void kill(View v) {
        finish();
        System.exit(0);
    }

    public void goBack(View v) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

}

