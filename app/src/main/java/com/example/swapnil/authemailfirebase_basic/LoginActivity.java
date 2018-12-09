package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    
    TextView register,forgotEmail,resendMail;
    EditText email,password;
    Button login;
    ImageView profile_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login = findViewById(R.id.login);
        register = findViewById(R.id.registerTextView);
        forgotEmail = findViewById(R.id.forgotTextView);
        resendMail = findViewById(R.id.resendEmailTextView);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        
        
        resendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "What The Fuck", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()))
                logMeIn(email.getText().toString(),password.getText().toString());
                else {
                    Toast.makeText(LoginActivity.this, "Fill both", Toast.LENGTH_SHORT).show();
                }

            }
        });



        
    }


    void logMeIn(String email,String password){


                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Welcome "+user.getEmail(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


    }


