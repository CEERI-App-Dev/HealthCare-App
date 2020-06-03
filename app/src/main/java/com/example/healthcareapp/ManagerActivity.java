package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        EditText email=findViewById(R.id.emailHR1);
        Intent intent=getIntent();
        String message = intent.getStringExtra("email");
        email.setText(message, TextView.BufferType.EDITABLE);
    }
    public void btnSubmit(View view){
        String toastText = "Accessing the database";
        Toast.makeText(this,toastText,Toast.LENGTH_LONG).show();
    }

}