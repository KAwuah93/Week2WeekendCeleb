package com.example.week2weekendceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    CelebrityDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new CelebrityDatabaseHelper(this);

        //Call the data population method
        populateData();
    }


    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.forward):
                Intent forward = new Intent(this, CelebrityCategoryActivity.class);
                startActivity(forward);
                break;
        }
    }

    //Create and inject some data if its empty, leave alone if it isn't
    public void populateData(){
            //todo delete this mess

        CelebrityDatabaseHelper dbhelper = new CelebrityDatabaseHelper(this);
        //to keep scrubbing for testing
        dbhelper.CLEAR();
        //add data
        dbhelper.insertCelebrityIntoDb(new Celebrity("Lil Wayne","Music","Best Rapper alive", "lol"));
        dbhelper.insertCelebrityIntoDb(new Celebrity("Jon Mayer","Music","Ohhooho GRAVITYyy", "Link"));
        dbhelper.insertCelebrityIntoDb(new Celebrity("Flume","Music","Take a chance, take my handdd won't youuu", "lol"));
        dbhelper.insertCelebrityIntoDb(new Celebrity("Logic","Music","Most Alright Rapper alive", "lol"));
        dbhelper.insertCelebrityIntoDb(new Celebrity("Drake","Music","Softest alive", "lol"));

        dbhelper.insertCelebrityIntoDb(new Celebrity("Leonardo DiCaprio","Actor","Bear man", "lol"));
        dbhelper.insertCelebrityIntoDb(new Celebrity("Scarlett Johansson","Actor","Black Widow", "lol"));

        dbhelper.insertCelebrityIntoDb(new Celebrity("Jake Paul","Influencer","It's every day bro", "lol"));
        dbhelper.insertCelebrityIntoDb(new Celebrity("PewDiePie","Influencer","Brofist", "lol"));


    }
}
