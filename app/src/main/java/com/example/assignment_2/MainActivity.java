package com.example.assignment_2;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    int selected;
    double total = 0.0;

    Button btnBuy;
    Button btnManager;
    ListView listView;
    TextView productIdentifier;
    TextView quantityIdentifier;
    TextView totalIdentifier;
    History historyManager;
    UnitManager productManager;
    NumberPicker picker;
    AdapterActivity adapter;

    Unit ItemPicked;
    Unit pants = new Unit("Pants", 10, 20.44);
    Unit shoes = new Unit("Shoes", 100, 10.44);
    Unit hats = new Unit("Hats", 50, 5.6);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuy = findViewById(R.id.buyBtn);
        btnManager = findViewById(R.id.managerBtn);
        picker = findViewById(R.id.numberScrollList);
        picker.setMaxValue(100);
        picker.setMinValue(0);
        picker.setValue(0);

        productManager = ((MyApp)getApplication()).controlItem;
        historyManager = ((MyApp)getApplication()).controlHistory;

        listView = findViewById(R.id.listview);
        productIdentifier = findViewById(R.id.productTxt);
        quantityIdentifier = findViewById(R.id.quantityTxt);
        totalIdentifier = findViewById(R.id.costTxt);

        productManager.createProduct(pants);
        productManager.createProduct(shoes);
        productManager.createProduct(hats);

        picker.setOnValueChangedListener(((numberPicker, i, i1) -> {
            quantityIdentifier.setText(String.valueOf(i1));
            if(productIdentifier.getText().toString().equals("Product Type"))
            {
                CharSequence text = "Item is not selected";
                Toast.makeText(this, text,
                        Toast.LENGTH_SHORT).show();
            } else {
                if(ItemPicked.quantity < i1){
                    CharSequence text = "Not enough products in stock";
                    Toast.makeText(this, text,
                            Toast.LENGTH_SHORT).show();
                }
                total = ItemPicked.price * Double.parseDouble(quantityIdentifier.
                        getText().toString());
                totalIdentifier.setText(String.valueOf(total));
            }
        }));

        adapter = new AdapterActivity(productManager.products,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            adapter.notifyDataSetChanged();
            selected = i;
            ItemPicked = productManager.products.get(selected);
            productIdentifier.setText(ItemPicked.name);
            if(!quantityIdentifier.getText().toString().equals("Quantity")) {
                total = ItemPicked.price * Double.parseDouble(quantityIdentifier
                        .getText().toString());
                totalIdentifier.setText(String.valueOf(Math.round(total)));
            }
        }));
        btnBuy.setOnClickListener(this);
        btnManager.setOnClickListener(this);
    }
    public boolean validate(){
        return !productIdentifier.getText().toString().equals("Product Type")
                && !totalIdentifier.getText().toString().equals("Total")
                && !quantityIdentifier.getText().toString().equals("Quantity");
    }
    public boolean isEnough(){
        return Integer.parseInt(quantityIdentifier.getText().toString()) <= ItemPicked.quantity;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id){
            case R.id.buyBtn:
                if(validate())
                {
                    if(isEnough())
                    {
                        int quantity_ = Integer.parseInt(quantityIdentifier
                                .getText().toString());
                        total = ItemPicked.price * Double.parseDouble(quantityIdentifier
                                .getText().toString());
                        historyManager.addItem(productIdentifier.getText().toString(),
                                quantity_, total);
                        AlertDialog.Builder build = new AlertDialog.Builder(this);
                        build.setTitle("Thank You for purchase");
                        build.setMessage("Your purchase is " + quantity_ + " "
                                + ItemPicked.name
                                + " for " + totalIdentifier.getText().toString());
                        build.show();

                        switch (ItemPicked.name)
                        {
                            case "Pants":
                                productManager.products.get(0).quantity-=quantity_;
                                break;
                            case "Shoes":
                                productManager.products.get(1).quantity-=quantity_;
                                break;
                            case "Hats":
                                productManager.products.get(2).quantity-=quantity_;
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        CharSequence text = "Not enough products in stock";
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    CharSequence text = "All fields are required";
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.managerBtn:
                Intent myIntent = new Intent(this, ManagerActivity.class);
                startActivity(myIntent);
                break;
        }
    }
}