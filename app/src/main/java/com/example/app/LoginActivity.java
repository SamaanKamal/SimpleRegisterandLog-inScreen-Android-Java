package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Clock;

public class LoginActivity extends AppCompatActivity {

    UsersDBHelper users= new UsersDBHelper(this);

    EditText UserName;
    EditText Password;
    TextView Txt_Register;
    CheckBox remember;
    Button Btn_Login;
    TextView Txt_Forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        UserName=findViewById(R.id.L_UserName);
        Password=findViewById(R.id.L_Password);
        Btn_Login=findViewById(R.id.btn_Login);
        Txt_Register=findViewById(R.id.RigesterAction);
        remember=findViewById(R.id.CB_remember);
        Txt_Forget=findViewById(R.id.ForgetPass);



        Txt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(RegisterIntent);
            }
        });

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = UserName.getText().toString();
                String Pass = Password.getText().toString();

                if(User.equals("Admin")&&Pass.equals("Admin"))
                {
                    Toast.makeText(LoginActivity.this,"You Have Successfully logged in as Admin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean checker = false;
                    checker= users.CheckUser(User,Pass);
                    if(checker)
                    {
                        Toast.makeText(LoginActivity.this,"You have Logged In Successfully",Toast.LENGTH_SHORT).show();
                        Intent MainIntent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(MainIntent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"UserName or Password invalid, please try again",Toast.LENGTH_SHORT).show();
                    }
                }
                }

        });

        Txt_Forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateIntent = new Intent(LoginActivity.this,UpdateActivity.class);
                startActivity(UpdateIntent);
            }
        });

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {
            Intent MainIntent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(MainIntent);
        }
        else if(checkbox.equals("false"))
        {
            //Toast.makeText(LoginActivity.this,"UserName or Password invalid, please try again",Toast.LENGTH_SHORT).show();
        }

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"Checked",Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"UnChecked",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}