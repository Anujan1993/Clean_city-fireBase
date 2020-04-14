package com.example.clean_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button Register;
    EditText reg_uname,Phone_number,Post_Address,reg_password,Confom_password;
    FirebaseAuth mFriebaseAuth;
    DatabaseReference reference;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFriebaseAuth = FirebaseAuth.getInstance();
        reg_uname = (EditText)findViewById(R.id.reg_uname);
        Phone_number = (EditText)findViewById(R.id.Phone_number);
        Post_Address = (EditText)findViewById(R.id.Post_Address);
        reg_password = (EditText)findViewById(R.id.reg_password);
        Confom_password = (EditText)findViewById(R.id.Confom_password);

        Register = (Button)findViewById(R.id.Register);
        member = new Member();
        reference = FirebaseDatabase.getInstance().getReference().child("Member");

        Register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register:
                String LoginN = reg_uname.getText().toString();
                String Pass = reg_password.getText().toString();
                String Phone_N = Phone_number.getText().toString();
                String Address = Post_Address.getText().toString();
                String ConfP = Confom_password.getText().toString();

                if (LoginN.isEmpty()) {
                    reg_uname.setError("Please Enter the Name");
                    reg_uname.requestFocus();
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

                        member.setName(LoginN);
                        member.setAddress(Address);
                        member.setPhone(Phone_N);
                        member.setPassword(Pass);

                        reference.push().setValue(member);
                        Toast.makeText(Registration.this,"Registered Success",Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}
