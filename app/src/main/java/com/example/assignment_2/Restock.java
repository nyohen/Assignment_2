package com.example.assignment_2;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Restock extends AppCompatActivity implements View.OnClickListener
{
    UnitManager productManager;
    Button update;
    Button cancel;
    EditText qtyNumber;
    TextView text;
    ListView products;
    AdapterActivity productAdapter;

    int selected = -1;
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        update = findViewById(R.id.updateQty);
        cancel = findViewById(R.id.updateCancel);
        qtyNumber = findViewById(R.id.input_qty);
        text = findViewById(R.id.prodType);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar)
                .setDisplayHomeAsUpEnabled(true);

        products = findViewById(R.id.productList);
        productManager = ((MyApp)getApplication()).controlItem;

        productAdapter = new AdapterActivity(productManager.products, this);
        products.setAdapter(productAdapter);

        products.setOnItemClickListener((adapterView, view, i, l) -> {
            selected = i;
            text.setText(productManager.products.get(selected).name);
        });
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
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
    public void isNumber()
    {
        int start = 0;
        try {
            int obj = Integer.parseInt(qtyNumber.getText().toString());
            if(obj >= start)
            {
                qty = obj;
                Log.d("Stock", " QTY: " + qty);
            }
        } catch (Exception error){
            CharSequence text = "All fields required";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.updateQty:
                isNumber();

                if(selected >= 0)
                {
                    productManager.products.get(selected).quantity+=qty;
                    productAdapter.notifyDataSetChanged();
                    qty = 0;
                } else {
                    CharSequence text = "Please select a product";
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                } break;
            case R.id.updateCancel:
                this.finish();
                break;
        }
    }
}