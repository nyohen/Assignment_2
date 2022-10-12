package com.example.assignment_2;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class HistoryActivity extends AppCompatActivity
{
    RecyclerView historyRec;
    History controlHistory_;
    HistoryAdapter adapterManager;

    private HistoryAdapter.RecycleViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setOnClickListener();

        historyRec = findViewById(R.id.historyItemList);
        controlHistory_ = ((MyApp)getApplication()).controlHistory;
        adapterManager = new HistoryAdapter(controlHistory_.operationHistory,
                this, listener);

        historyRec.setAdapter(adapterManager);
        historyRec.setLayoutManager(new LinearLayoutManager(this));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    private void setOnClickListener()
    {
        listener = (view, position) -> {
            Intent intent = new Intent(getApplicationContext(), HistoryDetail.class);
            intent.putExtra("Product", controlHistory_.operationHistory
                    .get(position).obj_name);
            intent.putExtra("Price",
                    String.valueOf(controlHistory_.operationHistory
                            .get(position).price));
            intent.putExtra("Date",
                    String.valueOf(controlHistory_.operationHistory
                            .get(position).date));
            startActivity(intent);
        };
    }
}