package com.example.assignment_2;
import android.util.Log;
import java.util.ArrayList;

public class History
{
    ArrayList<Operation> operationHistory = new ArrayList<>(0);

    static class Operation
    {
        String obj_name;
        int obj_quantity;
        double price;

        java.util.Date date;
        Operation(String title, int qty, double p)
        {
            obj_name = title;
            obj_quantity = qty;
            price = p;
            date = new java.util.Date();
        }
    }
    public void addItem(String id, int qty, double total)
    {
        Operation newOperation = new Operation(id, qty, total);
        operationHistory.add(newOperation);
        Log.d("History", " Added: " + newOperation.obj_name);
    }
}