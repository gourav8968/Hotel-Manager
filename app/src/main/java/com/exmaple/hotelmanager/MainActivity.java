package com.exmaple.hotelmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button register,login;
    DatabaseHelper db;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;
    CheckBox savelogincheckbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login);
        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        savelogincheckbox = (CheckBox) findViewById(R.id.checkBox);
        editor=sharedPreferences.edit();
        savelogin=sharedPreferences.getBoolean("savelogin", true);
        if(savelogin==true)
        {
            username.setText(sharedPreferences.getString("username", null));
            password.setText(sharedPreferences.getString("password", null));
        }
        db =new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamevalue=username.getText().toString();
                String passwordvalue=password.getText().toString();
                Boolean res = db.checkUser(usernamevalue, passwordvalue);
                if(res== true)
                {
                    if(savelogincheckbox.isChecked())
                    {
                        editor.putBoolean("savelogin", true);
                        editor.putString("username", usernamevalue);
                        editor.putString("password", passwordvalue);
                        editor.commit();
                    }
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
