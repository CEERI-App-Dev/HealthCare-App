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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class ManagerActivity extends Activity {
    private FirebaseFirestore mFirebase;
    private RecyclerView mFirestoreList;
   // private FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(ManagerActivity.this);
    ArrayAdapter arrayAdapter ;
    ListView listView ;

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
        String companyName = company.getText().toString();
        Intent intent = new Intent(this, DisplayDataActivity.class);
        intent.putExtra("companyName", companyName);
        startActivity(intent);
        //Toast.makeText(ManagerActivity.this,everyone.toString(),Toast.LENGTH_LONG).show();
    }

    public void btnFirebase(View view) {
       /* //Query
        Query query = mFirebase.collection("employees").orderBy("company", Query.Direction.ASCENDING);

        //Recycler Options
        FirestoreRecyclerOptions<EmployeeModel> options = new FirestoreRecyclerOptions.Builder<EmployeeModel>()
                .setQuery(query, EmployeeModel.class)
                .build();

        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<EmployeeModel, EmployeeViewHolder>(options) {


            @NonNull
            @Override
            public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_firestore, parent, false);
                return new EmployeeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position, @NonNull EmployeeModel model) {
                holder.list_company.setText(model.getCompany());
                holder.list_name.setText(model.getName());
                holder.list_email.setText(model.getEmail());
                holder.list_phone.setText(model.getPhone());
                holder.list_symptoms.setText(model.isSymptoms() + "");
                holder.list_absence.setText(model.isAbsence() + "");
                holder.list_overseas.setText(model.isOverseas() + "");
                holder.list_contact.setText(model.isContact() + "");
                holder.list_temp.setText(model.getTemperature() + "");
                holder.list_visit.setText(model.isVisit() + "");
            }
        };
        //View Holder Class
        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(firestoreRecyclerAdapter);
    }

    private class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView list_company;
        private TextView list_name;
        private TextView list_email;
        private TextView list_phone;
        private TextView list_symptoms;
        private TextView list_absence;
        private TextView list_overseas;
        private TextView list_contact;
        private TextView list_temp;
        private TextView list_visit;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            list_company = itemView.findViewById(R.id.list_company);
            list_name = itemView.findViewById(R.id.list_name);
            list_email = itemView.findViewById(R.id.list_email);
            list_phone = itemView.findViewById(R.id.list_phone);
            list_symptoms = itemView.findViewById(R.id.list_symptoms);
            list_absence = itemView.findViewById(R.id.list_absence);
            list_overseas = itemView.findViewById(R.id.list_overseas);
            list_contact = itemView.findViewById(R.id.list_contact);
            list_temp = itemView.findViewById(R.id.list_temp);
            list_visit = itemView.findViewById(R.id.list_visit);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firestoreRecyclerAdapter != null) {
            firestoreRecyclerAdapter.stopListening();
        }*/

       //use a recycler view
        listView = findViewById(R.id.listViewFirestore);
        List<CustomerModel> data = dataBaseHelper.getFirestore();
        arrayAdapter = new ArrayAdapter(ManagerActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
    }
}




