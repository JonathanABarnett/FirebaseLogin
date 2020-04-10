package com.alaythiaproductions.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button btnAddData, btnRetrieveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAddData = findViewById(R.id.add_data);
        btnRetrieveData = findViewById(R.id.retrieve_data);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SaveData.class);
                startActivity(intent);
                finish();
            }
        });

        btnRetrieveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, RetrieveData.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
