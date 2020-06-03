package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLoginEmployee extends Fragment {
    public static final String EXTRA_MESSAGE =
            "com.example.healthcareapp.extra.EMAIL";
    public FragmentLoginEmployee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_login_employee, container, false);
        Button button=(Button)view.findViewById(R.id.login);
        final EditText password=view.findViewById(R.id.password);

        final EditText email=view.findViewById(R.id.username);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EmployeeActivity.class);


                final String emailString=email.getText().toString();
                final String passwordString=password.getText().toString();


                i.putExtra(EXTRA_MESSAGE, emailString);
                i.putExtra("password",passwordString);
                startActivity(i);
            }
        });

        return view;
    }


}
