package com.example.tictacktoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class CourseGVAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CourseModel> courseModelArrayList;

    public CourseGVAdapter(Context context, ArrayList<CourseModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @Override
    public int getCount() {
        return courseModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        View colorView = convertView.findViewById(R.id.color_view);
        ImageView imageView = convertView.findViewById(R.id.idIVcourse);

        colorView.setBackgroundColor(ContextCompat.getColor(context, courseModelArrayList.get(position).getColor()));
        imageView.setImageResource(courseModelArrayList.get(position).getImageResId());

        return convertView;
    }

}
