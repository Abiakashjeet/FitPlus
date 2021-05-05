package com.example.fitplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitplus.Models.Accounts;
import com.example.fitplus.Models.EventsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Events extends AppCompatActivity {
    TextView eventText;
    FirebaseFirestore db;
    String event = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventText = findViewById(R.id.cardview_event_tv);

        db = FirebaseFirestore.getInstance();

        getData();
    }

    public void getData(){
        db.collection("EventsModel")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc:task.getResult()){
                                EventsModel eventsModel = doc.toObject(EventsModel.class);
                                eventText.setText(eventsModel.getEvents());
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