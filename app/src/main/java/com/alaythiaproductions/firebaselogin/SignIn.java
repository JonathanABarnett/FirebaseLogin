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

public class SignIn extends AppCompatActivity {

    private Button btnSignIn;
    private EditText signInEmail, signInPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();

        signInEmail = findViewById(R.id.sign_in_email);
        signInPassword = findViewById(R.id.sign_in_password);
        btnSignIn = findViewById(R.id.sign_in_button);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        String email = signInEmail.getText().toString().trim();
        String password = signInPassword.getText().toString().trim();

        if (email.equals("")) {
            Toast.makeText(SignIn.this, "Email Required", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(SignIn.this, "Password Required", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(SignIn.this, "Password Must Be 6 Characters or More", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignIn.this, "You are now signed in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignIn.this, "What did you do?", Toast.LENGTH_SHORT).show();
                    }

                }

            });
        }
    }

}
