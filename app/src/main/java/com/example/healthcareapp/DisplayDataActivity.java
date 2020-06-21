package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.List;

public class DisplayDataActivity extends Activity  {
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
        registerForContextMenu(list);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listView1) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.display, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.workFromHome:
                Toast.makeText(DisplayDataActivity.this, "Work from Home Approved", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.office:
                Toast.makeText(DisplayDataActivity.this, "Work in office Approved", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


        public void btnShow (View view){
            final ListView list = (ListView) findViewById(R.id.listView1);
            List<CustomerModel> everyone = dataBaseHelper.getFirestore(companyName);
            ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(DisplayDataActivity.this, android.R.layout.simple_list_item_1, everyone);
            list.setAdapter(customerArrayAdapter);
            registerForContextMenu(list);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    registerForContextMenu(list);
                }
            });

        }
    }