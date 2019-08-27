package com.example.week2weekendceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class newCelebrityAdd extends AppCompatActivity {
    EditText CelebName;
    Spinner CelebIndustry;
    EditText CelebDescription;
    EditText CelebUrl;
    CelebrityDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_celebrity_add);

        //Binding the Views
        CelebName = findViewById(R.id.nCelebName);
        CelebIndustry = findViewById(R.id.nIndustrySpinner);
        CelebDescription = findViewById(R.id.nDescription);
        CelebUrl = findViewById(R.id.nUrl);

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

    public void onClick(View view) {

        Log.d("EDIT ONCLICK", "onClick: ");
        //check if there is at least an input all of
        String nName = CelebName.getText().toString();
        String nIndustry = CelebIndustry.getSelectedItem().toString();
        String nDescription = CelebDescription.getText().toString();
        String nUrl = CelebUrl.getText().toString();

        if(isEmpty(nName) || isEmpty(nDescription) || isEmpty(nUrl)){
            Toast.makeText(this, "Please Fill in all fields!", Toast.LENGTH_SHORT).show();
        }else{
            //String array that contains all the cols of the table (defined in Database something)

            dbhelper.insertCelebrityIntoDb(new Celebrity(nName,nIndustry,nDescription, nUrl));
            finish();
        }
    }

    private boolean isEmpty(String myeditText) {
        return myeditText.trim().length() == 0;
    }
}
