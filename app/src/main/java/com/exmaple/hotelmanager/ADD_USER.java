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

public class ADD_USER extends AppCompatActivity {
    EditText username,password,email,country,dob;
    RadioGroup gender;
    Button register,cancel;
    Room_Info db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d__u_s_e_r);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        country=findViewById(R.id.country);
        dob=findViewById(R.id.dob);
        gender=findViewById(R.id.gender);
        register=findViewById(R.id.register);
        cancel=findViewById(R.id.cancel);
        db = new Room_Info(this);
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
                    Toast.makeText(ADD_USER.this, "Room Successfully Booked", Toast.LENGTH_SHORT).show();
                    Intent moveTologin = new Intent(ADD_USER.this,Home.class);
                    startActivity(moveTologin);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveTologin = new Intent(ADD_USER.this,Home.class);
                startActivity(moveTologin);
            }
        });
    }
}
