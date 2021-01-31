package com.example.oblig1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Cat> {
    private ArrayList<Cat> cats;
    Context context;

    public CustomAdapter(ArrayList<Cat> cats, Context context) {
        super(context, R.layout.list_item, cats);
        this.cats = cats;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cats.size();
    }

    @Override
    public Cat getItem(int position) {
        return cats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView tv;
        ImageView img;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Cat cat = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageViewCats);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.tv.setText(cat.getNavn());
        viewHolder.img.setImageBitmap(cat.getBilde());

        // Return the completed view to render on screen
        return convertView;
    }

}
