package com.example.week2weekendceleb.model.datasource.local.Contentprovider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CelebrityProviderContract {
    //provides URI information
    //we first need the content authority - unique identifier to know what provider we are referencing
    public static final String CONTENT_AUTHORITY = "com.example.week2weekendceleb.model.datasource.local.Contentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CELEBRITY ="celebrity";

    public static final class CelebrityEntry implements BaseColumns {
        public static final Uri Celebrity_CONTENT_URI = CONTENT_URI.buildUpon().appendPath("celebrity").build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir"+Celebrity_CONTENT_URI+"/celebrity";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item"+Celebrity_CONTENT_URI+"/celebrity";

        public static Uri buildCelebrityUri(Long id){
            return ContentUris.withAppendedId(Celebrity_CONTENT_URI, id);
        }
    }
}
