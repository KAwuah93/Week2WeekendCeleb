package com.example.week2weekendceleb;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;

import java.util.ArrayList;

public class ActorFragment extends Fragment {

    CelebrityDatabaseHelper dbhelper;
    View root;
    ArrayList<Celebrity> passedList;
    RecyclerView recyclerView;
    LinearLayoutManager lim;
    CelebrityRVAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //Create the Root of it all
        Log.d("TEST", "made it into the inflator for the view");
        root = inflater.inflate(R.layout.fragment_actor, container, false);
        //TODO fix the data call here
        inflateView();

        return root;

    }



    public ArrayList<Celebrity> getData(){
        ArrayList<Celebrity> returnList = new ArrayList<>();
        //TODO replace with actual SQL pulls for the initial data we are binding.
        returnList = dbhelper.getByIndustry("Actor");
        return returnList;
    }

    public void inflateView(){
        dbhelper = new CelebrityDatabaseHelper(getActivity().getApplicationContext());
        passedList = getData();

        recyclerView = root.findViewById(R.id.rvCelebrity);
        lim = new LinearLayoutManager(getActivity());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lim);

        myAdapter = new CelebrityRVAdapter(passedList);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.d("TEST", "sending back view");

    }
}
