package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Intent intent=getIntent();
        String message = intent.getStringExtra("email");
        TextInputEditText emailHR=findViewById(R.id.emailHRNameEdit);
        emailHR.setText(message);

    }

    public void btnSubmit(View view){
        String toastText = "Accessing the database";
        Toast.makeText(this,toastText,Toast.LENGTH_LONG).show();
    }

}
