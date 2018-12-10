package com.example.swapnil.authemailfirebase_basic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);







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
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
