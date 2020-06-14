package com.example.healthcareapp;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EmployeeActivity extends Activity {
    private static final int REQUEST_IMAGE_CAPTURE =1;
    ImageView imageView;

    FirebaseFirestore mFirebase = FirebaseFirestore.getInstance();
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Intent intent=getIntent();
        String message = intent.getStringExtra("email");
        final EditText email = findViewById(R.id.emailName);
        email.setText(message, TextView.BufferType.EDITABLE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(EmployeeActivity.this);

    }

    public void btnSave(View view) {
        imageView=findViewById(R.id.Image);
        EditText company = findViewById(R.id.companyName);
        EditText name = findViewById(R.id.employeeName);
        EditText email = findViewById(R.id.emailName);
        EditText phone = findViewById(R.id.phoneNumber);
        EditText visit = findViewById(R.id.visitPlace);
        EditText temperature = findViewById(R.id.temparatureFarenheit);
        RadioGroup symptoms = findViewById(R.id.symptomsRadio);
        RadioGroup absence = findViewById(R.id.absenceRadio);
        RadioGroup overseas = findViewById(R.id.overseasRadio);
        RadioGroup contact = findViewById(R.id.contactRadio);
        RadioGroup containment = findViewById(R.id.containmentRadio);
        //used to check if a field added by user is empty
        // if (TextUtils.isEmpty(company.getText()))
        //with RadioGroup buttons we use getCheckedRadioButtonId() function

        if (TextUtils.isEmpty(company.getText())){
            company.setError("Company Name is required");
        }
        if (TextUtils.isEmpty(name.getText())){
            name.setError("Name is required");
        }
        if (TextUtils.isEmpty(email.getText())){
            email.setError("e-mail id is required");
        }
        if(TextUtils.isEmpty(phone.getText())){
            phone.setError("Phone Number is required");
        }
        if (TextUtils.isEmpty(temperature.getText())){
            temperature.setError("Body Temperature is required.");
        }
        if (symptoms.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();
        }
        if (absence.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();
        }
        if (overseas.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();
        }
        if (contact.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();
        }
        if (containment.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill in all the yes/no questions",Toast.LENGTH_SHORT).show();
        }
    }

    public void addToDatabase(View view){

        // variables
        EditText company = findViewById(R.id.companyName);
        EditText name = findViewById(R.id.employeeName);
        EditText email = findViewById(R.id.emailName);
        EditText phone = findViewById(R.id.phoneNumber);
        RadioButton symptoms1=findViewById(R.id.yesSymptom);
        RadioButton absence1=findViewById(R.id.yesAbsence);
        RadioButton overseas1=findViewById(R.id.yesOverseas);
        RadioButton contact1=findViewById(R.id.yesContact);
        RadioButton containment1= findViewById(R.id.yesContainment);
        EditText temperature = findViewById(R.id.temparatureFarenheit);
        Double temp = Double.parseDouble(temperature.getText().toString());

        // SQLite database addition code.
        CustomerModel customerModel = new CustomerModel(1,company.getText().toString(),name.getText().toString(),
                email.getText().toString(),phone.getText().toString(),symptoms1.isChecked(),absence1.isChecked(),
                overseas1.isChecked(),contact1.isChecked(),temp,containment1.isChecked());

        Toast.makeText(EmployeeActivity.this,customerModel.toString(),Toast.LENGTH_LONG).show();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(EmployeeActivity.this);
        boolean success = dataBaseHelper.addOne(customerModel);
        Toast.makeText(EmployeeActivity.this,"Success="+success,Toast.LENGTH_SHORT).show();

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




    }
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
}
