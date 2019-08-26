package com.example.week2weekendceleb.model.datasource.local.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.week2weekendceleb.Celebrity;

import java.util.ArrayList;

public class CelebrityDatabaseHelper extends SQLiteOpenHelper {

    public CelebrityDatabaseHelper(Context context){
        super(context, CelebrityDatabaseContract.DATABASE_NAME,
                null,CelebrityDatabaseContract.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CelebrityDatabaseContract.QUERY_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CelebrityDatabaseContract.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
    //TODO create a few custom Queries
    //Select all Query

    public void CLEAR(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CelebrityDatabaseContract.DROP_TABLE);
        db.execSQL(CelebrityDatabaseContract.QUERY_CREATE_TABLE);
    }
    public ArrayList<Celebrity> getAllCelebs(){
        //Create a holder array for the function
        ArrayList<Celebrity> returnArray = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //use a cursor to move through and find the data that we need
        Cursor cursor = readableDatabase.rawQuery(CelebrityDatabaseContract.QUERY_SELECT_ALL, null);

        if(cursor.moveToFirst()) {
            do {
                Celebrity currentCelebrity = new Celebrity();
                currentCelebrity.setName(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_NAME)));
                currentCelebrity.setIndustry(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_INDUSTRY)));
                currentCelebrity.setDescription(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_DESCRIPTION)));
                currentCelebrity.setRelevantUrl(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_URL)));
                currentCelebrity.set_id(cursor.getInt(cursor.getColumnIndex(CelebrityDatabaseContract.COL_ID)));
                returnArray.add(currentCelebrity);
            } while(cursor.moveToNext());
        }

        return returnArray;
    }

    public ArrayList<Celebrity> getByIndustry(String industry){
        //Create a holder array for the function
        ArrayList<Celebrity> returnArray = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //use a cursor to move through and find the data that we need
        Cursor cursor = readableDatabase.rawQuery(CelebrityDatabaseContract.QUERY_SELECT_BY_INDUSTRY(industry), null);

        if(cursor.moveToFirst()) {
            do {
                Celebrity currentCelebrity = new Celebrity();
                currentCelebrity.setName(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_NAME)));
                currentCelebrity.setIndustry(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_INDUSTRY)));
                currentCelebrity.setDescription(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_DESCRIPTION)));
                currentCelebrity.setRelevantUrl(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_URL)));
                currentCelebrity.set_id(cursor.getInt(cursor.getColumnIndex(CelebrityDatabaseContract.COL_ID)));
                returnArray.add(currentCelebrity);
            } while(cursor.moveToNext());
        }

        return returnArray;
    }

    public Celebrity getCelebrityById(int id){
        //Create a holder array for the function
        Celebrity currentCelebrity = new Celebrity();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //use a cursor to move through and find the data that we need
        Cursor cursor = readableDatabase.rawQuery(CelebrityDatabaseContract.QUERY_SELECT_ALL, null);

        if(cursor.moveToFirst()) {
                currentCelebrity.setName(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_NAME)));
                currentCelebrity.setIndustry(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_INDUSTRY)));
                currentCelebrity.setDescription(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_DESCRIPTION)));
                currentCelebrity.setRelevantUrl(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_URL)));
                currentCelebrity.set_id(cursor.getInt(cursor.getColumnIndex(CelebrityDatabaseContract.COL_ID)));
        }

        return currentCelebrity;
    }
    //Set up the insert statement
    public void insertCelebrityIntoDb(Celebrity celebrityToSave) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //Container which is key value pairs, key being the Col of DB, value being
        // what to save in that place
        ContentValues contentValues = new ContentValues();
        //Put values in contentValues
        contentValues.put(CelebrityDatabaseContract.COL_NAME, celebrityToSave.getName());
        contentValues.put(CelebrityDatabaseContract.COL_DESCRIPTION, celebrityToSave.getDescription());
        contentValues.put(CelebrityDatabaseContract.COL_INDUSTRY, celebrityToSave.getIndustry());
        contentValues.put(CelebrityDatabaseContract.COL_URL, celebrityToSave.getRelevantUrl());

        writableDatabase.insert(CelebrityDatabaseContract.TABLE_NAME, null, contentValues);
    }

    public void updateCelebrityInDb(String id, Celebrity celebrityToSave) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //Container which is key value pairs, key being the Col of DB, value being
        // what to save in that place
        ContentValues contentValues = new ContentValues();
        //Put values in contentValues
        contentValues.put(CelebrityDatabaseContract.COL_NAME, celebrityToSave.getName());
        contentValues.put(CelebrityDatabaseContract.COL_DESCRIPTION, celebrityToSave.getDescription());
        contentValues.put(CelebrityDatabaseContract.COL_INDUSTRY, celebrityToSave.getIndustry());
        contentValues.put(CelebrityDatabaseContract.COL_URL, celebrityToSave.getRelevantUrl());
        //TODO check if this is wonky or not
        writableDatabase.update(CelebrityDatabaseContract.TABLE_NAME, contentValues, "ID = ?", new String[]{id});
    }

    public void deleteCelebrityInDb(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CelebrityDatabaseContract.TABLE_NAME, "ID = ?", new String[]{id});
    }




}
