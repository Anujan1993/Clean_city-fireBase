package com.example.clean_city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button LoginBtn,Register_Btn;
    EditText LoginName, LoginPassword;
    FirebaseAuth mFriebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFriebaseAuth = FirebaseAuth.getInstance();
        LoginName = (EditText)findViewById(R.id.Login_Name);
        LoginPassword = (EditText)findViewById(R.id.Login_password);
        LoginBtn = (Button)findViewById(R.id.Login);
        Register_Btn = (Button)findViewById(R.id.LoginRegister);

        LoginBtn.setOnClickListener(this);
        Register_Btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Login:
                String LoginN = LoginName.getText().toString();
                String Pass = LoginPassword.getText().toString();
                if (LoginN.isEmpty()) {
                    LoginName.setError("Please Enter the Name");
                    LoginName.requestFocus();
                }
                else if(Pass.isEmpty()){
                    LoginPassword.setError("Please Enter the password");
                    LoginPassword.requestFocus();
                }
                else if(LoginN.isEmpty() && Pass.isEmpty()){
                    Toast.makeText(Login.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.LoginRegister:
                startActivity(new Intent(this, Registration.class));
                break;
        }
    }
}
