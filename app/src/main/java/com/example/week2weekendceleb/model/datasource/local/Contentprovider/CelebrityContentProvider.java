package com.example.week2weekendceleb.model.datasource.local.Contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.week2weekendceleb.CelebrityDatabase;
import com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseHelper;

import static com.example.week2weekendceleb.model.datasource.local.Contentprovider.CelebrityProviderContract.CONTENT_AUTHORITY;
import static com.example.week2weekendceleb.model.datasource.local.Contentprovider.CelebrityProviderContract.PATH_CELEBRITY;
import static com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseContract.COL_ID;
import static com.example.week2weekendceleb.model.datasource.local.database.CelebrityDatabaseContract.TABLE_NAME;

public class CelebrityContentProvider extends ContentProvider {

    public static final int CELEBRITY = 100;
    public static final int CELEBRITY_ID = 101;
    private CelebrityDatabaseHelper celebrityDatabaseHelper;
    private UriMatcher uriMatcher = buildUriMatcher();


    @Override
    public boolean onCreate() {
        celebrityDatabaseHelper = new CelebrityDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getReadableDatabase();
        Cursor returnCursor= null;
        switch(uriMatcher.match(uri)){
            case CELEBRITY:
                returnCursor = sqLiteDatabase.query(
                        TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sort);
                break;
            case CELEBRITY_ID:
                returnCursor = sqLiteDatabase.query(
                        TABLE_NAME,
                        projection,
                        CelebrityProviderContract.CelebrityEntry._ID + " = ?",
                        new String []{
                                String.valueOf(ContentUris.parseId(uri))
                        },
                        null,
                        null,
                        sort);
                break;
        }

        return returnCursor;
    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case CELEBRITY:
                return CelebrityProviderContract.CelebrityEntry.CONTENT_TYPE;
            case CELEBRITY_ID:
                return CelebrityProviderContract.CelebrityEntry.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getWritableDatabase();
        Long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Uri returnUri = CelebrityProviderContract.CelebrityEntry.buildCelebrityUri(id);

        getContext().getContentResolver().notifyChange(returnUri,null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getWritableDatabase();
        int id = sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = ?", strings);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getWritableDatabase();
        int id = sqLiteDatabase.update(TABLE_NAME, contentValues, COL_ID + " = ?", strings);
        return id;
    }
    //type and crud operations handler

    public UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CELEBRITY, CELEBRITY);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CELEBRITY + "/#", CELEBRITY_ID);
        return uriMatcher;
    }
}
