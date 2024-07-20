package com.example.tictacktoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class CourseModel {
    private int color;
    private int imageResId;

    public CourseModel(int color, int imageResId) {
        this.color = color;
        this.imageResId = imageResId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
