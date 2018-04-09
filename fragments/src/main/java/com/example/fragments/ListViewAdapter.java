package com.example.fragments;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by htw on 19.02.2018.
 */

public class ListViewAdapter extends ArrayAdapter<String>
{
        private final Activity context;
        private final String []nameList;
        private final Integer []imageList;

    public ListViewAdapter(Activity context, String[] nameList, Integer[] imageList) {
        super(context,R.layout.list_view_element,nameList);
        this.context = context;
        this.nameList = nameList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rootView = layoutInflater.inflate(R.layout.list_view_element,null, true);
        TextView textView = rootView.findViewById(R.id.listViewText);
        ImageView imageView = rootView.findViewById(R.id.listViewImage);

        textView.setText(nameList[position]);
        imageView.setImageResource(imageList[position]);




        return rootView;
    }


}
