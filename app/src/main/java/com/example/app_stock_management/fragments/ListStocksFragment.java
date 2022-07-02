package com.example.app_stock_management.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_stock_management.database.Product;
import com.example.app_stock_management.R;
import com.example.app_stock_management.database.ProductService;
import com.example.app_stock_management.network.Callback;

import java.util.ArrayList;
import java.util.List;

public class ListStocksFragment extends Fragment {

    private static final String PRODUCT_KEY = "PRODUCT_KEY";
    private ArrayList<Product> products;
    private ListView lvProducts;
//    ProductService productService;

    public ListStocksFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListStocksFragment newInstance(ArrayList<Product> products) {
        ListStocksFragment fragment = new ListStocksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("PRODUCT_KEY", products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            products = getArguments().getParcelableArrayList(PRODUCT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_stocks, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        lvProducts = view.findViewById(R.id.list_stocks_lv);

        if(getContext() != null){
            ArrayAdapter<Product> adapter = new ArrayAdapter<>(getContext().getApplicationContext(),
                    android.R.layout.simple_list_item_1, products);
            lvProducts.setAdapter(adapter);
        }

    }
}