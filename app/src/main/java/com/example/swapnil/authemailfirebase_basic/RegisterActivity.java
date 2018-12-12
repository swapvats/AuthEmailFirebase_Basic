package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


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


    void sendVerficationEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Mail Send", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Sorry!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    void registerNewEmail(final String email, String password){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Done!!!!!!", Toast.LENGTH_SHORT).show();
                    // Verification
                    sendVerficationEmail();
                    User user = new User();
                    user.setName(email.substring(0,email.indexOf("@")));
                    user.setPhone("1");
                    user.setProfile_image("");
                    user.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    FirebaseDatabase.getInstance().getReference()
                            .child("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user);

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else {
                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    Toast.makeText(RegisterActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    Toast.makeText(RegisterActivity.this, "Couldn't Complete", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }
}
