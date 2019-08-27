package com.example.week2weekendceleb.model.datasource.local.database;

public class CelebrityDatabaseContract {
    // TODO fill this up  boyo

    //psfs {enter}
    public static final String DATABASE_NAME = "celebrity_db";
    public static final String TABLE_NAME = "celebrity_table";
    //psfi {enter}
    public static final int DATABASE_VERSION = 1;
    public static final String COL_NAME = "name";
    public static final String COL_INDUSTRY = "industry";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_URL = "url";
    public static final String COL_ID = "id";


    //Create Table Query
    public static final String QUERY_CREATE_TABLE =
            String.format("CREATE TABLE %s(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)",
                    TABLE_NAME, COL_NAME, COL_INDUSTRY, COL_DESCRIPTION, COL_URL, COL_ID);

    //Select All Query
    public static final String QUERY_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);

    //Select by ID
    public static String QUERY_SELECT_BY_ID(String id) {
        return String.format("SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, COL_ID, id);
    }

    public static String QUERY_SELECT_BY_INDUSTRY(String industry) {
        return String.format("SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, COL_INDUSTRY, industry);
    }

    //Drop Table Query
    public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);

    //Get Count
    public static final String COUNT = String.format("SELECT * FROM " + TABLE_NAME);

}
