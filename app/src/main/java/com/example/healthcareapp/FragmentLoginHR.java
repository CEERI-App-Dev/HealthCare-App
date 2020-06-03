package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLoginHR extends Fragment {

    String email1="nsbrocks@gmail.com";
    String email2="rohitrathi2411@gmail.com";
    String email3="agrawaldhairy@gmail.com";
    String password1="12345678";

    public FragmentLoginHR() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_h_r, container, false);
        Button button = (Button) view.findViewById(R.id.loginHR);

        final EditText password=view.findViewById(R.id.passwordHR);

        final EditText email=view.findViewById(R.id.usernameHR);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ManagerActivity.class);
                final String emailString=email.getText().toString();
                final String passwordString=password.getText().toString();

                String stringemail=emailString;
                String stringpass=passwordString;
                Boolean b=(email1.equals(stringemail)||email2.equals(stringemail)||email3.equals(stringemail));
                Boolean b2=(password1.equals(stringpass));
                if (b &&b2){
                i.putExtra("email", emailString);
                i.putExtra("password",passwordString);

                startActivity(i);}
                else{
                    Toast.makeText(getActivity(), "Wrong Input Commands", Toast.LENGTH_SHORT).show();

                }


            }
        });

        return view;
    }
}
