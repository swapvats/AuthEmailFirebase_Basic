package com.example.swapnil.authemailfirebase_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText registerEmail;
    EditText registerPassword1;
    EditText registerPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.registerButton);

        //TODO: Fucked here please t.c naming errors in layout
        registerEmail = findViewById(R.id.registerName);
        registerPassword1 = findViewById(R.id.registerEmail);
        registerPassword2 = findViewById(R.id.registerEmail2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerEmail.getText().toString().isEmpty() || registerPassword1.getText().toString().isEmpty() || registerPassword2.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Fill Out All Fields", Toast.LENGTH_SHORT).show();
                }
               else if (!registerPassword1.getText().toString().equals(registerPassword2.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerNewEmail(registerEmail.getText().toString(),registerPassword1.getText().toString());
                }
            }
        });






    }


    void registerNewEmail(String email,String password){




    }
}
