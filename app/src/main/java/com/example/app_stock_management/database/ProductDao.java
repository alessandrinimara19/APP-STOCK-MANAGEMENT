package com.example.app_stock_management.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ProductDao {
    @Insert
    long insert(Product product);
}
