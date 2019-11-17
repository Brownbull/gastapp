package com.utilone.gastapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.utilone.gastapp.R;
import com.utilone.gastapp.model.Transact;
import com.utilone.gastapp.sql.DatabaseHelper;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {
  // SYSTEMS
  private final AppCompatActivity activity = TransactionActivity.this;
  private DatabaseHelper databaseHelper;
  Calendar c = Calendar.getInstance();
  int cyear = c.get(Calendar.YEAR);
  int cmonth = c.get(Calendar.MONTH);
  int cday = c.get(Calendar.DAY_OF_MONTH);
  Calendar cDate = new GregorianCalendar(cyear, cmonth, cday);

  int cdaysInMonth = cDate.getActualMaximum(Calendar.DAY_OF_MONTH);

  // BACKEND
  Transact transact;
  String[] categories;
  String[] monthNames;
  long userID;
  long monthID;
  String monthName;
  long expectedID;
  long periodID;

  // FRONTEND
  private EditText tvDay;
  private Spinner spinnerMonths;
  private EditText tvYear;
  private EditText edtTrAmnt;
  private Spinner spinnerCategories;
  private Button btnTrIncome;
  private Button btnTrOutcome;
  private Button btnTrSubmit;
  private EditText edtTrDesc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction);

    Intent Panel = getIntent();
    String tempUserID = Panel.getStringExtra("USERID");
    userID = Long.valueOf(tempUserID);
    String tempMonthID = Panel.getStringExtra("MONTHID");
    monthID = Long.valueOf(tempMonthID);
    String monthName = Panel.getStringExtra("MONTHNAME");
    String tempExpectedID = Panel.getStringExtra("EXPECTEDID");
    expectedID = Long.valueOf(tempExpectedID);
    String tempPeriodID = Panel.getStringExtra("PERIODID");
    periodID = Long.valueOf(tempPeriodID);

    Log.i("TransactionActivity","cyear: " + cyear);
    Log.i("TransactionActivity","cmonth: " + cmonth);
    Log.i("TransactionActivity","cday: " + cday);
    Log.i("TransactionActivity","cdaysInMonth: " + cdaysInMonth);

    initViews();
    initObjects(userID);

  }

  /**
   * This method is to initialize views
   */
  private void initViews() {
    tvDay = (EditText) findViewById(R.id.edt_day);
    spinnerMonths = (Spinner) findViewById(R.id.spinner_months);
    tvYear = (EditText) findViewById(R.id.edt_year);
    edtTrAmnt = (EditText) findViewById(R.id.edt_tr_amnt);
    spinnerCategories = (Spinner) findViewById(R.id.spinner_categories);
    btnTrIncome = (Button) findViewById(R.id.btn_tr_income);
    btnTrOutcome = (Button) findViewById(R.id.btn_tr_outcome);
    btnTrSubmit = (Button) findViewById(R.id.btn_tr_submit);
    edtTrDesc = (EditText) findViewById(R.id.edt_tr_desc);
  }

  private void initObjects(long userID) {
    databaseHelper = new DatabaseHelper(activity);
    transact = databaseHelper.addTransact(periodID);
    // MONTHS INIT
    monthNames = databaseHelper.getAllNameMonthStrings();
    ArrayAdapter<String> adapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monthNames);
    spinnerMonths.setAdapter(adapterMonths);
    // CATEGORIES INIT
    categories = databaseHelper.getAllCategories();
    ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
    spinnerCategories.setAdapter(adapterCategories);

    // OTHER INITS
    tvDay.setText(String.valueOf(cday));
    tvYear.setText(String.valueOf(cyear));

    btnTrIncome.setText("Income");
    btnTrIncome.setBackgroundResource(R.drawable.income_radius);
    btnTrOutcome.setText("Outcome");
    btnTrOutcome.setBackgroundResource(R.drawable.outcome_radius);
    
  }
}
