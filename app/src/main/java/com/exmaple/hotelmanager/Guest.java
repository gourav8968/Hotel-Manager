package com.exmaple.hotelmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Guest extends AppCompatActivity {
    private ListView listView;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        cancel=findViewById(R.id.cancel);
        listView = findViewById(R.id.listView);
        final Room_Info helper = new Room_Info(this);
        final ArrayList array_list = helper.getAllCotacts();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(Guest.this,
                android.R.layout.simple_list_item_1, array_list);
        listView.setAdapter(arrayAdapter);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveTologin = new Intent(Guest.this,Home.class);
                startActivity(moveTologin);
            }
        });
    }
}
