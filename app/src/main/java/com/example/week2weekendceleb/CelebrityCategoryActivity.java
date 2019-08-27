package com.example.week2weekendceleb;

import android.content.Intent;
import android.os.Bundle;

import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class CelebrityCategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //The drawer class for the menu as well as the Bundle we use to pass the testing data
    private DrawerLayout drawer;
    private Bundle bundle;
    private CelebrityDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_celebrity_category);
        bundle = new Bundle();
        dbhelper = new CelebrityDatabaseHelper(this);

        //binding the tool bar
        Toolbar toolbar = findViewById(R.id.categoryToolbar);
        setSupportActionBar(toolbar);

        //binding the DrawerLayout so we can start creating the onclick methods
        //Also creating reference to the NavigationView so we can do the same
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //creating the button to deploy the side menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //creating the normal 'mode' for the function so we don't open a null page
        //However just like this it will always default back to musicians. unless we add this if
        if(savedInstanceState == null) {
            MusicianFragment musicianFragment = new MusicianFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,musicianFragment).commit();
            navigationView.setCheckedItem(R.id.nav_musician);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Binding each button in the Navigation with a function
        switch(item.getItemId()){
            case R.id.nav_musician:
                Log.d("TEST", "Musician has been touched");
                MusicianFragment musicianFragment = new MusicianFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,musicianFragment).commit();
                break;
            case R.id.nav_actor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new ActorFragment()).commit();
                break;
            case R.id.nav_influencer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new InfluencerFragment()).commit();
                break;
            case R.id.nav_favorite:
                //replace with detail view and checking statement to see if we even have a 'favorite' saved.
                Toast.makeText(this, "Show Favorites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_celeb:
                Intent createCeleb = new Intent(this, newCelebrityAdd.class);
                startActivityForResult(createCeleb,1);

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    // todo fit the 'favorite' function into the views

    public ArrayList<Celebrity> getData(){
        ArrayList<Celebrity> returnList = new ArrayList<>();

        //Populating the dummy data
        //TODO replace with actual SQL pulls for the initial data we are binding.
        returnList.add(new Celebrity("Lil Wayne","Musician","Best Rapper alive", "lol"));
        returnList.add(new Celebrity("Jon Mayer","Musician","Ohhooho GRAVITYyy", "Link"));
        returnList.add(new Celebrity("Flume","Musician","Take a chance, take my handdd won't youuu", "lol"));
        returnList.add(new Celebrity("Logic","Musician","Most Alright Rapper alive", "lol"));
        returnList.add(new Celebrity("Lil Wayne","Musician","Best Rapper alive", "lol"));

        return returnList;
    }
    //TODO create floating button that recieves the click event so we can do the 'create' function
    //TODO create the swipe delete function with the confirmation
}
