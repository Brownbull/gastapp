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

import java.util.List;

public class TransactionActivity extends AppCompatActivity {
  // SYSTEMS
  private final AppCompatActivity activity = TransactionActivity.this;
  private DatabaseHelper databaseHelper;
  
  // BACKEND
  Transact transact;
  String[] categories;
  long userID;
  long monthID;
  long expectedID;
  long periodID;

  // FRONTEND
  private EditText edtTrDt;
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
    String tempExpectedID = Panel.getStringExtra("EXPECTEDID");
    expectedID = Long.valueOf(tempExpectedID);
    String tempPeriodID = Panel.getStringExtra("PERIODID");
    periodID = Long.valueOf(tempPeriodID);

    initViews();
    initObjects(userID);
  }

  /**
   * This method is to initialize views
   */
  private void initViews() {
    edtTrDt = (EditText) findViewById(R.id.edt_tr_dt);
    edtTrAmnt = (EditText) findViewById(R.id.edt_tr_amnt);
    spinnerCategories = (Spinner) findViewById(R.id.spinner_categories);
    btnTrIncome = (Button) findViewById(R.id.btn_tr_income);
    btnTrOutcome = (Button) findViewById(R.id.btn_tr_outcome);
    btnTrSubmit = (Button) findViewById(R.id.btn_tr_submit);
    edtTrDesc = (EditText) findViewById(R.id.edt_tr_desc);
  }

  private void initObjects(long userID) {
    databaseHelper = new DatabaseHelper(activity);
    categories = databaseHelper.getAllCategories();
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
    spinnerCategories.setAdapter(adapter);
    transact = databaseHelper.addTransact(periodID);

    btnTrIncome.setText("Income");
    btnTrIncome.setBackgroundResource(R.drawable.income_radius);
    btnTrOutcome.setText("Outcome");
    btnTrOutcome.setBackgroundResource(R.drawable.outcome_radius);
    
  }
}
