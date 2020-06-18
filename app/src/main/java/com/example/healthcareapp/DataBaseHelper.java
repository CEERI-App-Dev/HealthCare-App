package com.example.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String EMPLOYEE_TABLE = "EMPLOYEE_TABLE";
    public static final String COLUMN_COMPANY = "COMPANY";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_SYMPTOMS = "SYMPTOMS";
    public static final String COLUMN_ABSENCE = "ABSENCE";
    public static final String COLUMN_OVERSEAS = "OVERSEAS";
    public static final String COLUMN_CONTACT = "CONTACT";
    public static final String COLUMN_TEMPERATURE = "TEMPERATURE";
    public static final String COLUMN_CONTAINMENT = "CONTAINMENT";
    public static final String COLUMN_ID = "ID";
    List<CustomerModel> returnListFinal= new ArrayList<>();

    public DataBaseHelper(@Nullable Context context) {
        super(context, "employee.db", null, 1);
    }

    private FirebaseFirestore mFirebase = FirebaseFirestore.getInstance();
    // this is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + EMPLOYEE_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMPANY + " TEXT, " + COLUMN_NAME + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONE + " TEXT," + COLUMN_SYMPTOMS + " BOOLEAN, " + COLUMN_ABSENCE + " BOOLEAN, " + COLUMN_OVERSEAS + " BOOLEAN, " + COLUMN_CONTACT + " BOOLEAN," + COLUMN_TEMPERATURE + " DOUBLE, " + COLUMN_CONTAINMENT + " BOOLEAN )";

        db.execSQL(createTableStatement);
    }

    // this is called if the database version number is changed. It prevents previous users app from breaking when change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COMPANY, customerModel.getCompany());
        cv.put(COLUMN_NAME, customerModel.getName());
        cv.put(COLUMN_EMAIL, customerModel.getEmail());
        cv.put(COLUMN_PHONE, customerModel.getPhone());
        cv.put(COLUMN_SYMPTOMS, customerModel.isSymptoms());
        cv.put(COLUMN_ABSENCE, customerModel.isAbsence());
        cv.put(COLUMN_OVERSEAS, customerModel.isOverseas());
        cv.put(COLUMN_CONTACT, customerModel.isContact());
        cv.put(COLUMN_TEMPERATURE, customerModel.getTemp());
        cv.put(COLUMN_CONTAINMENT, customerModel.isVisit());

        long insert = db.insert(EMPLOYEE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<CustomerModel> getEveryone(String company) {
        List<CustomerModel> returnList = new ArrayList<>();
        // get data from database
        String queryString = "SELECT * FROM " + EMPLOYEE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through cursor(result set) and create new employee objects. Put them into the return list.
            do {
                int employeeID = cursor.getInt(0);
                String companyName = cursor.getString(1);
                String employeeName = cursor.getString(2);
                String email = cursor.getString(3);
                String phone = cursor.getString(4);
                Boolean symptoms = cursor.getInt(5) == 1 ? true : false;
                Boolean absence = cursor.getInt(6) == 1 ? true : false;
                Boolean overseas = cursor.getInt(7) == 1 ? true : false;
                Boolean contact = cursor.getInt(8) == 1 ? true : false;
                Double temp = cursor.getDouble(9);
                Boolean visit = cursor.getInt(10) == 1 ? true : false;

                CustomerModel newCustomer = new CustomerModel(employeeID, companyName, employeeName,
                        email, phone, symptoms, absence, overseas, contact, temp, visit);
                returnList.add(newCustomer);

            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything to the list.
        }
        // close both the cursor and database when done.
        cursor.close();
        db.close();
        return returnList;
    }

    public List<CustomerModel> getFirestore(){

        //final Map<String, Object>[] employees = new Map<String, Object>[1];
        EmployeeModel employeeModel = new EmployeeModel();
        mFirebase.collection("employees")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    List<CustomerModel> returnList= new ArrayList<>();
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              //employeeModel[0] = (EmployeeModel) document.getData();
                                Log.d("reading successful", document.getId() + " => " + document.getData());
                                int employeeID = i;
                                String companyName = document.getString("company");
                                String employeeName = document.getString("company");
                                String email = document.getString("email");
                                String phone = document.getString("phone");
                                Boolean symptoms = document.getBoolean("symptoms");
                                Boolean absence = document.getBoolean("absence");
                                Boolean overseas = document.getBoolean("overseas");
                                Boolean contact = document.getBoolean("contact");
                                Double temp = document.getDouble("temperature");
                                Boolean visit = document.getBoolean("visit");
//                                employeeModel.getCompany(document.getString("company"));
//                                employeeModel.getName(document.getString("company"));
//                                employeeModel.getEmail(document.getString("email"));
//                                employeeModel.getPhone(document.getString("phone"));
//                                employeeModel.isSymptoms(document.getBoolean("symptoms"));
//                                employeeModel.isAbsence(document.getBoolean("absence"));
//                                employeeModel.isOverseas(document.getBoolean("overseas"));
//                                employeeModel.isContact(document.getBoolean("contact"));
//                                employeeModel.getTemperature(document.getDouble("temperature"));
//                                employeeModel.setVisit(document.getBoolean("visit"));
                                CustomerModel newCustomer = new CustomerModel(employeeID, companyName, employeeName,
                                        email, phone, symptoms, absence, overseas, contact, temp, visit);
                                i++;
                                returnList.add(newCustomer);
                                Log.d("OKAY",returnList.toString());
                                returnListFinal=returnList;
                            }
                        }

                        else {
                            Log.w("unable to read", "Error getting documents.", task.getException());
                        }
                        final List<CustomerModel> returnList = DataBaseHelper.this.returnListFinal;
                    }
                });
        return returnListFinal;

    }
    public List<CustomerModel> getFirestoreFinal(){
        return returnListFinal;
    }

}
