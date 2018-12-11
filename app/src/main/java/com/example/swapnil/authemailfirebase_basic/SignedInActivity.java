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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignedInActivity extends AppCompatActivity {

    ImageView imageView;
    TextView displayName;


    private static final String TAG = "Signed In Activity";
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        getSupportActionBar().setTitle("Signed In Activity");

        user = FirebaseAuth.getInstance().getCurrentUser();



        imageView = findViewById(R.id.imageView);
        displayName = findViewById(R.id.displayName);


        updateUI(user);


    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .into(imageView);

            displayName.setText(user.getDisplayName());

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkAuthenticationState();
        updateUI(user);
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

            case R.id.chat_menu:{
                startActivity(new Intent(SignedInActivity.this,ChatActivity.class));
            }

            case R.id.menu_profile:{
                Intent intent = new Intent(SignedInActivity.this,ProfileSettingsActivity.class);
                //TODO: Pass intent extras here
                startActivity(intent);
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

}
