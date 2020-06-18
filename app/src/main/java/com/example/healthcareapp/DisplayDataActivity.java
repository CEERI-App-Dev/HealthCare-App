package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DisplayDataActivity extends Activity {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(DisplayDataActivity.this);
    String companyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        ListView list = (ListView) findViewById(R.id.listView1);

        Intent intent=getIntent();

        companyName=intent.getStringExtra("companyName");
        List<CustomerModel> everyone = dataBaseHelper.getFirestore(companyName);
        everyone=dataBaseHelper.getFirestoreFinal();

        ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(DisplayDataActivity.this, android.R.layout.simple_list_item_1, everyone);
        list.setAdapter(customerArrayAdapter);


    }

    public void btnShow(View view) {
        ListView list = (ListView) findViewById(R.id.listView1);
        List<CustomerModel> everyone = dataBaseHelper.getFirestore(companyName);
        ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(DisplayDataActivity.this, android.R.layout.simple_list_item_1, everyone);
        list.setAdapter(customerArrayAdapter);
    }
}