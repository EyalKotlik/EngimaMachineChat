package com.example.enigmamachinechat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationActivity extends AppCompatActivity {
    public static final String TAG = "AuthenticationActivity";
    private FirebaseAuth firebaseAuth;

    // UI elements
    private Button buttonLogIn, buttonRegister;
    private EditText editTextEmail, editTextPassword;
    private String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_authentication);
        buttonLogIn = (Button) findViewById(R.id.buttonLogIn);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if (email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || password == null) {
                    Toast.makeText(AuthenticationActivity.this, "invalid email or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if (email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || password == null) {
                    Toast.makeText(AuthenticationActivity.this, "invalid email or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(AuthenticationActivity.this, "Registration failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
