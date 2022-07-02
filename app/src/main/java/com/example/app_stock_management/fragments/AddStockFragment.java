package com.example.app_stock_management.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_stock_management.R;
import com.example.app_stock_management.database.Product;
import com.example.app_stock_management.database.ProductService;
import com.example.app_stock_management.network.Callback;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddStockFragment extends Fragment {

    TextInputEditText tietName;
    TextInputEditText tietQuantity;
    TextInputEditText tietPrice;
    Button btnSave;
    private Spinner spnCategory;

    private List<Product> products = new ArrayList<>();
    ProductService  productService;

    public AddStockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_stock, container, false);
        initComponents(view);
        productService = new ProductService(getContext().getApplicationContext());
        return view;
    }

    private void initComponents(View view) {
        tietName = view.findViewById(R.id.add_stock_tiet_product_name);
        tietQuantity = view.findViewById(R.id.add_stock_tiet_quantity);
        tietPrice = view.findViewById(R.id.add_stock_tiet_price);
        spnCategory = view.findViewById(R.id.add_stock_spn_category);
        if(getContext() != null){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(getContext().getApplicationContext(),
                    R.array.add_category_values,
                            android.R.layout.simple_spinner_dropdown_item);
            spnCategory.setAdapter(adapter);
        }
        btnSave = view.findViewById(R.id.add_stock_btn_save);
        btnSave.setOnClickListener(getSaveProductClickListner());

    }

    private View.OnClickListener getSaveProductClickListner() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    Product product = buildProductFromComponents();
                    Log.i("addAct", product.toString());
                    productService.insert(product, insertProductCallback());
                }
            }
        };
    }

    private Callback<Product> insertProductCallback() {
        return new Callback<Product>() {
            @Override
            public void runResultOnUIThread(Product product) {
                if (product != null){
                    products.add(product);
                    //notifyAdapter();
                    clearForm();
                    Toast.makeText(getContext().getApplicationContext(), "Product added successfullly!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void clearForm() {
        tietName.setText("");
        tietQuantity.setText("");
        tietPrice.setText("");
        spnCategory.setSelection(0);
    }

    private Product buildProductFromComponents() {
        String name = tietName.getText().toString();
        int quantity = Integer.parseInt(tietQuantity.getText().toString().trim());
        double price = Double.parseDouble(tietPrice.getText().toString().trim());
        String category = spnCategory.getSelectedItem().toString();
        return new Product(name, quantity, price, category);
    }

    private boolean isValid() {
        if (tietName.getText() == null
        || tietName.getText().toString().trim().length() < 3){
            Toast.makeText(getContext().getApplicationContext(),
                    R.string.add_name_error_message,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        if (tietQuantity.getText() == null || tietQuantity.getText().toString().trim().isEmpty()
        || Integer.parseInt(tietQuantity.getText().toString().trim()) > 10000){
            Toast.makeText(getContext().getApplicationContext(),
                    R.string.add_quantity_error_message,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        if (tietPrice.getText() == null || tietPrice.getText().toString().trim().isEmpty()
                || Double.parseDouble(tietPrice.getText().toString().trim()) > 10000){
            Toast.makeText(getContext().getApplicationContext(),
                    R.string.add_price_error_message,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return  true;
    }
}