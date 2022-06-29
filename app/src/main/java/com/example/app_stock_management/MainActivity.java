package com.example.app_stock_management;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
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
                    Log.i("MainAct", "lv selected");
                } else if (item.getItemId() == R.id.main_nav_add_stock){
                    Log.i("MainAct", "add selected");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void configNavigation(){
        //init + link DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        //inuit Toolbar
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