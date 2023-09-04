package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    UsersDBHelper users = new UsersDBHelper(this);

    EditText rEmail;
    EditText rPassword;
    EditText rConfirm;
    Button Btn_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        rEmail=findViewById(R.id.RS_email);
        rPassword=findViewById(R.id.RS_password);
        rConfirm=findViewById(R.id.RS_confirmPassword);
        Btn_reset=findViewById(R.id.reset);

        Btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString();
                String pass =rPassword.getText().toString();
                String ConfirmPass =rConfirm.getText().toString();
                boolean check = users.CheckResetUser(email);
                if(check)
                {
                    if(pass.equals(ConfirmPass))
                    {
                        Toast.makeText(UpdateActivity.this,"Your Password has been reset",Toast.LENGTH_SHORT).show();
                        users.updatePass(email,pass);
                        Intent LoginIntent = new Intent(UpdateActivity.this,LoginActivity.class);
                        startActivity(LoginIntent);

                    }
                    else
                    {
                        Toast.makeText(UpdateActivity.this,"Your Password is not matched",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(UpdateActivity.this,"Your Email is not correct please check it",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}