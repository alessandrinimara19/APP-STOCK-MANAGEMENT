package com.example.app_stock_management.network;

public interface Callback<R> {
    void runResultOnUIThread(R result);
}
