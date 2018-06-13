package com.example.htw.myapplication.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.htw.myapplication.DataStorage.SharePreferencesConfig;
import com.example.htw.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    EditText inputEmail;
    EditText inputPass;

    String mCurrentPhotoPath;
    ImageView imageUser;
    Uri photoURI;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog mProgress;
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_PERMS = 123;

    SharePreferencesConfig sharePreferencesConfig;

    StorageReference firebaseStorageReference = FirebaseStorage.getInstance().getReference();

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String authority = getApplicationContext().getPackageName() + ".provider";

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, authority, photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                takePictureIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        } else {// No else ... #2 error
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            mProgress.setMessage("Uploading your image to database");
            mProgress.show();
            //Uri uri = data.getData();
            StorageReference filepatch = firebaseStorageReference.child("Photo").child(photoURI.getLastPathSegment());


            Glide.with(LoginActivity.this).load(photoURI).apply(RequestOptions.circleCropTransform()).into(imageUser);

            filepatch.putFile(photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(LoginActivity.this, "foto send to server !", Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                    // startActivity(new Intent(, LoginActivity.class));

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    //Picasso
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, "upload file ERROR", Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //open camera
            openCamera();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseStorageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, ShopActivity.class));
                }
            }
        };
        inputEmail = (EditText) findViewById(R.id.emailSign);
        inputPass = (EditText) findViewById(R.id.passSing);

        imageUser = findViewById(R.id.imageUpload);
        findViewById(R.id.imageUpload).setOnClickListener(this::ImageSend);
        findViewById(R.id.button_login).setOnClickListener(this::loginButton);

    }

    public void openCamera() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "I need your permision for take a foto", REQUEST_PERMS, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void ImageSend(View view) {
        openCamera();
        dispatchTakePictureIntent();


    }

    private void loginButton(View view) {
        startSingIn();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        mAuth.addAuthStateListener(authStateListener);


    }

    private void startSingIn() {

        String pastEmail = inputEmail.getText().toString();
        String pastePass = inputPass.getText().toString();

        if (TextUtils.isEmpty(pastEmail) || TextUtils.isEmpty(pastePass)) {

            Toast.makeText(this, "fields are empty", Toast.LENGTH_SHORT).show();

        } else {
            mAuth.signInWithEmailAndPassword(pastEmail, pastePass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful())
                        Toast.makeText(LoginActivity.this, "sign issue", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }



    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        openCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

}
