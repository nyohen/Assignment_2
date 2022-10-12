package com.example.assignment_2;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class HistoryDetail extends AppCompatActivity
{
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView textView = findViewById(R.id.buyDetails);
        String title = "The Title has not been set";
        String date = "The date has not been set";
        String price = "The price has not been set";
        Bundle intentExtras = getIntent().getExtras();

        if(intentExtras!= null){
            title = intentExtras.getString("Product");
            date = intentExtras.getString("Date");
            price = intentExtras.getString("Price");
        }

        textView.setText("\nProduct: " + title +
                "\nPrice: $" + price + "\nPurchase Date: " + date);
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar)
                .setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {return true;}
}