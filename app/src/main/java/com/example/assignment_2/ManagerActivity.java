package com.example.assignment_2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener
{
    Button historyBtn;
    Button restockBtn;
    int stance;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        historyBtn = findViewById(R.id.historyBtn);
        restockBtn = findViewById(R.id.restockBtn);
        historyBtn.setOnClickListener(this);
        restockBtn.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar)
                .setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view)
    {
        stance = view.getId();
        switch (stance){
            case R.id.historyBtn:
                Intent historySet = new Intent(this, HistoryActivity.class);
                startActivity(historySet);
                break;
            case R.id.restockBtn:
                Intent restockSet = new Intent(this, Restock.class);
                startActivity(restockSet);
                break;
        }
    }
}