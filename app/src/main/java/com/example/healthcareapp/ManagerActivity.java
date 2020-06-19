package com.example.healthcareapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class ManagerActivity extends Activity {
    private FirebaseFirestore mFirebase;
    private RecyclerView mFirestoreList;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(ManagerActivity.this);
    ArrayAdapter arrayAdapter ;
    ListView listView ;
    String companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Intent intent = getIntent();
        String message = intent.getStringExtra("email");
        TextInputEditText emailHR = findViewById(R.id.emailHRNameEdit);
        emailHR.setText(message);

        mFirebase = FirebaseFirestore.getInstance();

    }

    public void btnSubmit(View view) {
        String toastText = "Accessing the database";
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
        EditText company = findViewById(R.id.companyNameEdit);
        companyName = company.getText().toString();
        Intent intent = new Intent(this, DisplayDataActivity.class);
        intent.putExtra("companyName", companyName);
        startActivity(intent);
        //Toast.makeText(ManagerActivity.this,everyone.toString(),Toast.LENGTH_LONG).show();
    }


}




