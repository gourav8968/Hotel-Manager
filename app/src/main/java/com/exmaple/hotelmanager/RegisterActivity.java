package com.exmaple.hotelmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,password,email,country,dob;
    RadioGroup gender;
    Button register,cancel;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        country=findViewById(R.id.country);
        dob=findViewById(R.id.dob);
        gender=findViewById(R.id.gender);
        register=findViewById(R.id.register);
        cancel=findViewById(R.id.cancel);
        db = new DatabaseHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamevalue=username.getText().toString();
                String passwordvalue=password.getText().toString();
                String emailvalue=email.getText().toString();
                String countryvalue=country.getText().toString();
                String dobvalue=dob.getText().toString();
                RadioButton checkbutton=findViewById(gender.getCheckedRadioButtonId());
                String gendervalue=checkbutton.getText().toString();
                long val = db.addUser(usernamevalue, passwordvalue, emailvalue, countryvalue, dobvalue, gendervalue);
                if(val > 0)
                {
                    Toast.makeText(RegisterActivity.this, "User Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent moveTologin = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(moveTologin);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveTologin = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(moveTologin);
            }
        });
    }
}
