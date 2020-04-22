package com.example.clean_city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    EditText resetGmail;
    Button resetButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        resetGmail = (EditText)findViewById(R.id.reset_email);
        resetButton =(Button)findViewById(R.id.SendEmail);
        firebaseAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SendEmail:
                String resetG = resetGmail.getText().toString();
                firebaseAuth.sendPasswordResetEmail(resetG).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgetPassword.this,"Password send to your email",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(ForgetPassword.this,"Email not found please register",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
        }
    }
}
