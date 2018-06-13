package com.example.htw.myapplication.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.htw.myapplication.Model.PhotoModel;
import com.example.htw.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    FirebaseRecyclerAdapter  firebaseRecyclerAdapter;
    private RecyclerView recyclerList;
    DatabaseReference databaseReference;
    Query query;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo);
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // odniesienie do naszego pierwotnego url (linku)
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");
        query = FirebaseDatabase.getInstance().getReference().child("Blog").limitToLast(50);


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

        FirebaseRecyclerOptions<PhotoModel> options =
                new FirebaseRecyclerOptions.Builder<PhotoModel>()
                        .setQuery(query, PhotoModel.class)
                        .build();

        FirebaseRecyclerAdapter  firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PhotoModel, PhotoViewHolder>(options) {

            @NonNull
            @Override
            public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_single_photo_row, parent, false);

                return new PhotoViewHolder (view) ;
            }

            @Override
            protected void onBindViewHolder(@NonNull PhotoViewHolder holder, int position, @NonNull PhotoModel model) {

                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(model.getImage());
            }

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

        View globalView;
        public PhotoViewHolder(View itemView) {
            super(itemView);

            globalView = itemView;
        }

        public void setTitle (String title){
            TextView post_name = (TextView) globalView.findViewById(R.id.photo_name);
            post_name.setText(title);
        }
        public void setDesc (String desc){
            TextView post_name = (TextView) globalView.findViewById(R.id.photo_name);
            post_name.setText(desc);
        }

        public void setImage (String image){
            ImageView imageView = globalView.findViewById(R.id.image);
            Picasso.get().load(image).into(imageView);

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
