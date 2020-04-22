package com.example.clean_city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DB_ACCESS";
    Button Register;
    EditText reg_uname,Phone_number,Post_Address,reg_password,Confom_password,Email;
    FirebaseAuth mFriebaseAuth;
    //DatabaseReference reference;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db;
    Member member;
    String UID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFriebaseAuth = FirebaseAuth.getInstance();
        reg_uname = (EditText)findViewById(R.id.Up_uname);
        Phone_number = (EditText)findViewById(R.id.Up_Phone_number);
        Post_Address = (EditText)findViewById(R.id.Up_Post_Address);
        reg_password = (EditText)findViewById(R.id.Up_password);
        Confom_password = (EditText)findViewById(R.id.Up_Confom_password);
        Email = (EditText)findViewById(R.id.Up_email);

        Register = (Button)findViewById(R.id.update);
        member = new Member();
        //reference = FirebaseDatabase.getInstance().getReference().child("Member");


        // Access a Cloud Firestore instance from your Activity
         db = FirebaseFirestore.getInstance();

         Register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update:
                final String LoginN = reg_uname.getText().toString();
                String Pass = reg_password.getText().toString();
                final String Phone_N = Phone_number.getText().toString();
                final String email_ad = Email.getText().toString();
                final String Address = Post_Address.getText().toString();
                String ConfP = Confom_password.getText().toString();


                if (LoginN.isEmpty()) {
                    reg_uname.setError("Please Enter the Name");
                    reg_uname.requestFocus();
                }

                else if(email_ad.isEmpty()){
                    Email.setError("Please Enter the email address");
                    Email.requestFocus();
                }

                else if(Phone_N.isEmpty()){
                    Phone_number.setError("Please Enter the phone number");
                    Phone_number.requestFocus();
                }
                else if(Address.isEmpty()){
                    Post_Address.setError("Please Enter the Address");
                    Post_Address.requestFocus();
                }
                else if(Pass.isEmpty()){
                    reg_password.setError("Please Enter the password");
                    reg_password.requestFocus();
                }
                else if(ConfP.isEmpty()){
                    Confom_password.setError("Please Enter the Conform Password");
                    Confom_password.requestFocus();
                }
                else if(LoginN.isEmpty() && Pass.isEmpty() && Phone_N.isEmpty() && Address.isEmpty() && ConfP.isEmpty()){
                    Toast.makeText(Registration.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }
                else{
                    if (Pass.equals(ConfP)) {

                        mFriebaseAuth.createUserWithEmailAndPassword(email_ad,Pass)
                                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    UID = mFriebaseAuth.getCurrentUser().getUid();
                                    registerInDb(LoginN,email_ad,Address,Phone_N,UID);
                                    Toast.makeText(Registration.this,"DataAdded",Toast.LENGTH_LONG).show();

                                }
                            }
                        });


                    }
                    else {
                        Confom_password.setError("Passwords are not Matched");
                        Confom_password.requestFocus();
                    }

                }
                break;
        }
    }


    public void registerInDb(String  LoginN, String email_ad, String Address, String Phone_N,String UID){
        member.setName(LoginN);
        member.setEmail(email_ad);
        member.setAddress(Address);
        member.setPhone(Phone_N);
        member.setUuID( UID);
        db.collection("users").document(UID)
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
        Toast.makeText(Registration.this, "Registered Success please login here", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Login.class));
    }
}
