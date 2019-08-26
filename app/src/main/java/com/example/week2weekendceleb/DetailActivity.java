package com.example.week2weekendceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    //
    TextView detailTitle;
    TextView detailIndustry;
    TextView detailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent passedIntent = getIntent();
        Bundle bundle = passedIntent.getExtras();
        Celebrity passedCelebrity = bundle.getParcelable("celebrity");

        detailIndustry = findViewById(R.id.detailIndustry);
        detailTitle = findViewById(R.id.detailTitle);
        detailDescription = findViewById(R.id.detailBody);

        detailTitle.setText(passedCelebrity.getName());
        detailIndustry.setText(passedCelebrity.getIndustry());
        detailDescription.setText(passedCelebrity.getDescription());


    }
}
