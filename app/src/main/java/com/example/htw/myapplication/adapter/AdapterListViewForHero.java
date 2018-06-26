package com.example.htw.myapplication.adapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.htw.myapplication.Model.ModelHero;
import com.example.htw.myapplication.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class AdapterListViewForHero extends ArrayAdapter <ModelHero> {

    Context context;
    int resource;
    List<ModelHero> heroList;

    public AdapterListViewForHero(@NonNull Context context, int resource, List<ModelHero> heroList) {
        super(context, resource, heroList);

        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listview_hero_row_element,null);

        view.findViewById(R.id.deleteButtonHero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeHeroMethod(position);
            }
        });

        TextView textViewName = (TextView) view.findViewById(R.id.heroName);
        TextView textViewRealName = (TextView) view.findViewById(R.id.heroRealName);
        TextView textViewDesc = (TextView) view.findViewById(R.id.heroDesc);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewHero);

        ModelHero HerolistWithPossition = heroList.get(position);

        textViewName.setText(HerolistWithPossition.getName());
        textViewRealName.setText(HerolistWithPossition.getRealname());
        textViewDesc.setText(HerolistWithPossition.getBio());

        //Glide.with(context).load(heroList.get(position).getImageurl()).into(imageView);
        Picasso.get().load(heroList.get(position).getImageurl()).into(imageView);
        // test picasso
        //Picasso.with(getContext()).load(heroList.get(position).getImageurl()).into(imageView);

        return view;
    }


    private void removeHeroMethod (int position)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure to delete ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                heroList.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "canceled",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();




    }


}
