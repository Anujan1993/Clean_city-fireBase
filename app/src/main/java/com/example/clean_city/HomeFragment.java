package com.example.clean_city;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "Tested";
    CheckBox foodWaste, plasticWaste, metalWaste, paperWaste;
    String ClickedMetalWaste = "", ClickedFoodWaste = "",ClickedPlasticWaste = "";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    Button submit;
    String NameOf = "";
    ArrayList<String> l1;
    Jobs jobs;
    String currentDateTimeString;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        foodWaste = (CheckBox) view.findViewById(R.id.FoodWaste);
        plasticWaste = (CheckBox) view.findViewById(R.id.Plastic_waste);
        metalWaste = (CheckBox) view.findViewById(R.id.metalWaste);
        paperWaste = (CheckBox)view.findViewById(R.id.PaperWaste);
        submit = (Button) view.findViewById(R.id.WasteSubmit);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        l1 = new ArrayList<String>();

        jobs = new Jobs();


        userId = firebaseAuth.getCurrentUser().getUid();
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());


        firebaseFirestore.collection("users").whereEqualTo("uuID", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //  nameHome.setText((CharSequence) document.get("name"));
                                NameOf = String.valueOf(document.get("uuID"));

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        submit.setOnClickListener(this);
        return  view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.WasteSubmit:
                if (metalWaste.isChecked()) {
                    ClickedMetalWaste = "Metal Waste";
                    l1.add(ClickedMetalWaste);
                    Toast.makeText(getContext(), "Checked", Toast.LENGTH_LONG).show();
                }
                if (foodWaste.isChecked()) {
                    ClickedFoodWaste = "Food Waste";
                    l1.add(ClickedFoodWaste);
                }
                if (plasticWaste.isChecked()) {
                    ClickedPlasticWaste = "Plastic Waste";
                    l1.add(ClickedPlasticWaste);
                }
                if (paperWaste.isChecked()) {
                    ClickedPlasticWaste = "Paper Waste";
                    l1.add(ClickedPlasticWaste);
                }
                dataInDb(l1, NameOf, currentDateTimeString);
                break;
        }
    }
    public void dataInDb(ArrayList<String> wastes, String UUID, String Date) {
        jobs.setWastes(wastes);
        jobs.setUserID(UUID);
        jobs.setDate(Date);
        firebaseFirestore.collection("jobs")
                .add(jobs)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        Toast.makeText(getActivity(), "Request Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getContext(), Login.class));
    }
}

