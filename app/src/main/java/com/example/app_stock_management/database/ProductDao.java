package com.example.app_stock_management.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    long insert(Product product);

    @Query("select * from products")
    List<Product> getAll();

}
