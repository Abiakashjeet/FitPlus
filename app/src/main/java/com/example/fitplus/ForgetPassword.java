package com.example.fitplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitplus.Models.Accounts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ForgetPassword";

    EditText email;
    Button reset;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Log.d(TAG, "onCreate: Reset");

        email= findViewById(R.id.forgotPassword_email_et);
        reset = findViewById(R.id.forgetpassword_reset_btn);

        database = FirebaseFirestore.getInstance();
        reset.setOnClickListener(this);
        }
@Override
    public void onClick (View view){
        switch (view.getId()){

            case R.id.forgetpassword_reset_btn:

                String cemail= email.getText().toString();

                if(email.length()==0)
                {
                    email.requestFocus();
                    email.setError("Required");
                }
                else if(!cemail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                    email.requestFocus();
                    email.setError("Enter a Valid Email");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please wait while we process",Toast.LENGTH_LONG).show();
                    resetpassword(cemail);
                }
                break;
        }
    }

    public void resetpassword(String cemail) {
        database.collection("Accounts")
                .whereEqualTo("email", cemail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size()>0) {
                                for (DocumentSnapshot doc:task.getResult()) {

                                    Accounts accounts = doc.toObject(Accounts.class);
                                    Toast.makeText(ForgetPassword.this, "Password sent in Email!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgetPassword.this, Login.class);
                                    intent.putExtra("selected", accounts.getEmail());
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(ForgetPassword.this, "Failed...!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}


