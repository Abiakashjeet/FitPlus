package com.example.fitplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView maps,bmi,exercise,events;
    Button logout_btn;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maps = findViewById(R.id.cardview_maps_iv);
        bmi = findViewById(R.id.cardview_bmi_iv);
        exercise = findViewById(R.id.cardview_exercise_iv);
        events = findViewById(R.id.cardview_events_iv);
        logout_btn = findViewById(R.id.logout_btn);

        maps.setOnClickListener(this);
        bmi.setOnClickListener(this);
        exercise.setOnClickListener(this);
        events.setOnClickListener(this);
        logout_btn.setOnClickListener(this);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardview_maps_iv:
                Intent intent = new Intent(getApplicationContext(),Mapgoogle.class);
                startActivity(intent);
                break;

            case R.id.cardview_bmi_iv:
                Intent intent1 = new Intent(getApplicationContext(), Webview.class);
                startActivity(intent1);
                break;

            case R.id.cardview_exercise_iv:
                Intent intent2 = new Intent(getApplicationContext(),Exercise.class);
                startActivity(intent2);
                break;

            case R.id.cardview_events_iv:
                Intent intent3 = new Intent(getApplicationContext(),Events.class);
                startActivity(intent3);
                break;


            case R.id.logout_btn:
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent4 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent4);
                break;
        }

    }
}