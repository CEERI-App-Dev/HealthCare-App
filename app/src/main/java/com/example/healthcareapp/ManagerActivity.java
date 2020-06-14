package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Intent intent = getIntent();
        String message = intent.getStringExtra("email");
        TextInputEditText emailHR = findViewById(R.id.emailHRNameEdit);
        emailHR.setText(message);

    }

    public void btnSubmit(View view) {
        String toastText = "Accessing the database";
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
        // will later change it to Recycler View. Recycler View is more efficient and advanced.
        ListView list = (ListView) findViewById(R.id.listView);
        EditText company = findViewById(R.id.companyNameEdit);
        String companyName = company.getText().toString();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(ManagerActivity.this);
        List<CustomerModel> everyone = dataBaseHelper.getEveryone(companyName);

        ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(ManagerActivity.this, android.R.layout.simple_list_item_1, everyone);
        list.setAdapter(customerArrayAdapter);

        //Toast.makeText(ManagerActivity.this,everyone.toString(),Toast.LENGTH_LONG).show();
    }

}
