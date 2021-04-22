package com.example.fitplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitplus.Models.Accounts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText Email,Password;
    TextView ForgetPassword,SignUp;
    Button Login;

    FirebaseFirestore dbfirebase;

    int User_Type;

    SharedPreferences mpref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.login_email_et);
        Password = findViewById(R.id.login_password_et);
        ForgetPassword = findViewById(R.id.login_forgetpassword_tv);
        SignUp = findViewById(R.id.login_signup_tv);
        Login = findViewById(R.id.login_login_btn);

        dbfirebase = FirebaseFirestore.getInstance();

        Login.setOnClickListener(this);
        SignUp.setOnClickListener(this);

        mpref = getSharedPreferences("Login_Details",MODE_PRIVATE);
        Intent user_intent = new Intent(this.getApplicationContext(),MainActivity.class);

        if(mpref.contains("Email") && mpref.contains("Password") && mpref.contains("type")){
            if (mpref.getString("type","").equals("1"))
                startActivity(user_intent);
        }
    }
@Override
    public void onClick(View v){
     switch (v.getId()){
         case R.id.login_login_btn:

             String email = Email.getText().toString();
             String password = Password.getText().toString();

             if(email.length()==0)
             {
                 Email.requestFocus();
                 Email.setError("Required");
             }
             else if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                 Email.requestFocus();
                 Email.setError("Enter a Valid Email");
             }
             else if(password.length()==0)
             {
                 Password.requestFocus();
                 Password.setError("Required");
             }
             else{
                 Toast.makeText(getApplicationContext(),"In progress",Toast.LENGTH_LONG).show();
                 get_user(email,password);
             }
             break;

         case R.id.login_signup_tv:
             Intent intent = new Intent(getApplicationContext(),SignUp.class);
             startActivity(intent);
             break;
     }
    }

    public void get_user(String Email,String Password){
        dbfirebase.collection("Accounts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Accounts account = document.toObject(Accounts.class);
                                if(Email.equals(account.getEmail()) && Password.equals(account.getPassword())){
                                   User_Type = Integer.valueOf(account.getUser_Type().toString());
                                   editor = mpref.edit();
                                   editor.putString("Email",Email);
                                   editor.putString("Password",Password);
                                   editor.putString("Type",account.getUser_Type().toString());
                                   editor.commit();

                                   if(User_Type == 1){
                                       Intent intent = new Intent(Login.this,MainActivity.class);
                                       Login.this.startActivity(intent);
                                   }
                                   break;
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
    }
}