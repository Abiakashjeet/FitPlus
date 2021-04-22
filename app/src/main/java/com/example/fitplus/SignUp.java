package com.example.fitplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitplus.Models.Accounts;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    EditText name,email,age,password;

    Button signUp;
    Spinner signUp_spinner;

    String gender;

    FirebaseFirestore dbfirebase;

    List<String> genderArray = new ArrayList<String>();
    ArrayAdapter genderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.signUp_name_et);
        email = findViewById(R.id.signUp_email_et);
        age = findViewById(R.id.signUp_age_et);
        password = findViewById(R.id.signUp_password_et);
        signUp = findViewById(R.id.signUp_btn);
       signUp_spinner = findViewById(R.id.signUp_spinner);

       signUp_spinner.setOnItemSelectedListener(this);

       genderArray.add("Gender");
       genderArray.add("Male");
       genderArray.add("Female");
       genderArray.add("Others");
       genderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, genderArray);
       genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       signUp_spinner.setAdapter(genderAdapter);

        dbfirebase  = FirebaseFirestore.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Age = age.getText().toString();
                String Gender = gender;
                String Password = password.getText().toString();
                add_Data(Name,Email,Age,Gender,Password);
            }
        });
    }
    public void add_Data(String Name, String Email, String Age, String Gender, String Password)
    {
        String User_Type="User";
        Accounts account = new Accounts(Name,Email,Age,Gender,Password,User_Type);
        dbfirebase.collection("Accounts")
        .add(account)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Registered!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String  str = (String) adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        gender = adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}