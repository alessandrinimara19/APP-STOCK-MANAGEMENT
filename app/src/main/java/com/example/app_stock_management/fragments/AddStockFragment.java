package com.example.app_stock_management.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.app_stock_management.Product;
import com.example.app_stock_management.R;

public class AddStockFragment extends Fragment {

    private Spinner spnCategory;

    public AddStockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_stock, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        spnCategory = view.findViewById(R.id.add_stock_spn_category);
        if(getContext() != null){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(getContext().getApplicationContext(),
                    R.array.add_category_values,
                            android.R.layout.simple_spinner_dropdown_item);
            spnCategory.setAdapter(adapter);
        }
    }
}