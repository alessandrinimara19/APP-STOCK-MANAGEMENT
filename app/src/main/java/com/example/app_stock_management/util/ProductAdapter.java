package com.example.app_stock_management.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_stock_management.R;
import com.example.app_stock_management.database.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    private int resource;
    private List<Product> products;
    private LayoutInflater inflater;
    public ProductAdapter(@NonNull Context context, int resource,
                          @NonNull List<Product> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.products = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Product product = products.get(position);
        if (product == null){
            return view;
        }

        addProductName(view, product.getProductName());
        addProductCategory(view, product.getCategory());
        addProductPrice(view, product.getProductPrice());
        addProductQuantity(view, product.getProductQuantity());
        return view;
    }

    private void addProductQuantity(View view, int productQuantity) {
        TextView textView = view.findViewById(R.id.row_tv_quantity);
        populateTextViewContent(textView, String.valueOf(productQuantity));
    }

    private void addProductPrice(View view, double productPrice) {
        TextView textView = view.findViewById(R.id.row_tv_price);
        String value = context.getString(R.string.row_price_template, String.valueOf(productPrice));
        populateTextViewContent(textView, value);
    }

    private void addProductCategory(View view, String productCategory) {
        TextView textView = view.findViewById(R.id.row_tv_category);
        String value = context.getString(R.string.row_category_template, productCategory);
        populateTextViewContent(textView, value);
    }

    private void addProductName(View view, String productName) {
        TextView textView = view.findViewById(R.id.row_tv_name);
        populateTextViewContent(textView, productName);
    }

    private void populateTextViewContent(TextView textView, String value){
        if (value != null && !value.trim().isEmpty()){
            textView.setText(value);
        } else {
            textView.setText("-");
        }
    }
}
