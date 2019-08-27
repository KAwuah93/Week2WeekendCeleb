package com.example.week2weekendceleb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CelebrityRVAdapter extends RecyclerView.Adapter<CelebrityRVAdapter.ViewHolder>{
    private ArrayList<Celebrity> celebArrayList;

    //constructor for the class
    public CelebrityRVAdapter(ArrayList<Celebrity> celebArrayList) {
        this.celebArrayList = celebArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate a view into memory, pul context from the parent and attach it to the parent view
        //set attach to root to false
        View inflatedItem  = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.celebrity_list_item, parent, false);
        //Return the view within the Viewholder
        return new ViewHolder(inflatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Celebrity currentCelebrity = celebArrayList.get(position);
        //how you can do it by using a prewritten method
        holder.populateValues(currentCelebrity);
    }

    @Override
    public int getItemCount() {
        return celebArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleText;
        Celebrity selectCelebrity;

        public ViewHolder(View itemView){
            super(itemView);

            //Bind using the the ItemView instead of directly
            titleText = itemView.findViewById(R.id.celeb_item_title);
            itemView.setOnClickListener(this);
        }
        public void setItemCar(Celebrity celebrity) {
            this.selectCelebrity = celebrity;
        }

        public void populateValues(Celebrity celebrity){
            titleText.setText(celebrity.getName());
            setItemCar(celebrity);
        }
        @Override
        public void onClick(View view){
            // TODO replace this once you have a Details class
             Intent detailsIntent = new Intent(view.getContext(), DetailActivity.class);
             Bundle bundle = new Bundle();
             bundle.putParcelable("celebrity", selectCelebrity);
             detailsIntent.putExtras(bundle);

             view.getContext().startActivity(detailsIntent);
        }
    }
}
