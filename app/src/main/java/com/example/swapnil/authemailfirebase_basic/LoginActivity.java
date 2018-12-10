package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "XXXX";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthListner;
    
    TextView register, forgotPassword,resendMail;
    EditText email,password;
    Button login;
    ImageView profile_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login = findViewById(R.id.login);
        register = findViewById(R.id.registerTextView);
        forgotPassword = findViewById(R.id.forgotTextView);
        resendMail = findViewById(R.id.resendEmailTextView);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        setFirebaseAuth();
        
        
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

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty()) {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Mail Sent Check", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "No user exists like that", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else{
                    Toast.makeText(LoginActivity.this, "Fill in the email then click", Toast.LENGTH_SHORT).show();
                }
            }
        });



        
    }




    //Firebz

    void setFirebaseAuth(){
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){

                    if (user.isEmailVerified()){
                        Log.d(TAG, "onAuthStateChanged: Signed in");

                        Intent intent = new Intent(LoginActivity.this,SignedInActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this, "Check Mail", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                    }

                }else {

                    Log.d(TAG, "onAuthStateChanged: Signed Out");
                }
            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListner!=null){
            firebaseAuth.removeAuthStateListener(mAuthListner);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListner);
    }

    void logMeIn(String email, String password){


                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    //TODO: Hot Damn Check This Out
                                   // FirebaseUser user = firebaseAuth.getCurrentUser();

                                   Toast.makeText(LoginActivity.this, "Welcome ", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


    }


