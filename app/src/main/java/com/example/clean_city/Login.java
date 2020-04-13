package com.example.clean_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button LoginBtn;
    EditText LoginName, LoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginName = (EditText)findViewById(R.id.Login_Name);
        LoginPassword = (EditText)findViewById(R.id.Login_password);
        LoginBtn = (Button)findViewById(R.id.Login);

        LoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login:

                break;
        }
    }
}
