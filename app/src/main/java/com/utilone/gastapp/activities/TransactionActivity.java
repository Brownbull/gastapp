package com.utilone.gastapp.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.utilone.gastapp.R;
import com.utilone.gastapp.model.Transact;
import com.utilone.gastapp.sql.DatabaseHelper;

import java.text.SimpleDateFormat;
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
  String category;
//  String[] monthNames;
  long userID;
  long transactID;
  long monthID;
  String monthName;
  long expectedID;
  long periodID;
  String type;
  int amnt;
  int day;
  String desc;
  String temp;

  // FRONTEND
  private EditText edtDay;
  private TextView tvMonth;
//  private Spinner spinnerMonths;
  private TextView tvYear;
  private EditText edtTrAmnt;
  private Spinner spinnerCategories;
  private Button btnTrIncome;
  private Button btnTrOutcome;
  private Button btnTrDelete;
  private Button btnTrSubmit;
  private EditText edtTrDesc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    
    Intent Panel = getIntent();
    temp = Panel.getStringExtra("USERID");
    userID = Long.valueOf(temp);
    temp = Panel.getStringExtra("MONTHID");
    monthID = Long.valueOf(temp);
    monthName = Panel.getStringExtra("MONTHNAME");
    Log.i("TransactionActivity", "monthName: " + monthName);
    temp = Panel.getStringExtra("EXPECTEDID");
    expectedID = Long.valueOf(temp);
    temp = Panel.getStringExtra("PERIODID");
    periodID = Long.valueOf(temp);
    
    transact = new Transact();
    temp = Panel.getStringExtra("TRANSACTID");
    transact.setId(Long.valueOf(temp));
    transact.setPeriodID(periodID);
    transact.setType(Panel.getStringExtra("TRANSACTTYPE"));
    temp = Panel.getStringExtra("TRANSACTDAY");
    transact.setTransactDay(Integer.parseInt(temp));
    temp = Panel.getStringExtra("TRANSACTAMNT");
    transact.setAmount(Integer.parseInt(temp));
    transact.setCategory(Panel.getStringExtra("TRANSACTCATEGORY"));
    transact.setDesc(Panel.getStringExtra("TRANSACTDESC"));

    Log.i("TransactionActivity onCreate", "TRANSACT: " + transact.toString());
  
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
    edtDay = (EditText) findViewById(R.id.edt_day);
    tvMonth = (TextView) findViewById(R.id.tv_month);
//    spinnerMonths = (Spinner) findViewById(R.id.spinner_months);
    tvYear = (TextView) findViewById(R.id.edt_year);
    edtTrAmnt = (EditText) findViewById(R.id.edt_tr_amnt);
    spinnerCategories = (Spinner) findViewById(R.id.spinner_categories);
    btnTrIncome = (Button) findViewById(R.id.btn_tr_income);
    btnTrOutcome = (Button) findViewById(R.id.btn_tr_outcome);
    btnTrDelete = (Button) findViewById(R.id.btn_tr_delete);
    btnTrSubmit = (Button) findViewById(R.id.btn_tr_submit);
    edtTrDesc = (EditText) findViewById(R.id.edt_tr_desc);
  }

  private void initObjects(long userID) {
    databaseHelper = new DatabaseHelper(activity);
    // MONTHS INIT
    tvMonth.setText(monthName);
    Log.i("TransactionActivity", "monthName; " + monthName);
    // monthNames = databaseHelper.getAllNameMonthStrings();
    // ArrayAdapter<String> adapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monthNames);
    // spinnerMonths.setAdapter(adapterMonths);
    // CATEGORIES INIT
    categories = databaseHelper.getAllCategories();
    ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
    spinnerCategories.setAdapter(adapterCategories);

    // OTHER INITS
    edtDay.setTextSize(20);
    edtDay.setBackgroundResource(R.drawable.month_radius);;
    edtDay.setText(String.valueOf(cday));
    tvYear.setText(String.valueOf(cyear));
    btnTrIncome.setText("Income");
    btnTrOutcome.setText("Outcome");
    type = "Outcome";
    btnTrOutcome.setBackgroundResource(R.drawable.outcome_radius);
    btnTrIncome.setBackgroundResource(R.drawable.inactive_radius);

    if(transact.getId() != -16){
      btnTrDelete.setVisibility(LinearLayout.VISIBLE);
      if(transact.getType().equals("Income")){
        type = "Income";
        btnTrIncome.setBackgroundResource(R.drawable.income_radius);
        btnTrOutcome.setBackgroundResource(R.drawable.inactive_radius);
      }else if (transact.getType().equals("Outcome")){
        type = "Outcome";
        btnTrOutcome.setBackgroundResource(R.drawable.outcome_radius);
        btnTrIncome.setBackgroundResource(R.drawable.inactive_radius);
      }
      
      edtTrAmnt.setText(transact.getAmountStr());
      int spinnerPosition = adapterCategories.getPosition(transact.getCategory());
      //set the default according to value
      spinnerCategories.setSelection(spinnerPosition);

      edtTrDesc.setText(transact.getDesc());
      edtDay.setText(transact.getTransactDayStr());
    }

  }

  public void setIncome(View view){
    type = "Income";
    btnTrIncome.setBackgroundResource(R.drawable.income_radius);
    btnTrOutcome.setBackgroundResource(R.drawable.inactive_radius);
  }

  public void setOutcome(View view){
    type = "Outcome";
    btnTrOutcome.setBackgroundResource(R.drawable.outcome_radius);
    btnTrIncome.setBackgroundResource(R.drawable.inactive_radius);
  }

  public void addCurrentTransact(View view){
    temp = edtTrAmnt.getText().toString();
    amnt = Integer.parseInt(temp);
    category = spinnerCategories.getSelectedItem().toString();
    desc = edtTrDesc.getText().toString();
    temp = edtDay.getText().toString();
    day = Integer.parseInt(temp);

    if (transact.getId() == -16){
      transact = new Transact(periodID, type, day, amnt, category, desc);

      transactID = databaseHelper.addTransact(transact);
      transact.setId(transactID);

      Log.i("addCurrentTransact NEW", "transact: " + transact.toString());
    }
    else {
      Log.i("addCurrentTransact UPD PRE", "transact: " + transact.toString());
      transact.setType(type);
      transact.setTransactDay(day);
      transact.setAmount(amnt);
      transact.setCategory(category);
      transact.setDesc(desc);
      databaseHelper.updateTransact(transact);

      Log.i("addCurrentTransact UPD POST", "transact: " + transact.toString());
    }

    finish();
  }

  public void deleteCurrentTransact(View view){
    temp = edtTrAmnt.getText().toString();
    amnt = Integer.parseInt(temp);
    category = spinnerCategories.getSelectedItem().toString();
    desc = edtTrDesc.getText().toString();
    temp = edtDay.getText().toString();
    day = Integer.parseInt(temp);
    Log.i("deleteCurrentTransact DELETE", "transact: " + transact.toString());

    if (transact.getId() != -16){
    
      transact.setType(type);
      transact.setTransactDay(day);
      transact.setAmount(amnt);
      transact.setCategory(category);
      transact.setDesc(desc);
      databaseHelper.deleteTransact(transact);
      Log.i("deleteCurrentTransact DELETE", "DELETED");

      
    }

    finish();
  }
}
