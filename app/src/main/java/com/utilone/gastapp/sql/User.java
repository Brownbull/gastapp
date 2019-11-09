package com.utilone.gastapp.sql;

import android.database.sqlite.SQLiteDatabase;

public class User {
  // TABLE NAME
  private static final String TABLE_USER = "user";

  // COLUMNS NAMES
  // User Table Columns
  private static final String COLUMN_USER_ID = "user_id";
  private static final String COLUMN_USER_NAME = "user_name";
  private static final String COLUMN_USER_EMAIL = "user_email";
  private static final String COLUMN_USER_PASSWORD = "user_password";

  // COLUMNS DEFINITIONS
  // User Table Columns
  private static final String DEF_COLUMN_USER_ID = "user_id INTEGER PRIMARY KEY AUTOINCREMENT";
  private static final String DEF_COLUMN_USER_NAME = "user_name TEXT";
  private static final String DEF_COLUMN_USER_EMAIL = "user_email TEXT";
  private static final String DEF_COLUMN_USER_PASSWORD = "user_password TEXT";

  // CREATE TABLE
  private  String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
        + DEF_COLUMN_USER_ID + ","
        + DEF_COLUMN_USER_NAME + ","
        + DEF_COLUMN_USER_EMAIL + ","
        + DEF_COLUMN_USER_PASSWORD + ")";

  // DROP TABLES
  private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_USER_TABLE);
  }

  public void onDrop(SQLiteDatabase db){
    db.execSQL(DROP_USER_TABLE);
  }

  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    //Drop User Table if exist
    onDrop(db);
    // Create tables again
    onCreate(db);
  }


}
