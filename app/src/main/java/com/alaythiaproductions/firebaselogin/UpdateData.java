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

public class UpdateData extends AppCompatActivity {

    EditText updateName, updatePhone, updateAddress;
    Button btnUpdate;
    String id, name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        updateName = findViewById(R.id.update_name);
        updatePhone = findViewById(R.id.update_phone_number);
        updateAddress = findViewById(R.id.update_address);

        btnUpdate = findViewById(R.id.update_data);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        address = intent.getStringExtra("address");

        updateName.setText(name);
        updatePhone.setText(phone);
        updateAddress.setText(address);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("User Data").child(id);
                UserData userData = new UserData();
                userData.setName(name);
                userData.setPhone(phone);
                userData.setAddress(address);
                dbRef.setValue(userData);
                Toast.makeText(UpdateData.this, "Data Updated", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateData.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
}
