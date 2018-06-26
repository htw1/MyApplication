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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.htw.myapplication.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class AddNewPhotoActivity extends AppCompatActivity {

    String mCurrentPhotoPath;
    Uri photoURI;


    private String uploadImageUrl;
    private ProgressDialog mProgress;
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_PERMS = 123;

    StorageReference firebaseStorageReference = FirebaseStorage.getInstance().getReference();
    DatabaseReference dataBAseFirebase = FirebaseDatabase.getInstance().getReference().child("Blog");

    ImageButton imageButton;
    EditText editTextNamePhoto;
    EditText editTextDesc;

    String title_name_val;
    String desc_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_photo);
        mProgress = new ProgressDialog(this);

        //url_db = FirebaseDatabase.getInstance().getReference().child("Url");

        findViewById(R.id.addPhotoButton).setOnClickListener(this::addPhotoButton);
        imageButton = findViewById(R.id.addPhoto);
        findViewById(R.id.addPhoto).setOnClickListener(this::addPhoto);

        editTextNamePhoto = findViewById(R.id.editTextNamePhoto);
        editTextDesc = findViewById(R.id.editTextAddPhotoDesc);

    }

    private void addPhoto(View view) {
        openCamera();
        dispatchTakePictureIntent ();
    }

    public void addPhotoButton (View view) {

        title_name_val = editTextNamePhoto.getText().toString().trim();
        desc_val = editTextDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(title_name_val) && !TextUtils.isEmpty(desc_val) && photoURI != null){

            //Tworzy unikatowe ID
            DatabaseReference newPostDatabase = dataBAseFirebase.push();
            newPostDatabase.child("title").setValue(title_name_val);
            newPostDatabase.child("desc").setValue(desc_val);
            newPostDatabase.child("url_link").setValue(uploadImageUrl);

            findViewById(R.id.addPhotoButton).setEnabled(false);
            Toast.makeText(this, "Good JOB !", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddNewPhotoActivity.this, PhotoActivity.class));
        }

    }

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
                takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                takePictureIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
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
            StorageReference filepatch = firebaseStorageReference.child("PhotoSunset").child(photoURI.getLastPathSegment());

            if (imageButton != null) {
                Glide.with(AddNewPhotoActivity.this).load(photoURI).into(imageButton);
            }

            filepatch.putFile(photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filepatch.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            uploadImageUrl = downloadUrl.toString();
                            Log.i("photoURI",uploadImageUrl ) ;
                        }
                    });



                    Toast.makeText(AddNewPhotoActivity.this, "foto send" +
                            "  to server !", Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                    // startActivity(new Intent(, LoginActivity.class));


                   //Uri downloadUri = taskSnapshot.getDownloadUrl();
                    //Picasso
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddNewPhotoActivity.this, "upload file ERROR", Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

            //openCamera();
        }
    }
    public void openCamera() {
        String[] perms = {android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
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





}
