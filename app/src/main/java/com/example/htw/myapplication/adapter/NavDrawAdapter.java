package com.example.htw.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htw.myapplication.Model.RecyclerNavDrawModel;
import com.example.htw.myapplication.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by htw on 15.01.2018.
 */

public class NavDrawAdapter extends RecyclerView.Adapter<NavDrawAdapter.MyViewHolder> {

    private Context context;
    private  LayoutInflater inflater;
    List <RecyclerNavDrawModel> data = Collections.emptyList();

    public NavDrawAdapter(Context context, List <RecyclerNavDrawModel> data) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }
    public void delete (int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.nav_drawer_custom_row, parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //put DATA

        RecyclerNavDrawModel current = data.get(position);
        holder.textView.setText(current.getTitle());
        holder.image.setImageResource(current.getIconID());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txt_nav_drawer_custom_row);
            image = itemView.findViewById(R.id.image_nav_drawer_custom_row);
            itemView.findViewById(R.id.txt_nav_drawer_custom_row).setOnClickListener(this::clickForshowCounter);
            itemView.findViewById(R.id.image_nav_drawer_delete_item).setOnClickListener(this::clickForDelete);




        }



        private void clickForDelete(View view) {
            delete(getAdapterPosition());

        }

        private void clickForshowCounter(View view){

            Toast.makeText(context, " obiekt na pozycji"+ getAdapterPosition(),Toast.LENGTH_SHORT ).show();
        }
    }


}
