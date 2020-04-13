package com.example.clean_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button Register;
    EditText reg_uname,Phone_number,Post_Address,reg_password,Confom_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        reg_uname = (EditText)findViewById(R.id.reg_uname);
        Phone_number = (EditText)findViewById(R.id.Phone_number);
        Post_Address = (EditText)findViewById(R.id.Post_Address);
        reg_password = (EditText)findViewById(R.id.reg_password);
        Confom_password = (EditText)findViewById(R.id.Confom_password);

        Register = (Button)findViewById(R.id.Register);

        Register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register:
                String name = reg_uname.getText().toString();
                String Address = Post_Address.getText().toString();
                String Phone = Phone_number.getText().toString();
                String Password = reg_password.getText().toString();

                User registedData= new User(name,Address,Phone,Password);
                break;
        }
    }
}
