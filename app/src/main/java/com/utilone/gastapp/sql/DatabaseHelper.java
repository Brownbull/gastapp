package com.utilone.gastapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.utilone.gastapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

  // DATABASE
  // Database Version
  private static final int DATABASE_VERSION = 1;
  // Database Name
  private static final String DATABASE_NAME = "GastApp11.db";

  // TABLES
  private static final String TABLE_USER = "user";
  private static final String TABLE_MONTH = "month";
  private static final String TABLE_EXPECTED = "expected";
  private static final String TABLE_PERIOD = "period";
  private static final String TABLE_TRANSACT = "transact";
  private static final String TABLE_TYPE = "type";
  private static final String TABLE_CATEGORY = "category";
  private static final String TABLE_NAMEMONTH = "namemonth";

  // COLUMNS NAMES
  // User Table Columns
  private static final String COLUMN_USER_ID = "user_id";
  private static final String COLUMN_USER_NAME = "user_name";
  private static final String COLUMN_USER_EMAIL = "user_email";
  private static final String COLUMN_USER_PASSWORD = "user_password";
  // Month Table Columns
  private static final String COLUMN_MONTH_ID = "month_id";    
  private static final String COLUMN_MONTH_USER_ID = "month_user_id";
  private static final String COLUMN_MONTH_PERIOD_ID = "month_period_id";
  private static final String COLUMN_MONTH_EXPECTED_ID = "month_expected_id";
  private static final String COLUMN_MONTH_BALDIFF = "month_baldiff";
  // Expected Table Columns
  private static final String COLUMN_EXPECTED_ID = "expected_id";    
  private static final String COLUMN_EXPECTED_BALANCE = "expected_balance";
  private static final String COLUMN_EXPECTED_MONTHLY_INCOME = "expected_monthly_income";
  private static final String COLUMN_EXPECTED_MONTHLY_OUTCOME = "expected_monthly_outcome";
  // Period Table Columns
  private static final String COLUMN_PERIOD_ID = "period_id";   
  private static final String COLUMN_PERIOD_BALANCE = "period_balance";   
  private static final String COLUMN_PERIOD_TRANSACTIONS = "period_transactions";   
  // Transact Table Columns
  private static final String COLUMN_TRANSACT_ID = "transact_id"; 
  private static final String COLUMN_TRANSACT_PERIOD_ID = "transact_period_id"; 
  private static final String COLUMN_TRANSACT_TYPE_ID = "transact_type_id"; 
  private static final String COLUMN_TRANSACT_DATE = "transact_date DATETIME"; 
  private static final String COLUMN_TRANSACT_AMOUNT = "transact_amount"; 
  private static final String COLUMN_TRANSACT_CATEGORY_1 = "transact_category_1"; 
  private static final String COLUMN_TRANSACT_CATEGORY_2 = "transact_category_2"; 
  private static final String COLUMN_TRANSACT_CATEGORY_3 = "transact_category_3"; 
  // Type Table Columns
  private static final String COLUMN_TYPE_ID = "type_id"; 
  private static final String COLUMN_TYPE_DESC = "type_desc"; 
  // Category Table Columns
  private static final String COLUMN_CATEGORY_ID = "category_id"; 
  private static final String COLUMN_CATEGORY_DESC = "category_desc";
  // nameMonth Table Columns
  private static final String COLUMN_NAMEMONTH_ID = "namemonth_id"; 
  private static final String COLUMN_NAMEMONTH_DESC = "namemonth_desc";

  // COLUMNS DEFINITIONS
  // User Table Columns
  private static final String DEF_COLUMN_USER_ID = "user_id INTEGER PRIMARY KEY AUTOINCREMENT";
  private static final String DEF_COLUMN_USER_NAME = "user_name TEXT";
  private static final String DEF_COLUMN_USER_EMAIL = "user_email TEXT";
  private static final String DEF_COLUMN_USER_PASSWORD = "user_password TEXT";
  // Month Table Columns
  private static final String DEF_COLUMN_MONTH_ID = "month_id INTEGER PRIMARY KEY AUTOINCREMENT";    
  private static final String DEF_COLUMN_MONTH_USER_ID = "month_user_id INTEGER";
  private static final String DEF_COLUMN_MONTH_PERIOD_ID = "month_period_id INTEGER";
  private static final String DEF_COLUMN_MONTH_EXPECTED_ID = "month_expected_id INTEGER";
  private static final String DEF_COLUMN_MONTH_BALDIFF = "month_baldiff INTEGER";
  // Expected Table Columns
  private static final String DEF_COLUMN_EXPECTED_ID = "expected_id INTEGER PRIMARY KEY AUTOINCREMENT";    
  private static final String DEF_COLUMN_EXPECTED_BALANCE = "expected_balance INTEGER";
  private static final String DEF_COLUMN_EXPECTED_MONTHLY_INCOME = "expected_monthly_income INTEGER";
  private static final String DEF_COLUMN_EXPECTED_MONTHLY_OUTCOME = "expected_monthly_outcome INTEGER";
  // Period Table Columns
  private static final String DEF_COLUMN_PERIOD_ID = "period_id INTEGER PRIMARY KEY AUTOINCREMENT";   
  private static final String DEF_COLUMN_PERIOD_BALANCE = "period_balance INTEGER";   
  private static final String DEF_COLUMN_PERIOD_TRANSACTIONS = "period_transactions INTEGER";   
  // Transact Table Columns
  private static final String DEF_COLUMN_TRANSACT_ID = "transact_id INTEGER PRIMARY KEY AUTOINCREMENT"; 
  private static final String DEF_COLUMN_TRANSACT_PERIOD_ID = "transact_period_id INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_TYPE_ID = "transact_type_id INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_DATE = "transact_date DATETIME"; 
  private static final String DEF_COLUMN_TRANSACT_AMOUNT = "transact_amount INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_CATEGORY_1 = "transact_category_1 INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_CATEGORY_2 = "transact_category_2 INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_CATEGORY_3 = "transact_category_3 INTEGER"; 
  // Type Table Columns
  private static final String DEF_COLUMN_TYPE_ID = "type_id INTEGER PRIMARY KEY AUTOINCREMENT"; 
  private static final String DEF_COLUMN_TYPE_DESC = "type_desc TEXT"; 
  // Category Table Columns
  private static final String DEF_COLUMN_CATEGORY_ID = "category_id INTEGER PRIMARY KEY AUTOINCREMENT"; 
  private static final String DEF_COLUMN_CATEGORY_DESC = "category_desc TEXT";
  // nameMonth Table Columns
  private static final String DEF_COLUMN_NAMEMONTH_ID = "namemonth_id INTEGER PRIMARY KEY AUTOINCREMENT";
  private static final String DEF_COLUMN_NAMEMONTH_DESC = "namemonth_desc TEXT";

  // CREATE TABLES
  private  String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
        + DEF_COLUMN_USER_ID + ","
        + DEF_COLUMN_USER_NAME + ","
        + DEF_COLUMN_USER_EMAIL + ","
        + DEF_COLUMN_USER_PASSWORD + ")";
  private String CREATE_MONTH_TABLE = "CREATE TABLE " + TABLE_MONTH + "("
        + DEF_COLUMN_MONTH_ID + "," 
        + DEF_COLUMN_MONTH_USER_ID + "," 
        + DEF_COLUMN_MONTH_PERIOD_ID + "," 
        + DEF_COLUMN_MONTH_EXPECTED_ID + "," 
        + DEF_COLUMN_MONTH_BALDIFF + ")";
  private String CREATE_EXPECTED_TABLE = "CREATE TABLE " + TABLE_EXPECTED + "("
        + DEF_COLUMN_EXPECTED_ID + "," 
        + DEF_COLUMN_EXPECTED_BALANCE + "," 
        + DEF_COLUMN_EXPECTED_MONTHLY_INCOME + "," 
        + DEF_COLUMN_EXPECTED_MONTHLY_OUTCOME + ")";
  private String CREATE_PERIOD_TABLE = "CREATE TABLE " + TABLE_PERIOD + "("
        + DEF_COLUMN_PERIOD_ID + "," 
        + DEF_COLUMN_PERIOD_BALANCE + "," 
        + DEF_COLUMN_PERIOD_TRANSACTIONS + ")";
  private String CREATE_TRANSACT_TABLE = "CREATE TABLE " + TABLE_TRANSACT + "("
        + DEF_COLUMN_TRANSACT_ID + "," 
        + DEF_COLUMN_TRANSACT_PERIOD_ID + "," 
        + DEF_COLUMN_TRANSACT_TYPE_ID + "," 
        + DEF_COLUMN_TRANSACT_DATE + "," 
        + DEF_COLUMN_TRANSACT_AMOUNT + "," 
        + DEF_COLUMN_TRANSACT_CATEGORY_1 + "," 
        + DEF_COLUMN_TRANSACT_CATEGORY_2 + "," 
        + DEF_COLUMN_TRANSACT_CATEGORY_3 + ")";
  private String CREATE_TYPE_TABLE = "CREATE TABLE " + TABLE_TYPE + "("
        + DEF_COLUMN_TYPE_ID + "," 
        + DEF_COLUMN_TYPE_DESC + ")";
  private String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
        + DEF_COLUMN_CATEGORY_ID + "," 
        + DEF_COLUMN_CATEGORY_DESC + ")";
  private String CREATE_NAMEMONTH_TABLE = "CREATE TABLE " + TABLE_NAMEMONTH + "("
        + DEF_COLUMN_NAMEMONTH_ID + "," 
        + DEF_COLUMN_NAMEMONTH_DESC + ")";

  // DROP TABLES
  private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
  private String DROP_MONTH_TABLE = "DROP TABLE IF EXISTS " + TABLE_MONTH;
  private String DROP_EXPECTED_TABLE = "DROP TABLE IF EXISTS " + TABLE_EXPECTED;
  private String DROP_PERIOD_TABLE = "DROP TABLE IF EXISTS " + TABLE_PERIOD;
  private String DROP_TRANSACT_TABLE = "DROP TABLE IF EXISTS " + TABLE_TRANSACT;
  private String DROP_TYPE_TABLE = "DROP TABLE IF EXISTS " + TABLE_TYPE;
  private String DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + TABLE_CATEGORY;
  private String DROP_NAMEMONTH_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAMEMONTH;

  // INIT TABLES
  private String INIT_TYPE_TABLE = "INSERT INTO " + TABLE_TYPE + "("
    + COLUMN_TYPE_ID + ","
    + COLUMN_TYPE_DESC + ") VALUES "
    + "(0, 'Income')"
    + ",(1, 'Outcome')";
  private String INIT_CATEGORY_TABLE = "INSERT INTO " + TABLE_CATEGORY + "("
    + COLUMN_CATEGORY_ID + ","
    + COLUMN_CATEGORY_DESC + ") VALUES "
    + "(0, 'Bills')"
    + ",(1, 'Rent')"
    + ",(2, 'Restaunrant')"
    + ",(3, 'Transport')"
    + ",(4, 'Education')"
    + ",(5, 'Health')"
    + ",(6, 'Investment')"
    + ",(7, 'Rent')"
    + ",(8, 'Salary')"
    + ",(9, 'Other')";
  private String INIT_NAMEMONTH_TABLE = "INSERT INTO " + TABLE_NAMEMONTH + "("
    + COLUMN_NAMEMONTH_ID + ","
    + COLUMN_NAMEMONTH_DESC + ") VALUES "
    + "(0, 'Other')"
    + ",(1, 'January')"
    + ",(2, 'February')"
    + ",(3, 'March')"
    + ",(4, 'April')"
    + ",(5, 'May')"
    + ",(6, 'June')"
    + ",(7, 'July')"
    + ",(8, 'August')"
    + ",(9, 'September')"
    + ",(10, 'Octuber')"
    + ",(11, 'November')"
    + ",(12, 'December')";

  /**
   * Constructor
   * 
   * @param context
   */
  public DatabaseHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // Create Tables
    db.execSQL(CREATE_USER_TABLE);
    db.execSQL(CREATE_MONTH_TABLE);
    db.execSQL(CREATE_EXPECTED_TABLE);
    db.execSQL(CREATE_PERIOD_TABLE);
    db.execSQL(CREATE_TRANSACT_TABLE);
    db.execSQL(CREATE_TYPE_TABLE);
    db.execSQL(CREATE_CATEGORY_TABLE);
    db.execSQL(CREATE_NAMEMONTH_TABLE);
    // Initialize Tables
    db.execSQL(INIT_TYPE_TABLE);
    Log.i("DB","1");
    Log.i("DB",INIT_CATEGORY_TABLE);
    db.execSQL(INIT_CATEGORY_TABLE);
    Log.i("DB","2");
    Log.i("DB",INIT_NAMEMONTH_TABLE);
    db.execSQL(INIT_NAMEMONTH_TABLE);
    Log.i("DB","3");

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Drop User Table if exist
    db.execSQL(DROP_USER_TABLE);
    db.execSQL(DROP_MONTH_TABLE);
    db.execSQL(DROP_EXPECTED_TABLE);
    db.execSQL(DROP_PERIOD_TABLE);
    db.execSQL(DROP_TRANSACT_TABLE);
    db.execSQL(DROP_TYPE_TABLE);
    db.execSQL(DROP_CATEGORY_TABLE);
    // Create tables again
    onCreate(db);
  }

  // METHODS INI - USER
  /**
   * This method is to create user record
   *
   * @param user
   */
  public void addUser(User user) {
      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(COLUMN_USER_NAME, user.getName());
      values.put(COLUMN_USER_EMAIL, user.getEmail());
      values.put(COLUMN_USER_PASSWORD, user.getPassword());

      // Inserting Row
      db.insert(TABLE_USER, null, values);
      db.close();
  }

  /**
   * This method is to fetch all user and return the list of user records
   *
   * @return list
   */
  public List<User> getAllUser() {
    // array of columns to fetch
    String[] columns = {
      COLUMN_USER_ID,
      COLUMN_USER_EMAIL,
      COLUMN_USER_NAME,
      COLUMN_USER_PASSWORD
    };
    // sorting orders
    String sortOrder =
      COLUMN_USER_NAME + " ASC";
    List<User> userList = new ArrayList<User>();

    SQLiteDatabase db = this.getReadableDatabase();

    // query the user table
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
     */
    Cursor cursor = db.query(
      TABLE_USER, //Table to query
      columns,    //columns to return
      null,        //columns for the WHERE clause
      null,        //The values for the WHERE clause
      null,       //group the rows
      null,       //filter by row groups
      sortOrder); //The sort order


    // Traversing through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        User user = new User();
        user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
        user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
        // Adding user record to list
        userList.add(user);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();

    // return user list
    return userList;
  }

  /**
   * This method to update user record
   *
   * @param user
   */
  public void updateUser(User user) {
      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(COLUMN_USER_NAME, user.getName());
      values.put(COLUMN_USER_EMAIL, user.getEmail());
      values.put(COLUMN_USER_PASSWORD, user.getPassword());

      // updating row
      db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
              new String[]{String.valueOf(user.getId())});
      db.close();
  }

  /**
   * This method is to delete user record
   *
   * @param user
   */
  public void deleteUser(User user) {
      SQLiteDatabase db = this.getWritableDatabase();
      // delete user record by id
      db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
              new String[]{String.valueOf(user.getId())});
      db.close();
  }

  /**
   * This method to check user exist or not
   *
   * @param email
   * @return true/false
   */
  public boolean checkUser(String email) {

    // array of columns to fetch
    String[] columns = {
      COLUMN_USER_ID
    };
    SQLiteDatabase db = this.getReadableDatabase();

    // selection criteria
    String selection = COLUMN_USER_EMAIL + " = ?";

    // selection argument
    String[] selectionArgs = {email};

    // query user table with condition
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
     */
    Cursor cursor = db.query(
      TABLE_USER,                 //Table to query
      columns,                    //columns to return
      selection,                  //columns for the WHERE clause
      selectionArgs,              //The values for the WHERE clause
      null,                       //group the rows
      null,                      //filter by row groups
      null);                      //The sort order
    int cursorCount = cursor.getCount();
    cursor.close();
    db.close();

    if (cursorCount > 0) {
      return true;
    }

    return false;
  }

  /**
   * This method to check user exist or not
   *
   * @param email
   * @param password
   * @return true/false
   */
  public boolean checkUser(String email, String password) {

    // array of columns to fetch
    String[] columns = {
            COLUMN_USER_ID
    };
    SQLiteDatabase db = this.getReadableDatabase();
    // selection criteria
    String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

    // selection arguments
    String[] selectionArgs = {email, password};

    // query user table with conditions
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
     */
    Cursor cursor = db.query(
      TABLE_USER,                 //Table to query
      columns,                    //columns to return
      selection,                  //columns for the WHERE clause
      selectionArgs,              //The values for the WHERE clause
      null,                       //group the rows
      null,                       //filter by row groups
      null);                      //The sort order
    int cursorCount = cursor.getCount();

    cursor.close();
    db.close();
    if (cursorCount > 0) {
        return true;
    }

    return false;
  }
  // METHODS END - USER

  // METHODS INI - MONTH
  // METHODS END - MONTH

  // METHODS INI - EXPECTED
  // METHODS END - EXPECTED

  // METHODS INI - PERIOD
  // METHODS END - PERIOD

  // METHODS INI - TRANSACT
  // METHODS END - TRANSACT

  // METHODS INI - TYPE
  // METHODS END - TYPE

  // METHODS INI - CATEGORY
  // METHODS END - CATEGORY
}
