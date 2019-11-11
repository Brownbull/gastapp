package com.utilone.gastapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.utilone.gastapp.R;
import com.utilone.gastapp.model.Expected;
import com.utilone.gastapp.model.Month;
import com.utilone.gastapp.model.NameMonth;
import com.utilone.gastapp.model.Period;
import com.utilone.gastapp.model.Transact;
import com.utilone.gastapp.model.User;
import com.utilone.gastapp.sql.DatabaseHelper;

import java.util.Calendar;
import java.util.List;

public class PanelActivity extends AppCompatActivity {
  // SYSTEM
  private final AppCompatActivity activity = PanelActivity.this;
  Calendar c = Calendar.getInstance();
  int cyear = c.get(Calendar.YEAR);
  int cmonth = c.get(Calendar.MONTH);
  List<NameMonth> nameMonthList;
  private DatabaseHelper databaseHelper;

  // BACKEND
  long userID;
  String email;
  int DiffBal;
  private User user;
  private Month month;
  private Expected expected;
  private Period period;
  private Transact transact;
  private NameMonth nameMonth;
  
  // FRONTEND
  private TextView tvMonth;
  private TextView tvYear;
  private TextView tvCurrBalance;
  private TextView tvDiffBalance;
  private LinearLayout llIncome;
  private EditText edtIncomeAmnt;
  private TextView tvConfirmIncome;
  private LinearLayout llOutcome;
  private EditText edtOutcomeAmnt;
  private TextView tvConfirmOutcome;
  private Button btnTransaction;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_panel);
    Intent Login = getIntent();
    String tempUserID = Login.getStringExtra("USERID");
    userID = Long.valueOf(tempUserID);
    email = Login.getStringExtra("EMAIL");

    // INIT
    initViews();
    initObjects(userID);
  }

  /**
   * This method is to initialize views
   */
  private void initViews() {
    tvMonth = (TextView) findViewById(R.id.tv_month);
    tvYear = (TextView) findViewById(R.id.tv_year);
    tvCurrBalance = (TextView) findViewById(R.id.tv_CurrBalance);
    tvDiffBalance = (TextView) findViewById(R.id.tv_DiffBalance);
    llIncome = (LinearLayout) findViewById(R.id.ll_income);
    edtIncomeAmnt = (EditText) findViewById(R.id.edt_income_amnt);
    tvConfirmIncome = (TextView) findViewById(R.id.tv_confirm_income);
    llOutcome = (LinearLayout) findViewById(R.id.ll_outcome);
    edtOutcomeAmnt = (EditText) findViewById(R.id.edt_outcome_amnt);
    tvConfirmOutcome = (TextView) findViewById(R.id.tv_confirm_outcome);
  }

  /**
   * This method is to initialize objects to be used
   */
  private void initObjects(long userID) {
    databaseHelper = new DatabaseHelper(activity);
    nameMonthList = databaseHelper.getAllNameMonth();
    user = databaseHelper.getUser(userID);
    transact = new Transact();
    // NEW USER
    if (user.getCurrMonth() == -16){
      Log.i("initPanel", "if case");
      String cmonthName = nameMonthList.get(cmonth + 1).getDesc();
      user.setCurrMonth(databaseHelper.addMonth(userID, cmonthName, cyear));
      databaseHelper.updateUser(user);
      updateObjects(); 
    }
    // OLD USER
    else {
      Log.i("initPanel", "else case");
      updateObjects();
    }
  } // eof initObjects

  /**
   * This method is to initialize listeners
   */
//  private void initListeners() {
//    btnTransaction.setOnClickListener((Button.OnClickListener) this);
//  }

  private void updateObjects(){
    // BACK
    month = databaseHelper.getMonth(user.getCurrMonth());
    period = databaseHelper.getPeriod(month.getPeriodID());
    expected = databaseHelper.getExpected(month.getExpectedID());

    // FRONT
    tvMonth.setText(month.getMonth());
    tvYear.setText(String.valueOf(month.getYear()));
    Log.i("updateObjects", "period.getBalance(): " + period.getBalance());
    
    if (expected.getMonthlyIncome() > 0){
      edtIncomeAmnt.setEnabled(false);
      edtIncomeAmnt.setText("$ " + expected.getMonthlyIncome());
      llIncome.setBackgroundResource(R.drawable.income_radius);
      tvConfirmIncome.setTextSize(20);
      tvConfirmIncome.setText("✔");
    }
    if (expected.getMonthlyOutcome() > 0){
      edtOutcomeAmnt.setEnabled(false);
      edtOutcomeAmnt.setText("$ " + expected.getMonthlyOutcome());
      llOutcome.setBackgroundResource(R.drawable.outcome_radius);
      tvConfirmOutcome.setTextSize(20);
      tvConfirmOutcome.setText("✔");
    }

    if (period.getBalance() == -16){
      tvCurrBalance.setText("$ ?");
      tvDiffBalance.setText("· ?");
    }
    else {
      tvCurrBalance.setText("$ " + String.valueOf(period.getBalance()));
      DiffBal = expected.getBalance() - period.getBalance();
      if(DiffBal < 0){
        tvDiffBalance.setText("" + DiffBal);
      }
      else{
        tvDiffBalance.setText("+" + DiffBal);
      }
    }
  }

  /* Custom Methods */
  public void setIncome(View view) {
    String value = edtIncomeAmnt.getText().toString();
    int income = Integer.parseInt(value);

    if(income <= 0){
      Toast.makeText(this, "Debe ingresar un monto positivo", Toast.LENGTH_SHORT).show();
    }
    else{
      //BACK
      expected.setMonthlyIncome(income);
      expected.setBalance(expected.getBalance() + income);
      databaseHelper.updateExpected(expected);
      updateObjects();
    }
  }

  public void setOutcome(View view) {
    String value = edtOutcomeAmnt.getText().toString();
    int outcome = Integer.parseInt(value);

    if(outcome <= 0){
      Toast.makeText(this, "Debe ingresar un monto positivo", Toast.LENGTH_SHORT).show();
    }
    else{
      //BACK
      expected.setMonthlyOutcome(outcome);
      expected.setBalance(expected.getBalance() - outcome);
      databaseHelper.updateExpected(expected);
      updateObjects();
    }
  }

  public void startTransaction(View view) {
    Intent transactionIntent = new Intent(activity, TransactionActivity.class);
    transactionIntent.putExtra("USERID", String.valueOf(user.getId()));
    transactionIntent.putExtra("MONTHID", String.valueOf(month.getId()));
    transactionIntent.putExtra("EXPECTEDID", String.valueOf(expected.getId()));
    transactionIntent.putExtra("PERIODID", String.valueOf(period.getId()));
    startActivity(transactionIntent);
  }

}
