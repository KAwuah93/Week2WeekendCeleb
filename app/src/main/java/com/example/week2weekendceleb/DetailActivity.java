package com.example.week2weekendceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;

import static com.example.week2weekendceleb.model.datasource.local.Contentprovider.CelebrityProviderContract.CelebrityEntry.Celebrity_CONTENT_URI;

public class DetailActivity extends AppCompatActivity {

    //
    TextView detailTitle;
    TextView detailIndustry;
    TextView detailDescription;
    Button editBtn;
    Bundle bundle;
    CelebrityDatabaseHelper dbhelper;

    Celebrity passedCelebrity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbhelper = new CelebrityDatabaseHelper(this);

        Intent passedIntent = getIntent();
        Bundle bundle = passedIntent.getExtras();
        passedCelebrity = bundle.getParcelable("celebrity");

        detailIndustry = findViewById(R.id.detailIndustry);
        detailTitle = findViewById(R.id.detailTitle);
        detailDescription = findViewById(R.id.detailBody);
        editBtn = findViewById(R.id.editbtn);

        detailTitle.setText(passedCelebrity.getName());
        detailIndustry.setText(passedCelebrity.getIndustry());
        detailDescription.setText(passedCelebrity.getDescription());

    }

    public void onClick(View view) {

        switch(view.getId()){
            case R.id.editbtn:
                bundle = new Bundle();
                bundle.putParcelable("celeb", passedCelebrity);
                Intent edit = new Intent(this, EditCelebActivity.class);
                edit.putExtras(bundle);
                startActivityForResult(edit,1);

                break;
            case R.id.deleteBtn:
                String pointer = ""+passedCelebrity.get_id();


                Log.d("DELETE", "onClick: worked... " + pointer);

                //uses URI
                //dbhelper.deleteCelebrityInDb(pointer);

                getContentResolver().delete(Celebrity_CONTENT_URI, null, new String[]{pointer});
                finish();

                break;
        }
    }
}
