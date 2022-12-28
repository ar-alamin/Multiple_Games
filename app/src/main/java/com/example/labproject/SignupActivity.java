package com.example.labproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText username, password, c_password;
    Button register;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        register = findViewById(R.id.registerButton);

        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String c_pass = c_password.getText().toString();

                if(user.equals("") || pass.equals("") || c_pass.equals("")) {
                    Toast.makeText(SignupActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(c_pass)) {
                        Boolean checkuser = DB.checkUsername(user);

                        if(checkuser == false) {
                            Boolean regResult = DB.insertData(user, pass);

                            if(regResult == true) {
                                Toast.makeText(SignupActivity.this, "Registration Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "User Already Exists!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignupActivity.this,"Password not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}