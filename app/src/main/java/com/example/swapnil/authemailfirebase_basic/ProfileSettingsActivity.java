package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileSettingsActivity extends AppCompatActivity {

    EditText setName,setPhoto;
    Button set,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        setName = findViewById(R.id.setName);
        setPhoto = findViewById(R.id.photoUri);
        set = findViewById(R.id.setButton);
        cancel = findViewById(R.id.cancel);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserDetails();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileSettingsActivity.this,SignedInActivity.class));
            }
        });









    }

    void setUserDetails(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(setName.getText().toString())
                    .setPhotoUri(Uri.parse(setPhoto.getText().toString()))
                    .build();

            user.updateProfile(profileChangeRequest)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Log.d("XXX", "onComplete: Done");
                                Toast.makeText(ProfileSettingsActivity.this, "Done:)", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProfileSettingsActivity.this,SignedInActivity.class));
                            }
                        }
                    });
        }
    }
}
