package com.example.htw.myapplication.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.htw.myapplication.Model.PhotoModel;
import com.example.htw.myapplication.R;

//import com.example.htw.myapplication.control.GlideApp;
import com.example.htw.myapplication.control.FireBaseRequestHandler;
import com.example.htw.myapplication.control.GlideApp;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhotoActivity extends AppCompatActivity {


    private RecyclerView recyclerList;

    FirebaseStorage mFirebaseStorage;
    StorageReference mStorageReferance;
    FirebaseRecyclerAdapter  firebaseRecyclerAdapter;
    Query query;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo);
        super.onCreate(savedInstanceState);


        mStorageReferance = FirebaseStorage.getInstance().getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //query = FirebaseDatabase.getInstance().getReference("Blog");
        query = FirebaseDatabase.getInstance().getReference().child("Blog");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        };
        query.addChildEventListener(childEventListener);

        recyclerList = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerList.setHasFixedSize(true);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<PhotoModel> options = new FirebaseRecyclerOptions.Builder<PhotoModel>()
                        .setQuery(query, PhotoModel.class)
                        .build();

        mFirebaseStorage = FirebaseStorage.getInstance();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PhotoModel, PhotoViewHolder>(options) {

            @Override
            public void onError(DatabaseError e) {
                Log.e("FirebaseRecyclerAdapter", e.toString());
            }
            @NonNull
            @Override
            public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_single_photo_row, parent, false);
                return new PhotoViewHolder (view) ;
            }
            @Override
            protected void onBindViewHolder(@NonNull PhotoViewHolder holder, int position, @NonNull PhotoModel model) {

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference imageNamesRef = rootRef.child("Blog");

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String, PhotoModel> map = (Map<String, PhotoModel>) dataSnapshot.getValue();
                        Log.d("SINGLE_VALUE", "Value is: " + map);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                imageNamesRef.addListenerForSingleValueEvent(eventListener);


                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                Picasso
                       .get()
                       .load(model.getUrl_link())
                       .error(R.drawable.ic_error_black_24dp)
                        .placeholder(R.drawable.placeholder)
                       .into(holder.firebaseImages );
            }
            /*                GlideApp.with(getBaseContext())
                        .load()
                        .centerCrop()
                        .error(R.drawable.ic_error_black_24dp)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Log.e("TAG_FROM_GLIDE", "Error loading image", e);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .fitCenter()
                        .into(holder.firebaseImages);*/

        };

        recyclerList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }


/*    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }*/

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        public  ImageView firebaseImages;

        View globalView;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            this.firebaseImages = (ImageView) itemView.findViewById(R.id.imageFirebase);
            globalView = itemView;
        }

        public void setTitle (String title){
            TextView post_name = (TextView) globalView.findViewById(R.id.photo_name);
            post_name.setText(title);
        }
        public void setDesc (String desc){
            TextView post_desc = (TextView) globalView.findViewById(R.id.photo_desc);
            post_desc.setText(desc);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.addPhotoActivity) {
            Intent intent = new Intent(PhotoActivity.this, AddNewPhotoActivity.class);
            startActivity(intent);
        }
        return true;
    }

}
