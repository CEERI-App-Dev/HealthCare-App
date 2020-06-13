package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class DisplayDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        ListView list = (ListView) findViewById(R.id.listView1);

        Intent intent=getIntent();
        String companyName=intent.getStringExtra("companyName");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(DisplayDataActivity.this);
        List<CustomerModel> everyone = dataBaseHelper.getEveryone(companyName);

        ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(DisplayDataActivity.this, android.R.layout.simple_list_item_1, everyone);
        list.setAdapter(customerArrayAdapter);

    }
}