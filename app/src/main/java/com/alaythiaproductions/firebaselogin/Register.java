package com.alaythiaproductions.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button btnSignUp;
    private EditText registerEmail, registerPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);

        btnSignUp = findViewById(R.id.register_button);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String email, password;
        email = registerEmail.getText().toString().trim();
        password = registerPassword.getText().toString().trim();

        if (email.equals("")) {
            Toast.makeText(Register.this, "Email Required", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(Register.this, "Password Required", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(Register.this, "Password Must Be 6 Characters or More", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, SignIn.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Register.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
