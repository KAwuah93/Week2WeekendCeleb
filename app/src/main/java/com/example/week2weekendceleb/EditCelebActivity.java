package com.example.week2weekendceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;

import java.util.ArrayList;

public class EditCelebActivity extends AppCompatActivity {
    EditText CelebName;
    Spinner CelebIndustry;
    EditText CelebDescription;
    EditText CelebUrl;
    CelebrityDatabaseHelper dbhelper;
    Bundle bundle;
    Celebrity passedCeleb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_celeb);

        Intent passedIntent = getIntent();
        bundle = passedIntent.getExtras();
        passedCeleb = bundle.getParcelable("celeb");

        //Binding the Views
        CelebName = findViewById(R.id.eCelebName);
        CelebIndustry = findViewById(R.id.eIndustrySpinner);
        CelebDescription = findViewById(R.id.eDescription);
        CelebUrl = findViewById(R.id.eUrl);

        //ReflectWhatsInTexts
        CelebName.setText(passedCeleb.getName());
        CelebDescription.setText(passedCeleb.getDescription());
        CelebUrl.setText(passedCeleb.getRelevantUrl());

        dbhelper = new CelebrityDatabaseHelper(this);
        //Making ArrayList to feed into the Spinner
        ArrayList<String> spinnerOptions = new ArrayList<>();
        spinnerOptions.add("Music");
        spinnerOptions.add("Actor");
        spinnerOptions.add("Influencer");

        //Binding Options
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerOptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CelebIndustry.setAdapter(arrayAdapter);
    }

    public void editClick(View view) {


        Log.d("EDIT", "editClick: IT HAPPEND WHY NO FUNCION ");
        //check if there is at least an input all of
        String nName = CelebName.getText().toString();
        String nIndustry = CelebIndustry.getSelectedItem().toString();
        String nDescription = CelebDescription.getText().toString();
        String nUrl = CelebUrl.getText().toString();

        if(isEmpty(nName) || isEmpty(nDescription) || isEmpty(nUrl)){
            Toast.makeText(this, "Please Fill in all fields!", Toast.LENGTH_SHORT).show();
        }else{

            dbhelper.updateCelebrityInDb(passedCeleb.get_id()+"",new Celebrity(nName,nIndustry,nDescription, nUrl));
            finish();
        }
    }

    private boolean isEmpty(String myeditText) {
        return myeditText.trim().length() == 0;
    }
}
