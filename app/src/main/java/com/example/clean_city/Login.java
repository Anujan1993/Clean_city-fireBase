package com.example.clean_city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button LoginBtn,Register_Btn;
    EditText LoginName, LoginPassword;
    UserLocalStorage userLocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginName = (EditText)findViewById(R.id.Login_Name);
        LoginPassword = (EditText)findViewById(R.id.Login_password);
        LoginBtn = (Button)findViewById(R.id.Login);
        Register_Btn = (Button)findViewById(R.id.LoginRegister);

        LoginBtn.setOnClickListener(this);
        Register_Btn.setOnClickListener(this);
        userLocalStorage = new UserLocalStorage(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login:
                User user = new User(null,null,null,null);
                userLocalStorage.StoreUserData(user);
                userLocalStorage.setUserLockedIn(true);
                break;
            case R.id.LoginRegister:
                startActivity(new Intent(this, Registration.class));
                break;
        }
    }
}
