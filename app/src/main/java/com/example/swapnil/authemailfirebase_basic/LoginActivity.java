package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    
    TextView register,forgotEmail,resendMail;
    EditText email,password;
    Button button;
    ImageView profile_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



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
    }
}

