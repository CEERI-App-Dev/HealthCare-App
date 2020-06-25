package com.example.healthcareapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmployeeActivity extends Activity {
    private static final int REQUEST_IMAGE_CAPTURE =1;
    private ImageView imageView;
    private SharedPreferences userPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private String nameShared;
    private String companyShared;
    private String phoneShared;
    private boolean symptomsShared;
    private boolean absenceShared;
    private boolean overseasShared;
    private boolean contactShared;
    private boolean containmentShared;
    private EditText company;
    private EditText name ;
    private EditText email ;
    private EditText phone ;
    private EditText visit ;
    private TextInputEditText temperature ;
    private RadioGroup symptoms;
    private RadioGroup absence;
    private RadioGroup overseas;
    private RadioGroup contact;
    private RadioGroup containment;
    private RadioButton symptoms1;
    private RadioButton absence1;
    private RadioButton overseas1;
    private RadioButton contact1;
    private RadioButton containment1;

    FirebaseFirestore mFirebase;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        company = findViewById(R.id.companyName);
        name = findViewById(R.id.employeeName);
        email = findViewById(R.id.emailName);
        phone = findViewById(R.id.phoneNumber);
        visit = findViewById(R.id.visitPlace);
        temperature = findViewById(R.id.temparatureFarenheit);
        symptoms = findViewById(R.id.symptomsRadio);
        absence = findViewById(R.id.absenceRadio);
        overseas = findViewById(R.id.overseasRadio);
        contact = findViewById(R.id.contactRadio);
        containment = findViewById(R.id.containmentRadio);
        symptoms1=findViewById(R.id.yesSymptom);
        symptoms=findViewById(R.id.symptomsRadio);
        absence1=findViewById(R.id.yesAbsence);
        
        overseas1=findViewById(R.id.yesOverseas);
        contact1=findViewById(R.id.yesContact);
        containment1= findViewById(R.id.yesContainment);



        userPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        Intent intent=getIntent();
        String message = intent.getStringExtra("email");
        final EditText email = findViewById(R.id.emailName);
        email.setText(message, TextView.BufferType.EDITABLE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(EmployeeActivity.this);
        mFirebase = FirebaseFirestore.getInstance();
        if (userPreferences!=null) {
            nameShared=userPreferences.getString("name","");
            companyShared=userPreferences.getString("company","");
            phoneShared=userPreferences.getString("phone","");
            symptomsShared=userPreferences.getBoolean("symptoms",false);
            absenceShared=userPreferences.getBoolean("absence",false);
            overseasShared=userPreferences.getBoolean("overseas",false);
            contactShared=userPreferences.getBoolean("contact",false);
            containmentShared=userPreferences.getBoolean("containment",false);
            name.setText(nameShared);
            company.setText(companyShared);
            phone.setText(phoneShared);
            symptoms1.setChecked(symptomsShared);
            absence1.setChecked(absenceShared);
            overseas1.setChecked(overseasShared);
            contact1.setChecked(contactShared);
            containment1.setChecked(containmentShared);
        }


    }

    public void btnSave(View view) {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addToDatabase(View view){
        imageView=findViewById(R.id.Image);
        //used to check if a field added by user is empty
        // if (TextUtils.isEmpty(company.getText()))
        company = findViewById(R.id.companyName);
        name = findViewById(R.id.employeeName);
        email = findViewById(R.id.emailName);
        phone = findViewById(R.id.phoneNumber);
        visit = findViewById(R.id.visitPlace);
        temperature = findViewById(R.id.temparatureFarenheit);
        symptoms = findViewById(R.id.symptomsRadio);
        absence = findViewById(R.id.absenceRadio);
        overseas = findViewById(R.id.overseasRadio);
        contact = findViewById(R.id.contactRadio);
        containment = findViewById(R.id.containmentRadio);
        symptoms1=findViewById(R.id.yesSymptom);
        absence1=findViewById(R.id.yesAbsence);
        overseas1=findViewById(R.id.yesOverseas);
        contact1=findViewById(R.id.yesContact);
        containment1= findViewById(R.id.yesContainment);


        //with RadioGroup buttons we use getCheckedRadioButtonId() function
int check=1;
        if (TextUtils.isEmpty(company.getText())){
            company.setError("Company Name is required");check=0;
        }
        if (TextUtils.isEmpty(name.getText())){
            name.setError("Name is required");check=0;
        }
        if (TextUtils.isEmpty(email.getText())){
            email.setError("e-mail id is required");check=0;
        }
        if(TextUtils.isEmpty(phone.getText())){
            phone.setError("Phone Number is required");check=0;
        }
        if (TextUtils.isEmpty(temperature.getText())){
            temperature.setError("Body Temperature is required.");check=0;
        }
        if (symptoms.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();check=0;
        }
        if (absence.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();check=0;
        }
        if (overseas.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();check=0;
        }
        if (contact.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();check=0;
        }
        if (containment.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();check=0;
        }
        // variables


        if(check==1){
        double temp = Double.parseDouble(Objects.requireNonNull(temperature.getText()).toString());


        nameShared=name.getText().toString();
        companyShared=company.getText().toString();
        phoneShared=phone.getText().toString();
        symptomsShared=symptoms1.isChecked();
        absenceShared=absence1.isChecked();
        overseasShared=overseas1.isChecked();
        contactShared=contact1.isChecked();
        containmentShared=containment1.isChecked();

        // SQLite database addition code.
        CustomerModel customerModel = new CustomerModel(1,company.getText().toString(),name.getText().toString(),
                email.getText().toString(),phone.getText().toString(),symptoms1.isChecked(),absence1.isChecked(),
                overseas1.isChecked(),contact1.isChecked(),temp,containment1.isChecked());
        if (!(symptoms1.isChecked() || overseas1.isChecked() || contact1.isChecked() || containment1.isChecked() || temp >100))
            Toast.makeText(EmployeeActivity.this,"You can join office",Toast.LENGTH_SHORT).show();

        // 100.5 is considered as high temperature
        if(temp>100.5)
            Toast.makeText(EmployeeActivity.this,"You should work from home",Toast.LENGTH_SHORT).show();

            if (symptoms1.isChecked() && contact1.isChecked() && containment1.isChecked())
                Toast.makeText(EmployeeActivity.this,"You should work from home",Toast.LENGTH_SHORT).show();

//        Toast.makeText(EmployeeActivity.this,customerModel.toString(),Toast.LENGTH_LONG).show();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(EmployeeActivity.this);
        boolean success = dataBaseHelper.addOne(customerModel);
//        Toast.makeText(EmployeeActivity.this,"Success="+success,Toast.LENGTH_SHORT).show();

        // Firebase data addition code.
        Map<String, Object> users = new HashMap<>();
        users.put("company",company.getText().toString());
        users.put("name",name.getText().toString());
        users.put("email",email.getText().toString());
        users.put("phone",phone.getText().toString());
        users.put("symptoms",symptoms1.isChecked());
        users.put("absence",absence1.isChecked());
        users.put("overseas",overseas1.isChecked());
        users.put("contact",contact1.isChecked());
        users.put("temperature",temp);
        users.put("visit",containment1.isChecked());

        mFirebase.collection("employees")
                .add(users)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("success","DocumentSnapshot added with ID: "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("failure","Error adding document",e);
                    }
                });

        //.............................
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);

    }}
    private void selectImage() {
        Intent takeImageIntent = ImagePicker.getPickImageIntent(this);
        if (takeImageIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takeImageIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = ImagePicker.getBitmapFromResult(this, resultCode, data);
        if (null != bitmap && resultCode == RESULT_OK) {
            imageView=findViewById(R.id.Image);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 480, false));
        }
    }


    public void btnAddImage(View view) {
            selectImage();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = userPreferences.edit();
        preferencesEditor.putString("name",nameShared);
        preferencesEditor.putString("company",companyShared);
        preferencesEditor.putString("phone",phoneShared);
        preferencesEditor.putBoolean("symptoms",symptomsShared);
        preferencesEditor.putBoolean("absence",absenceShared);
        preferencesEditor.putBoolean("overseas",overseasShared);
        preferencesEditor.putBoolean("contact",contactShared);
        preferencesEditor.putBoolean("containment",containmentShared);
        preferencesEditor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor preferencesEditor = userPreferences.edit();
        preferencesEditor.putString("name",nameShared);
        preferencesEditor.putString("company",companyShared);
        preferencesEditor.putString("phone",phoneShared);
        preferencesEditor.putBoolean("symptoms",symptomsShared);
        preferencesEditor.putBoolean("absence",absenceShared);
        preferencesEditor.putBoolean("overseas",overseasShared);
        preferencesEditor.putBoolean("contact",contactShared);
        preferencesEditor.putBoolean("containment",containmentShared);
        preferencesEditor.apply();

    }
}

