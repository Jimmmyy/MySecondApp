package com.example.jimmy.mysecondapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class Adapter extends ArrayAdapter<DataItem> {

    Context context;
    int layoutResourceId;
    List<DataItem> data=null;

    public Adapter(Context context, int resource, List<DataItem> objects){
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }

    static class DataHolder {
        ImageView background;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DataHolder holder;

        if(convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent,false);

            holder = new DataHolder();
            holder.background = convertView.findViewById(R.id.image_fond);
            convertView.setTag(holder);

        }
        else{
            holder = (DataHolder)convertView.getTag();
        }

        DataItem dataItem = data.get(position);
        holder.background.setImageResource(dataItem.IdImage);

        return convertView;
    }
}
