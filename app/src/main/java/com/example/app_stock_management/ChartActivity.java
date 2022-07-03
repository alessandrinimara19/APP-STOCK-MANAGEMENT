package com.example.app_stock_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.app_stock_management.database.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    public static final String PRODUCT_KEY = "productkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Product> students = getIntent().getParcelableArrayListExtra(PRODUCT_KEY);
        setContentView(new ChartView(getApplicationContext(), getSource(students)));
    }

    private Map<String, Integer> getSource(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, Integer> source = new HashMap<>();
        for (Product product : products) {
            if (source.containsKey(product.getCategory())) {
                Integer currentValue = source.get(product.getCategory());
                source.put(product.getCategory(), currentValue + 1);
            } else {
                source.put(product.getCategory(), 1);
            }
        }
        return source;
    }
}