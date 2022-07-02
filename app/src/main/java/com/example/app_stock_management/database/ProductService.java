package com.example.app_stock_management.database;

import android.content.Context;

import com.example.app_stock_management.network.AsyncTaskRunner;
import com.example.app_stock_management.network.Callback;

import java.util.concurrent.Callable;

public class ProductService {
    private final ProductDao productDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public ProductService(Context context){
        this.productDao = DatabaseManager.getInstance(context).getProductDao();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(Product product, Callback<Product> activityThread){
        Callable<Product> insertOperation = new Callable<Product>() {
            @Override
            public Product call() {
                if (product == null || product.getId() > 0){
                    return null;
                }
                long id = productDao.insert(product);
                if(id < 0){
                    return null;
                }
                product.setId(id);
                return product;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }
}
