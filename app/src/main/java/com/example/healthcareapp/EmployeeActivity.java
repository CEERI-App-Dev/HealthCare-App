package com.example.healthcareapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE =1;
    ImageView imageView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        EditText email = findViewById(R.id.emailName);

        Intent intent=getIntent();
        String message = intent.getStringExtra("email");
        email.setText(message, TextView.BufferType.EDITABLE);

    }

    public void btnSave(View view) {


        EditText company = findViewById(R.id.companyName);


        EditText name = findViewById(R.id.employeeName);
        EditText phone = findViewById(R.id.phoneNumber);
        EditText email = findViewById(R.id.emailName);

        EditText visit = findViewById(R.id.visitPlace);
        EditText temperature = findViewById(R.id.temperatureFarenheit);
        RadioGroup symptoms = findViewById(R.id.symptomsRadio);
        RadioGroup absence = findViewById(R.id.absenceRadio);
        RadioGroup overseas = findViewById(R.id.overseasRadio);
        RadioGroup contact = findViewById(R.id.contactRadio);
        RadioGroup containment = findViewById(R.id.containmentRadio);
        imageView=findViewById(R.id.Image);

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
