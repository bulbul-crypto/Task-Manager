

// This is the file for home page screen

// All the modules are initiated from this file

// It also contains options to update navigation drawer menu. See below

package com.example.try_2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class HomePageND extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_nd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     /*   FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final Button ts_button = findViewById(R.id.button_ts);  // Switch to Task Scheduler Module
        ts_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomePageND.this,TS_Page_Three.class);
                startActivity(intent);
            }
        });

        final Button htp_button = findViewById(R.id.button_htp); // Switch to Weather Update Module
        htp_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomePageND.this,HTP_Page_one.class);
                startActivity(intent);
            }
        });

        final Button wth_button = findViewById(R.id.button_wth);  // Switch to Health Tips Module
        wth_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomePageND.this,WTH_Page_One.class);
                startActivity(intent);
            }
        });

        final Button goals_btn =findViewById(R.id.button_goal);
        goals_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomePageND.this,Goal_Activity_Page_1.class);
                startActivity(intent);
            }
        });

        final Button event_button = findViewById(R.id.button_event);  // Switch to Task Scheduler Module
        event_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomePageND.this,Event_Activity.class);
                startActivity(intent);
                Toast.makeText(HomePageND.this,"Event",Toast.LENGTH_LONG);
            }
        });

        final Button exit_button = findViewById(R.id.button_exit);  // Switch to Task Scheduler Module
        exit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                // Intent intent = new Intent(HomePageND.this,TS_Page_Three.class);
                //startActivity(intent);
                Toast.makeText(HomePageND.this,"Event",Toast.LENGTH_LONG);
                finish();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page_nd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        
        
        //------------------------------------------ Navigation drawer menu options

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(HomePageND.this,HomePage.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
