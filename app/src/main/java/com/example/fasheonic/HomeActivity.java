package com.example.fasheonic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.fasheonic.ui.Search.SearchFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;


import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialze

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();



//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        updateNavHeader();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.+
        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_home, R.id.nav_uploadorder, R.id.nav_myorder,
                R.id.nav_mywishlist, R.id.nav_chat, R.id.nav_history, R.id.nav_settings, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_search_icon:

                //Toast.makeText(HomeActivity.this, "You selected search", Toast.LENGTH_SHORT).show();
              //  SearchFragment searchFragment=new SearchFragment();
              //  FragmentManager fragmentManager=getSupportFragmentManager();
                //pink toolbar Hide
           //     getSupportActionBar().hide();
             //   fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,searchFragment).commit();


                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

                return true;
            case R.id.main_notification_icon:

                Toast.makeText(HomeActivity.this, "You selected notification", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.main_cart_icon:

                Toast.makeText(HomeActivity.this, "You selected cart", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }












    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            dialogbox();
        }

    }

    public void dialogbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
    }



    public void updateNavHeader(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        TextView navUsername=headerView.findViewById(R.id.user_prof_name);
        TextView navEmail=headerView.findViewById(R.id.user_prof_email);

        navUsername.setText(currentUser.getDisplayName());
        navEmail.setText(currentUser.getEmail());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id  = menuItem.getItemId();

        if (id == R.id.nav_history) {
            startActivity(new Intent(this,LogSign.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
