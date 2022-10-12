package com.example.assignment_2;
import java.util.ArrayList;

public class UnitManager
{
    ArrayList<Unit> products = new ArrayList<>(0);
    public void createProduct(Unit product) {
        products.add(product);
    }
}