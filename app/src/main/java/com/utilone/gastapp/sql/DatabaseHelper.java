package com.utilone.gastapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.utilone.gastapp.model.Expected;
import com.utilone.gastapp.model.Month;
import com.utilone.gastapp.model.NameMonth;
import com.utilone.gastapp.model.Period;
import com.utilone.gastapp.model.Transact;
import com.utilone.gastapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016 -> Modified by Brownbull 11/09/2019-> Modified by Brownbull 11/09/2019.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

  // DATABASE
  // Database Version
  private static final int DATABASE_VERSION = 2;
  // Database Name
  private static final String DATABASE_NAME = "GastApp.db";

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
  private static final String COLUMN_USER_CURRMONTH_ID = "user_currmonth_id";
  // Month Table Columns
  private static final String COLUMN_MONTH_ID = "month_id";    
  private static final String COLUMN_MONTH_USER_ID = "month_user_id";
  private static final String COLUMN_MONTH_PERIOD_ID = "month_period_id";
  private static final String COLUMN_MONTH_EXPECTED_ID = "month_expected_id";
  private static final String COLUMN_MONTH_NAME = "month_name";
  private static final String COLUMN_MONTH_YEAR = "month_year";
  private static final String COLUMN_MONTH_BALDIFF = "month_baldiff";
  // Expected Table Columns
  private static final String COLUMN_EXPECTED_ID = "expected_id";    
  private static final String COLUMN_EXPECTED_BALANCE = "expected_balance";
  private static final String COLUMN_EXPECTED_MONTHLY_INCOME = "expected_monthly_income";
  private static final String COLUMN_EXPECTED_MONTHLY_OUTCOME = "expected_monthly_outcome";
  // Period Table Columns
  private static final String COLUMN_PERIOD_ID = "period_id";   
  private static final String COLUMN_PERIOD_INCOMES = "period_incomes";   
  private static final String COLUMN_PERIOD_OUTCOMES = "period_outcomes";   
  private static final String COLUMN_PERIOD_BALANCE = "period_balance";   
  private static final String COLUMN_PERIOD_TRANSACTIONS = "period_transactions";   
  // Transact Table Columns
  private static final String COLUMN_TRANSACT_ID = "transact_id"; 
  private static final String COLUMN_TRANSACT_PERIOD_ID = "transact_period_id"; 
  private static final String COLUMN_TRANSACT_TYPE = "transact_type"; 
  private static final String COLUMN_TRANSACT_DAY = "transact_day"; 
  private static final String COLUMN_TRANSACT_AMOUNT = "transact_amount"; 
  private static final String COLUMN_TRANSACT_CATEGORY = "transact_category";
  private static final String COLUMN_TRANSACT_DESC = "transact_desc";
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
  private static final String DEF_COLUMN_USER_ID = COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";
  private static final String DEF_COLUMN_USER_NAME = COLUMN_USER_NAME + " TEXT";
  private static final String DEF_COLUMN_USER_EMAIL = COLUMN_USER_EMAIL + " TEXT";
  private static final String DEF_COLUMN_USER_PASSWORD = COLUMN_USER_PASSWORD + " TEXT";
  private static final String DEF_COLUMN_USER_CURRMONTH_ID = COLUMN_USER_CURRMONTH_ID + " INT";
  // Month Table Columns
  private static final String DEF_COLUMN_MONTH_ID = COLUMN_MONTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";    
  private static final String DEF_COLUMN_MONTH_USER_ID = COLUMN_MONTH_USER_ID + " INTEGER";
  private static final String DEF_COLUMN_MONTH_PERIOD_ID = COLUMN_MONTH_PERIOD_ID + " INTEGER";
  private static final String DEF_COLUMN_MONTH_EXPECTED_ID = COLUMN_MONTH_EXPECTED_ID + " INTEGER";
  private static final String DEF_COLUMN_MONTH_NAME = COLUMN_MONTH_NAME + " TEXT";
  private static final String DEF_COLUMN_MONTH_YEAR = COLUMN_MONTH_YEAR + " INTEGER";
  private static final String DEF_COLUMN_MONTH_BALDIFF = COLUMN_MONTH_BALDIFF + " INTEGER";
  // Expected Table Columns
  private static final String DEF_COLUMN_EXPECTED_ID = COLUMN_EXPECTED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";    
  private static final String DEF_COLUMN_EXPECTED_BALANCE = COLUMN_EXPECTED_BALANCE + " INTEGER";
  private static final String DEF_COLUMN_EXPECTED_MONTHLY_INCOME = COLUMN_EXPECTED_MONTHLY_INCOME + " INTEGER";
  private static final String DEF_COLUMN_EXPECTED_MONTHLY_OUTCOME = COLUMN_EXPECTED_MONTHLY_OUTCOME + " INTEGER";
  // Period Table Columns
  private static final String DEF_COLUMN_PERIOD_ID = COLUMN_PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";   
  private static final String DEF_COLUMN_PERIOD_INCOMES = COLUMN_PERIOD_INCOMES + " INTEGER";   
  private static final String DEF_COLUMN_PERIOD_OUTCOMES = COLUMN_PERIOD_OUTCOMES + " INTEGER";   
  private static final String DEF_COLUMN_PERIOD_BALANCE = COLUMN_PERIOD_BALANCE + " INTEGER";   
  private static final String DEF_COLUMN_PERIOD_TRANSACTIONS = COLUMN_PERIOD_TRANSACTIONS + " INTEGER";   
  // Transact Table Columns
  private static final String DEF_COLUMN_TRANSACT_ID = COLUMN_TRANSACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"; 
  private static final String DEF_COLUMN_TRANSACT_PERIOD_ID = COLUMN_TRANSACT_PERIOD_ID + " INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_TYPE = COLUMN_TRANSACT_TYPE + " TEXT"; 
  private static final String DEF_COLUMN_TRANSACT_DAY = COLUMN_TRANSACT_DAY + " INTEGER";
  private static final String DEF_COLUMN_TRANSACT_AMOUNT = COLUMN_TRANSACT_AMOUNT + " INTEGER"; 
  private static final String DEF_COLUMN_TRANSACT_CATEGORY = COLUMN_TRANSACT_CATEGORY + " TEXT"; 
  private static final String DEF_COLUMN_TRANSACT_DESC = COLUMN_TRANSACT_DESC + " TEXT"; 
  // Type Table Columns
  private static final String DEF_COLUMN_TYPE_ID = COLUMN_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"; 
  private static final String DEF_COLUMN_TYPE_DESC = COLUMN_TYPE_DESC + " TEXT"; 
  // Category Table Columns
  private static final String DEF_COLUMN_CATEGORY_ID = COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"; 
  private static final String DEF_COLUMN_CATEGORY_DESC = COLUMN_CATEGORY_DESC + " TEXT";
  // nameMonth Table Columns
  private static final String DEF_COLUMN_NAMEMONTH_ID = COLUMN_NAMEMONTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";
  private static final String DEF_COLUMN_NAMEMONTH_DESC = COLUMN_NAMEMONTH_DESC + " TEXT";

  // CREATE TABLES
  private  String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
        + DEF_COLUMN_USER_ID + ","
        + DEF_COLUMN_USER_NAME + ","
        + DEF_COLUMN_USER_EMAIL + ","
        + DEF_COLUMN_USER_PASSWORD + ","
        + DEF_COLUMN_USER_CURRMONTH_ID + ")";
  private String CREATE_MONTH_TABLE = "CREATE TABLE " + TABLE_MONTH + "("
        + DEF_COLUMN_MONTH_ID + "," 
        + DEF_COLUMN_MONTH_USER_ID + "," 
        + DEF_COLUMN_MONTH_PERIOD_ID + "," 
        + DEF_COLUMN_MONTH_EXPECTED_ID + "," 
        + DEF_COLUMN_MONTH_NAME + "," 
        + DEF_COLUMN_MONTH_YEAR + "," 
        + DEF_COLUMN_MONTH_BALDIFF + ")";
  private String CREATE_EXPECTED_TABLE = "CREATE TABLE " + TABLE_EXPECTED + "("
        + DEF_COLUMN_EXPECTED_ID + "," 
        + DEF_COLUMN_EXPECTED_BALANCE + "," 
        + DEF_COLUMN_EXPECTED_MONTHLY_INCOME + "," 
        + DEF_COLUMN_EXPECTED_MONTHLY_OUTCOME + ")";
  private String CREATE_PERIOD_TABLE = "CREATE TABLE " + TABLE_PERIOD + "("
        + DEF_COLUMN_PERIOD_ID + "," 
        + DEF_COLUMN_PERIOD_INCOMES + "," 
        + DEF_COLUMN_PERIOD_OUTCOMES + "," 
        + DEF_COLUMN_PERIOD_BALANCE + "," 
        + DEF_COLUMN_PERIOD_TRANSACTIONS + ")";
  private String CREATE_TRANSACT_TABLE = "CREATE TABLE " + TABLE_TRANSACT + "("
        + DEF_COLUMN_TRANSACT_ID + "," 
        + DEF_COLUMN_TRANSACT_PERIOD_ID + "," 
        + DEF_COLUMN_TRANSACT_TYPE + "," 
        + DEF_COLUMN_TRANSACT_DAY + "," 
        + DEF_COLUMN_TRANSACT_AMOUNT + "," 
        + DEF_COLUMN_TRANSACT_CATEGORY + ","
        + DEF_COLUMN_TRANSACT_DESC + ")";
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
    + ",(2, 'Restaurant')"
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
    + ",(10, 'October')"
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
    db.execSQL(INIT_CATEGORY_TABLE);
    db.execSQL(INIT_NAMEMONTH_TABLE);
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
      values.put(COLUMN_USER_CURRMONTH_ID, -16);

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
      values.put(COLUMN_USER_CURRMONTH_ID, user.getCurrMonth());
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

  /**
   * This method to check user exist or not
   *
   * @param email
   * @param password
   * @return true/false
   */
  public User getUser(String email, String password) {
    User user = new User();
    // array of columns to fetch
    String[] columns = {
        COLUMN_USER_ID,
        COLUMN_USER_EMAIL,
        COLUMN_USER_NAME,
        COLUMN_USER_CURRMONTH_ID
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

    if (cursorCount == 1 && cursor.moveToFirst()) {
        user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
        user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
        user.setCurrMonth(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CURRMONTH_ID))));
    }
    cursor.close();
    db.close();

    return user;
  }

  public User getUser(long userID) {
    User user = new User();
    // array of columns to fetch
    String[] columns = {
        COLUMN_USER_ID,
        COLUMN_USER_EMAIL,
        COLUMN_USER_NAME,
        COLUMN_USER_CURRMONTH_ID
    };
    SQLiteDatabase db = this.getReadableDatabase();
    // selection criteria
    String selection = COLUMN_USER_ID + " = ?";

    // selection arguments
    String[] selectionArgs = {String.valueOf(userID)};

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

    if (cursorCount == 1 && cursor.moveToFirst()) {
        user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
        user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
        user.setCurrMonth(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CURRMONTH_ID))));
    }

    cursor.close();
    db.close();

    return user;
  }
  
  // METHODS END - USER

  // METHODS INI - MONTH
  public long addMonth(long userID, String month, int year) {
    long id;
    long periodID = newPeriodID();
    long expectedID = newExpectedID();
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_MONTH_USER_ID, userID);
    values.put(COLUMN_MONTH_PERIOD_ID, periodID);
    values.put(COLUMN_MONTH_EXPECTED_ID, expectedID);
    values.put(COLUMN_MONTH_NAME, month);
    values.put(COLUMN_MONTH_YEAR, year);
    values.put(COLUMN_MONTH_BALDIFF, 0);

    // Inserting Row
    id = db.insert(TABLE_MONTH, null, values);
    db.close();
    return id;
  }

  public Month getMonth(long monthID) {
    Month month = new Month();
    int cursorCount = 0;
    // array of columns to fetch
    String[] columns = {
      COLUMN_MONTH_ID,
      COLUMN_MONTH_USER_ID,
      COLUMN_MONTH_PERIOD_ID,
      COLUMN_MONTH_EXPECTED_ID,
      COLUMN_MONTH_NAME,
      COLUMN_MONTH_YEAR,
      COLUMN_MONTH_BALDIFF
    };
    // selection criteria
    String selection = COLUMN_MONTH_ID + " = ?" ;
    
    // selection arguments
    String[] selectionArgs = { String.valueOf(monthID) };
    
    // query user table with conditions
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
     */
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(
      TABLE_MONTH,                //Table to query
      columns,                    //columns to return
      selection,                  //columns for the WHERE clause
      selectionArgs,              //The values for the WHERE clause
      null,                       //group the rows
      null,                       //filter by row groups
      null);                      //The sort order

    if (cursor.moveToFirst()) {
      cursorCount = cursor.getCount();
      month.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_ID))));
      month.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_USER_ID))));
      month.setPeriodID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_PERIOD_ID))));
      month.setExpectedID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_EXPECTED_ID))));
      month.setMonth(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_NAME)));
      month.setYear(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_YEAR))));
      month.setBalDiff(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_BALDIFF))));
    }
    cursor.close();
    db.close();

    return month;
  }
  // METHODS END - MONTH

  // METHODS INI - EXPECTED
  public long newExpectedID() {
    long id;
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_EXPECTED_BALANCE, 0);
    values.put(COLUMN_EXPECTED_MONTHLY_INCOME, 0);
    values.put(COLUMN_EXPECTED_MONTHLY_OUTCOME, 0);
    // Inserting Row
    id = db.insert(TABLE_EXPECTED, null, values);
    db.close();
    return id;
  }

  public void updateExpected(Expected expected) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(COLUMN_EXPECTED_BALANCE, expected.getBalance());
    values.put(COLUMN_EXPECTED_MONTHLY_INCOME, expected.getMonthlyIncome());
    values.put(COLUMN_EXPECTED_MONTHLY_OUTCOME, expected.getMonthlyOutcome());
    // updating row
    db.update(TABLE_EXPECTED, values, COLUMN_EXPECTED_ID + " = ?",
            new String[]{String.valueOf(expected.getId())});
    db.close();
  }

  public Expected getExpected(long expectedID) {
    Expected expected = new Expected();
    // array of columns to fetch
    String[] columns = {
      COLUMN_EXPECTED_ID,
      COLUMN_EXPECTED_BALANCE,
      COLUMN_EXPECTED_MONTHLY_INCOME,
      COLUMN_EXPECTED_MONTHLY_OUTCOME
    };
    SQLiteDatabase db = this.getReadableDatabase();
    // selection criteria
    String selection = COLUMN_EXPECTED_ID + " = ?" ;

    // selection arguments
    String[] selectionArgs = { String.valueOf(expectedID) };

    // query user table with conditions
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
     */
    Cursor cursor = db.query(
      TABLE_EXPECTED,                //Table to query
      columns,                    //columns to return
      selection,                  //columns for the WHERE clause
      selectionArgs,              //The values for the WHERE clause
      null,                       //group the rows
      null,                       //filter by row groups
      null);                      //The sort order
    int cursorCount = cursor.getCount();

    if (cursorCount > 0 && cursor.moveToFirst()) {
      expected.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED_ID))));
      expected.setBalance(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED_BALANCE))));
      expected.setMonthlyIncome(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED_MONTHLY_INCOME))));
      expected.setMonthlyOutcome(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED_MONTHLY_OUTCOME))));
    }
    cursor.close();
    db.close();

    return expected;
  }
  // METHODS END - EXPECTED

  // METHODS INI - PERIOD
  public long newPeriodID() {
    long id;
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_PERIOD_INCOMES, 0);
    values.put(COLUMN_PERIOD_OUTCOMES, 0);
    values.put(COLUMN_PERIOD_BALANCE, 0);
    values.put(COLUMN_PERIOD_TRANSACTIONS, 0);
    // Inserting Row
    id = db.insert(TABLE_PERIOD, null, values);
    db.close();
    return id;
  }

  public void updatePeriod(Period period) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(COLUMN_PERIOD_BALANCE, period.getBalance());
    values.put(COLUMN_PERIOD_TRANSACTIONS, period.getTransactions());
    // updating row
    db.update(TABLE_PERIOD, values, COLUMN_PERIOD_ID + " = ?",
            new String[]{String.valueOf(period.getId())});
    db.close();
  }

  public Period updatePeriodAmnts(long periodID) {
    int ins = 0;
    int outs = 0;
    int bal = 0;
    String type;
    int amnt;
    int howMany = 0;
    SQLiteDatabase db = this.getWritableDatabase();
    
    String[] columnsTransact = {
      COLUMN_TRANSACT_TYPE,
      COLUMN_TRANSACT_AMOUNT
    };

     // selection criteria
    String selection = COLUMN_TRANSACT_PERIOD_ID + " = ?";

    // selection argument
    String[] selectionArgs = {String.valueOf(periodID)};

    Cursor cursor = db.query(
      TABLE_TRANSACT, //Table to query
      columnsTransact,    //columns to return
      selection,        //columns for the WHERE clause
      selectionArgs,        //The values for the WHERE clause
      null,       //group the rows
      null,       //filter by row groups
      null); //The sort order

    // Traversing through all rows and adding to list
    if (cursor.moveToFirst()) {
      Log.i("updateObjects", "if");
      howMany = cursor.getCount();
      do {
        type = cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_TYPE));
        amnt = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_AMOUNT)));
        // Adding values
        if (type.equals("Income")){
          ins = ins + amnt;
        }
        else if (type.equals("Outcome")){
          outs = outs + amnt;
        }
      } while (cursor.moveToNext());
      
      ContentValues values = new ContentValues();
      
      values.put(COLUMN_PERIOD_INCOMES, ins);
      values.put(COLUMN_PERIOD_OUTCOMES, outs);
      values.put(COLUMN_PERIOD_BALANCE, ins - outs);
      values.put(COLUMN_PERIOD_TRANSACTIONS, howMany);
      // updating row
      db.update(TABLE_PERIOD, values, COLUMN_PERIOD_ID + " = ?", selectionArgs);
      cursor.close();
      db.close();

      return new Period(periodID, ins, outs, ins - outs, howMany );
    }
    else{
      Log.i("updateObjects", "else");
      Period prd = getPeriod(periodID);
      cursor.close();
      db.close();
      return prd;
    }
  }

  public Period getPeriod(long periodID) {
    Period period = new Period();
    // array of columns to fetch
    String[] columns = {
      COLUMN_PERIOD_ID,
      COLUMN_PERIOD_INCOMES,
      COLUMN_PERIOD_OUTCOMES,
      COLUMN_PERIOD_BALANCE,
      COLUMN_PERIOD_TRANSACTIONS
    };
    SQLiteDatabase db = this.getReadableDatabase();
    // selection criteria
    String selection = COLUMN_PERIOD_ID + " = ?" ;

    // selection arguments
    String[] selectionArgs = { String.valueOf(periodID) };

    // query user table with conditions
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
     */
    Cursor cursor = db.query(
      TABLE_PERIOD,                //Table to query
      columns,                    //columns to return
      selection,                  //columns for the WHERE clause
      selectionArgs,              //The values for the WHERE clause
      null,                       //group the rows
      null,                       //filter by row groups
      null);                      //The sort order
    int cursorCount = cursor.getCount();

    if (cursorCount > 0 && cursor.moveToFirst()) {
      period.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_ID))));
      period.setIncomes(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_INCOMES))));
      period.setOutcomes(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_OUTCOMES))));
      period.setBalance(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_BALANCE))));
      period.setTransactions(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_TRANSACTIONS))));
    }
    cursor.close();
    db.close();

    return period;
  }
  // METHODS END - PERIOD

  // METHODS INI - TRANSACT
  public Transact addTransact(long periodID) {
    long id;
    Transact tr = new Transact(periodID);

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_TRANSACT_PERIOD_ID, tr.getPeriodID());
    values.put(COLUMN_TRANSACT_TYPE, tr.getType());
    values.put(COLUMN_TRANSACT_DAY, tr.getTransactDay());
    values.put(COLUMN_TRANSACT_AMOUNT, tr.getAmount());
    values.put(COLUMN_TRANSACT_CATEGORY, tr.getCategory());
    values.put(COLUMN_TRANSACT_DESC, tr.getDesc());

    // Inserting Row
    id = db.insert(TABLE_TRANSACT, null, values);
    tr.setId(id);
    db.close();
    return tr;
  }

  public long addTransact(Transact transact) {
    long id;
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_TRANSACT_PERIOD_ID, transact.getPeriodID());
    values.put(COLUMN_TRANSACT_TYPE, transact.getType());
    values.put(COLUMN_TRANSACT_DAY, transact.getTransactDay());
    values.put(COLUMN_TRANSACT_AMOUNT, transact.getAmount());
    values.put(COLUMN_TRANSACT_CATEGORY, transact.getCategory());
    values.put(COLUMN_TRANSACT_DESC, transact.getDesc());

    // Inserting Row
    id = db.insert(TABLE_TRANSACT, null, values);
    db.close();
    return Long.valueOf(id);
  }

  public List<Transact> getAllTransact(long periodID, String type) {
    Log.i("getAllTransact", "periodID" + periodID);
    // array of columns to fetch
    String[] columns = {
      COLUMN_TRANSACT_TYPE,
      COLUMN_TRANSACT_DAY,
      COLUMN_TRANSACT_AMOUNT,
      COLUMN_TRANSACT_CATEGORY,
      COLUMN_TRANSACT_DESC
    };
    // selection criteria
    String selection = COLUMN_TRANSACT_PERIOD_ID + " = ?" + " AND " +  COLUMN_TRANSACT_TYPE + " =?";

    // selection arguments
    String[] selectionArgs = { String.valueOf(periodID), type };
    
    List<Transact> transactList = new ArrayList<Transact>();

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(
      TABLE_TRANSACT, //Table to query
      columns,    //columns to return
      selection,        //columns for the WHERE clause
      selectionArgs,        //The values for the WHERE clause
      null,       //group the rows
      null,       //filter by row groups
      null); //The sort order


    // Traversing through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        Transact transact = new Transact();
        transact.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_TYPE)));
        transact.setTransactDay(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_DAY))));
        transact.setAmount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_AMOUNT))));
        transact.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_CATEGORY)));
        transact.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACT_DESC)));
        // Adding transact record to list
        transactList.add(transact);
        Log.i("getAllTransact", transact.toString());
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();

    // return transact list
    return transactList;
  }
  // METHODS END - TRANSACT

  // METHODS INI - TYPE
  // METHODS END - TYPE

  // METHODS INI - CATEGORY
  public String[] getAllCategories() {
    // array of columns to fetch
    String[] columns = {
      COLUMN_CATEGORY_ID,
      COLUMN_CATEGORY_DESC
    };
    // sorting orders
    String sortOrder =
      COLUMN_CATEGORY_ID + " ASC";
    
    SQLiteDatabase db = this.getReadableDatabase();

    // query the user table
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
     */
    Cursor cursor = db.query(
      TABLE_CATEGORY, //Table to query
      columns,    //columns to return
      null,        //columns for the WHERE clause
      null,        //The values for the WHERE clause
      null,       //group the rows
      null,       //filter by row groups
      sortOrder); //The sort order
    String[] cats = new String[cursor.getCount()];
    // Traversing through all rows and adding to list
    int i;
    if (cursor.moveToFirst()) {
      for( i = 0; i < cursor.getCount() ; i++){
        cats[i] = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_DESC));
        cursor.moveToNext();
      }
    }
    // for( i = 0; i < cursor.getCount() ; i++){
    //   Log.i("initTransact", "i:" + i + " cats[i]: "+ cats[i]);
    // }

    cursor.close();
    db.close();
    // return user list
    return cats;
  }
  // METHODS END - CATEGORY

  // METHODS INI - NAMEMONTH
  public List<NameMonth> getAllNameMonth() {
    // array of columns to fetch
    String[] columns = {
      COLUMN_NAMEMONTH_ID,
      COLUMN_NAMEMONTH_DESC
    };
    // sorting orders
    String sortOrder =
      COLUMN_NAMEMONTH_ID + " ASC";
    List<NameMonth> nameMonthList = new ArrayList<NameMonth>();
    SQLiteDatabase db = this.getReadableDatabase();

    // query the user table
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
     */
    Cursor cursor = db.query(
      TABLE_NAMEMONTH, //Table to query
      columns,    //columns to return
      null,        //columns for the WHERE clause
      null,        //The values for the WHERE clause
      null,       //group the rows
      null,       //filter by row groups
      sortOrder); //The sort order

    // Traversing through all rows and adding to list
    if (cursor.moveToFirst()) {

      do {
        NameMonth user = new NameMonth();
        user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAMEMONTH_ID))));
        user.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_NAMEMONTH_DESC)));
        // Adding user record to list
        nameMonthList.add(user);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    // return user list
    return nameMonthList;
  }

  public String[] getAllNameMonthStrings() {
    // array of columns to fetch
    String[] columns = {
      COLUMN_NAMEMONTH_ID,
      COLUMN_NAMEMONTH_DESC
    };
    // sorting orders
    String sortOrder =
      COLUMN_NAMEMONTH_ID + " ASC";
    
    SQLiteDatabase db = this.getReadableDatabase();

    // query the user table
    /**
     * Here query function is used to fetch records from user table this function works like we use sql query.
     * SQL query equivalent to this query function is
     * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
     */
    Cursor cursor = db.query(
      TABLE_NAMEMONTH, //Table to query
      columns,    //columns to return
      null,        //columns for the WHERE clause
      null,        //The values for the WHERE clause
      null,       //group the rows
      null,       //filter by row groups
      sortOrder); //The sort order
    String[] cats = new String[cursor.getCount()];
    // Traversing through all rows and adding to list
    int i;
    if (cursor.moveToFirst()) {
      for( i = 0; i < cursor.getCount() ; i++){
        cats[i] = cursor.getString(cursor.getColumnIndex(COLUMN_NAMEMONTH_DESC));
        cursor.moveToNext();
      }
    }
    // for( i = 0; i < cursor.getCount() ; i++){
    //   Log.i("initTransact", "i:" + i + " cats[i]: "+ cats[i]);
    // }

    cursor.close();
    db.close();
    // return user list
    return cats;
  }
  // METHODS END - NAMEMONTH
}
