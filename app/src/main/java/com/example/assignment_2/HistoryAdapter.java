package com.example.assignment_2;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>
{
    String interface_position = "                                ";
    private final RecycleViewClickListener viewListener;
    ArrayList<History.Operation> buyingList;
    Context context;

    public HistoryAdapter(ArrayList<History.Operation> transactions,
                          Context context, RecycleViewClickListener listener)
    {
        this.buyingList = transactions;
        this.context = context;
        this.viewListener = listener;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent,
                false);
        return new HistoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position)
    {
        holder.productName.setText(buyingList.get(position).obj_name + interface_position
                + buyingList.get(position).price + interface_position
                + buyingList.get(position).obj_quantity);
    }

    @Override
    public int getItemCount() {
        return buyingList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        TextView productName;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.history_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {viewListener.onClick(view, getAdapterPosition());}
    }
    public interface RecycleViewClickListener { void onClick(View view, int position); }
}
