package com.example.apptiendad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apptiendad.R;
import com.example.apptiendad.model.cuenta;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {

    private final Context context;
    private cuenta model;
    private ArrayList<cuenta> list;

    public ContactoAdapter(Context context, ArrayList<cuenta> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item_contacto, viewGroup, false);
        }
        TextView tv_iten_contacto = itemView.findViewById(R.id.tv_iten_contacto);
        model = list.get(i);
        tv_iten_contacto.setText(model.getNombre());

        return itemView;
    }
}
