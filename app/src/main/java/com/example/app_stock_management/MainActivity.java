package com.example.app_stock_management;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.app_stock_management.fragments.AddStockFragment;
import com.example.app_stock_management.fragments.ListStocksFragment;
import com.example.app_stock_management.util.Product;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private List<Product> products = new ArrayList<>();

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        if (savedInstanceState == null){
            currentFragment = ListStocksFragment.newInstance((ArrayList<Product>) products);
            openFragment();
            navigationView.setCheckedItem(R.id.main_nav_lv_stocks);
        }
    }

    private void initComponents() {
        configNavigation();
        navigationView = findViewById(R.id.nav_view);
        //select option menu
        navigationView.setNavigationItemSelectedListener(getNavigationItemSelectedListner());
    }

    private NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListner() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.main_nav_lv_stocks){
                    currentFragment = ListStocksFragment.newInstance((ArrayList<Product>) products);

                } else if (item.getItemId() == R.id.main_nav_add_stock){
                    currentFragment = new AddStockFragment();
                }
                openFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container, currentFragment)
                .commit();
    }

    private void configNavigation(){
        //init + link DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        //init Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //set toolbar
        setSupportActionBar(toolbar);
        //define toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //link toggle and drawerLayout
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}