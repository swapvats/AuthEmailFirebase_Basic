package com.example.swapnil.authemailfirebase_basic;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileSettingsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 121;
    EditText setName,setPhoto;
    Button set,cancel,choosePhoto;
    private StorageReference mStorageRef;

    Uri photoUri;
    private boolean mStoragePermissions=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        setName = findViewById(R.id.setName);
        setPhoto = findViewById(R.id.photoUri);
        set = findViewById(R.id.setButton);
        cancel = findViewById(R.id.cancel);
        choosePhoto = findViewById(R.id.chooseButton);

        //Getting Ref
        mStorageRef = FirebaseStorage.getInstance().getReference();



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

        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions();
                if (mStoragePermissions) {
                    getPhotos();


                }
            }
        });











    }

    private void getPhotos() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1215);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1215 && resultCode == Activity.RESULT_OK){
            Uri selectedImageUri = data.getData();
            Log.d("SEE ME", "onActivityResult: image "+selectedImageUri);

            photoUri = selectedImageUri;

        }

    }

    void setUserDetails(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(setName.getText().toString())
                    .setPhotoUri(photoUri)
                  //  .setPhotoUri(Uri.parse(setPhoto.getText().toString()))

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

    public void verifyStoragePermissions(){
        Log.d("SEE ME", "verifyStoragePermissions: Asking For Permission");
        // What permission you want
        String[] permissions = {
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[0])== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[1])==PackageManager.PERMISSION_GRANTED)
        {
            mStoragePermissions = true;
        }
        else {
            ActivityCompat.requestPermissions(ProfileSettingsActivity.this,permissions,REQUEST_CODE);
        }

    }
    // Permission milne pe kya krna
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:{
                if (grantResults.length>0){
                    boolean read =  grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean write = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (read && write){
                    //    getPhotos();
                    }
                    else {
                        Toast.makeText(this, "GRANT PERMISSIONS", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        }

    }
}
