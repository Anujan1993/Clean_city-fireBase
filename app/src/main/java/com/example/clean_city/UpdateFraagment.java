package com.example.clean_city;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.UUID;

public class UpdateFraagment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Tested";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    Member member;
    EditText upName, upAddress, upPhone, upPassword, upConPass, upEmail;
    Button upDate;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_of_client, container, false);
        upName = (EditText) view.findViewById(R.id.Up_uname);
        upAddress = (EditText) view.findViewById(R.id.Up_Post_Address);
        upEmail = (EditText) view.findViewById(R.id.Up_email);
        upPhone = (EditText) view.findViewById(R.id.Up_Phone_number);
        upPassword = (EditText) view.findViewById(R.id.Up_password);
        upConPass = (EditText) view.findViewById(R.id.Up_Confom_password);
        upDate = (Button) view.findViewById(R.id.update);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        //toolbar.setTitle("Update Profile");

        member = new Member();

        userId = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore.collection("users").whereEqualTo("uuID", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                upName.setText((CharSequence) document.get("name"));
                                upAddress.setText((CharSequence) document.get("address"));
                                upEmail.setText((CharSequence) document.get("email"));
                                upPhone.setText((CharSequence) document.get("phone"));

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        upDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                final String LoginN = upName.getText().toString();
                String Pass = upPassword.getText().toString();
                final String Phone_N = upPhone.getText().toString();
                final String email_ad = upEmail.getText().toString();
                final String Address = upAddress.getText().toString();
                String ConfP = upConPass.getText().toString();
                if (Pass.isEmpty()) {
                    UpdateInDb(LoginN,email_ad,Address,Phone_N,userId);
                    updateEmail(email_ad);

                } else {
                    if (Pass.equals(ConfP)) {
                        UpdateInDb(LoginN,email_ad,Address,Phone_N,userId);
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null){
                            user.updateEmail(email_ad).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        updatePass(upPassword.getText().toString());
                                    }
                                    else {
                                        Log.d(TAG, "error");
                                    }
                                }
                            });
                        }

                    } else {
                        upConPass.setError("Passwords are not Matched");
                        upConPass.requestFocus();
                    }
                }
                break;
        }
    }

    public void UpdateInDb(String LoginN, String email_ad, String Address, String Phone_N,String UID) {
        member.setName(LoginN);
        member.setEmail(email_ad);
        member.setAddress(Address);
        member.setPhone(Phone_N);
        member.setUuID(UID);
        firebaseFirestore.collection("users").document(UID)
                .set(member)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
    public void updatePass(String pass){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            user.updatePassword(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                  if (task.isSuccessful()){
                      Intent intent = new Intent(getContext(), Login.class);
                      startActivity(intent);
                      firebaseAuth.signOut();
                      getActivity().finish();
                  }
                  else {
                      Log.d(TAG, "error");
                  }
                }
            });
        }
    }
    public void updateEmail(String email){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                  if (task.isSuccessful()){
                      Intent intent = new Intent(getContext(), Login.class);
                      startActivity(intent);
                      firebaseAuth.signOut();
                      getActivity().finish();
                  }
                  else {
                      Log.d(TAG, "error");
                  }
                }
            });
        }
    }
}
