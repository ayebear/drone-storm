package com.example.nirmesh.dronestorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Dummy extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayAdapter<String> adapter;
    String[ ] countries = {"summary",  "Mostly Cloudy","icon", "partly-cloudy-night","temperature","precipitation","intensity","probability","wind","speed","bearing","noFlyZone","safety", "messages" ,"This location intersects a no-fly zone.","level"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);
        // employee = new Employee(Company,"MSG", Person);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, countries[position], Toast.LENGTH_LONG).show();
    }
}