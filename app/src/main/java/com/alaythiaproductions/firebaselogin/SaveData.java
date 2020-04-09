package com.alaythiaproductions.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveData extends AppCompatActivity {

    private EditText name, phoneNumber, address;
    private Button btnInsertData;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);

        btnInsertData = findViewById(R.id.insert_data);

        db = FirebaseDatabase.getInstance().getReference().child("User Data");


        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId, username, userPhone, userAddress;

                userId = db.push().getKey();
                username = name.getText().toString().trim();
                userPhone = phoneNumber.getText().toString().trim();
                userAddress = address.getText().toString().trim();

                if (username.equals("")) {
                    Toast.makeText(SaveData.this, "User Name Required", Toast.LENGTH_SHORT).show();
                } else if (userPhone.equals("")) {
                    Toast.makeText(SaveData.this, "Phone Number Required", Toast.LENGTH_SHORT).show();
                } else if (userAddress.equals("")) {
                    Toast.makeText(SaveData.this, "Address Required", Toast.LENGTH_SHORT).show();
                } else {
                    UserData data = new UserData(userId, username, userPhone, userAddress);
                    db.child(userId).setValue(data);
                    Toast.makeText(SaveData.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SaveData.this, Home.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
