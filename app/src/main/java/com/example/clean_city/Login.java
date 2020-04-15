package com.example.clean_city;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.UUID;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button LoginBtn,Register_Btn;
    EditText LoginEmail, LoginPassword;
    FirebaseAuth mFriebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFriebaseAuth = FirebaseAuth.getInstance();
        LoginEmail = (EditText)findViewById(R.id.Login_email);
        LoginPassword = (EditText)findViewById(R.id.Login_password);
        LoginBtn = (Button)findViewById(R.id.Login);
        Register_Btn = (Button)findViewById(R.id.LoginRegister);

        LoginBtn.setOnClickListener(this);
        Register_Btn.setOnClickListener(this);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = mFriebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Toast.makeText(Login.this,"You are logged in",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Login.this,"Wrong Name Password",Toast.LENGTH_LONG).show();
                    FirebaseUser currentUser = mFriebaseAuth.getCurrentUser();
                }
            }
        };
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Login:
                String LoginN = LoginEmail.getText().toString();
                String Pass = LoginPassword.getText().toString();
                if (LoginN.isEmpty()) {
                    LoginEmail.setError("Please Enter the Name");
                    LoginEmail.requestFocus();
                }
                else if(Pass.isEmpty()){
                    LoginPassword.setError("Please Enter the password");
                    LoginPassword.requestFocus();
                }
                else if(LoginN.isEmpty() && Pass.isEmpty()){
                    Toast.makeText(Login.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }
                else {
                    mFriebaseAuth.signInWithEmailAndPassword(LoginN,Pass)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(Login.this,MainActivity.class));
                               // FirebaseUser user = mFriebaseAuth.getCurrentUser();
                                //UUID(user);
                            }
                            else {
                                Toast.makeText(Login.this,"Login Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                break;
            case R.id.LoginRegister:
                startActivity(new Intent(this, Registration.class));
                break;
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        mFriebaseAuth.addAuthStateListener(authStateListener);
        //currentUser = mFriebaseAuth.getCurrentUser();


    }

}
