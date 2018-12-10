package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignedInActivity extends AppCompatActivity {

    ImageView imageView;


    private static final String TAG = "Signed In Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        setUserDetails();

        imageView = findViewById(R.id.imageView);


        if (user != null) {
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .into(imageView);

        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        checkAuthenticationState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signOut:{
             //   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SignedInActivity.this,LoginActivity.class);
                startActivity(intent);
            }

            case R.id.aboutMe:{
                startActivity(new Intent(SignedInActivity.this,UserInformationActivity.class));
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    void checkAuthenticationState(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            Intent intent = new Intent(SignedInActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else {
            Log.d(TAG, "checkAuthenticationState: User is Authentic");
        }
    }

    //TODO:: Bar bar setup krna jaruri hai kya?
    void setUserDetails(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName("Swapnil")
                    .setPhotoUri(Uri.parse("https://www.pinkvilla.com/files/styles/gallery-preview/public/Deepika%20Padukone%20Hot%20and%20Sexy.png?itok=tzugo3Jk"))
                    .build();

            user.updateProfile(profileChangeRequest)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Log.d(TAG, "onComplete: Done");
                            }
                        }
                    });
        }
    }
}
