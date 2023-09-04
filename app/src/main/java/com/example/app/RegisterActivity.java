package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    UsersDBHelper users;

    EditText inputUserName;
    EditText inputEmail;
    EditText inputPassword;
    EditText inputPhone;
    Button Btn_Register;
    TextView Txt_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        users=new UsersDBHelper(this);

        inputUserName = findViewById(R.id.R_UserName);
        inputEmail = findViewById(R.id.R_Email);
        inputPassword = findViewById(R.id.R_Password);
        inputPhone = findViewById(R.id.R_PhoneNumber);
        Btn_Register=findViewById(R.id.btn_Registration);
        Txt_Login=findViewById(R.id.LoginAction);


        Btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.CreateNewUser(inputUserName.getText().toString(),inputEmail.getText().toString(),inputPassword.getText().toString(),inputPhone.getText().toString());
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);

            }
        });

        Txt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
    }
}