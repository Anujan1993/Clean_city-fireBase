package com.example.clean_city;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox foodWaste, plasticWaste, metalWaste;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    Button submit;
    TextView nameHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodWaste = (CheckBox)findViewById(R.id.FoodWaste);
        plasticWaste =(CheckBox)findViewById(R.id.Plastic_waste);
        metalWaste = (CheckBox)findViewById(R.id.MetalWaste);
        nameHome = (TextView)findViewById(R.id.nameHome);
        submit = (Button)findViewById(R.id.WasteSubmit);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
        submit.setOnClickListener(this);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nameHome.setText(documentSnapshot.getString("name"));
            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    public void onClick(View v) {

    }
}
