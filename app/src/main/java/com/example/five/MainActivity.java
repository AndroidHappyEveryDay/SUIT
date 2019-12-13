package com.example.five;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.five.ui.star.RecyclerAdapter;
import com.example.five.ui.star.StarViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    private int SCAN_REQUEST_CODE=200;
    private int SELECT_IMAGE_REQUEST_CODE=201;
    protected final int PERMS_REQUEST_CODE = 202;
    EditText e1, e2;
    ImageView m1, m2;
    private ImageView welcomeImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mBottomNavigationView = findViewById(R.id.nav_view);
        mBottomNavigationView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_anli, R.id.navigation_star,R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(mBottomNavigationView, navController);

        String[] permissions=new String[]{Manifest.permission.
                WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(permissions,PERMS_REQUEST_CODE);
        }



    }




}
