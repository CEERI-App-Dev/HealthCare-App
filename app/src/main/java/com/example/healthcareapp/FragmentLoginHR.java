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
public class FragmentLoginHR extends Fragment {

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


                i.putExtra("email", emailString);
                i.putExtra("password",passwordString);

                startActivity(i);

            }
        });

        return view;
    }
}
