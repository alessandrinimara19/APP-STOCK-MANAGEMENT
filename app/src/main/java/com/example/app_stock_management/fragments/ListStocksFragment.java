package com.example.app_stock_management.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_stock_management.database.Product;
import com.example.app_stock_management.R;
import com.example.app_stock_management.util.ProductAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class ListStocksFragment extends Fragment {

    private static final String PRODUCT_KEY = "PRODUCT_KEY";
    private ArrayList<Product> products;
    private ListView lvProducts;

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

    public void notifyAdapter(){
        ArrayAdapter<Product> adapter = (ArrayAdapter<Product>) lvProducts.getAdapter();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("lifeCycle", "onCreate()");
        if (getArguments() != null) {
            products = getArguments().getParcelableArrayList(PRODUCT_KEY);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Log.i("lifeCycle", "onCreateView()");
        View view =  inflater.inflate(R.layout.fragment_list_stocks, container, false);
        initComponents(view);
        notifyAdapter();
        return view;
    }

    private void initComponents(View view) {
        lvProducts = view.findViewById(R.id.list_stocks_lv);

        if(getContext() != null){
//            ArrayAdapter<Product> adapter = new ArrayAdapter<>(getContext().getApplicationContext(),
//                    android.R.layout.simple_list_item_1, products);
            ProductAdapter adapter = new ProductAdapter(getContext().getApplicationContext(),
                    R.layout.lv_row_view, products, getLayoutInflater());
            lvProducts.setAdapter(adapter);
        }

    }
}