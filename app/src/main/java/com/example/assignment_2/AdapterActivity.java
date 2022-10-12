package com.example.assignment_2;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterActivity extends BaseAdapter
{
    ArrayList<Unit> list;
    Context context;

    public AdapterActivity(ArrayList<Unit> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int obj) {
        return list.get(obj);
    }

    @Override
    public long getItemId(int obj) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int obj, View view, ViewGroup viewGroup)
    {
        view = LayoutInflater.from(context).inflate(R.layout.layout_item,null);
        TextView itemName = view.findViewById(R.id.productName);
        TextView itemquantity = view.findViewById(R.id.quantityNum);
        TextView itemPrice = view.findViewById(R.id.costNum);
        itemName.setText(String.valueOf(list.get(obj).name));
        itemquantity.setText(String.valueOf(list.get(obj).quantity));
        itemPrice.setText(String.valueOf(list.get(obj).price));
        return view;
    }
}

