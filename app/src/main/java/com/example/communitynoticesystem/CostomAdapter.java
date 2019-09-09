package com.example.communitynoticesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CostomAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> data;
    private int resource;

    public CostomAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = objects;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String temp = data.get(i);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.timeline_elements, null);
            TextView decri = view.findViewById(R.id.postDiscription);

            decri.setText(temp);
        }
        return view;
    }

}
