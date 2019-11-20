package com.utilone.gastapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.utilone.gastapp.R;
import com.utilone.gastapp.adapters.TransactRecyclerAdapter;
import com.utilone.gastapp.model.Expected;
import com.utilone.gastapp.model.Month;
import com.utilone.gastapp.model.NameMonth;
import com.utilone.gastapp.model.Period;
import com.utilone.gastapp.model.Transact;
import com.utilone.gastapp.model.User;
import com.utilone.gastapp.sql.DatabaseHelper;

import java.util.ArrayList;
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
  String cmonthName;
  private User user;
  private Month month;
  private Expected expected;
  private Period period;
  private Transact transact;
  private NameMonth nameMonth;
  private RecyclerView recyclerViewIncomes;
  private RecyclerView recyclerViewOutcomes;
  private List<Transact> listTransact;
  private List<Transact> listTransactIns;
  private List<Transact> listTransactOuts;
  private TransactRecyclerAdapter transactRecyclerAdapterIns;
  private TransactRecyclerAdapter transactRecyclerAdapterOuts;
  String[] monthNames;
  ArrayAdapter<String> adapterMonths;
  
  // FRONTEND
  // private TextView tvMonth;
  private TextView tvYear;
  private TextView tvCurrBalance;
  private TextView tvDiffBalance;
  private LinearLayout llIncome;
  private LinearLayout llins;
  private EditText edtIncomeAmnt;
//  private TextView tvConfirmIncome;
  private LinearLayout llOutcome;
  private LinearLayout llouts;
  private EditText edtOutcomeAmnt;
//  private TextView tvConfirmOutcome;
  private Button btnTransaction;
  private Spinner spinnerMonths;

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

  @Override
  protected void onResume() {
    super.onResume();
    if (user.getCurrMonth() != -16){
      Log.i("onResume", "USER" + user.toString());
      Log.i("onResume", "MONTH" + month.toString());
      updateObjects();
    }
  }

  /**
   * This method is to initialize views
   */
  private void initViews() {
    // tvMonth = (TextView) findViewById(R.id.tv_month);
    tvYear = (TextView) findViewById(R.id.tv_year);
    tvCurrBalance = (TextView) findViewById(R.id.tv_CurrBalance);
    tvDiffBalance = (TextView) findViewById(R.id.tv_DiffBalance);
    llIncome = (LinearLayout) findViewById(R.id.ll_income);
    llins = (LinearLayout) findViewById(R.id.llins);
    edtIncomeAmnt = (EditText) findViewById(R.id.edt_income_amnt);
//    tvConfirmIncome = (TextView) findViewById(R.id.tv_confirm_income);
    llOutcome = (LinearLayout) findViewById(R.id.ll_outcome);
    llouts = (LinearLayout) findViewById(R.id.llouts);
    edtOutcomeAmnt = (EditText) findViewById(R.id.edt_outcome_amnt);
//    tvConfirmOutcome = (TextView) findViewById(R.id.tv_confirm_outcome);
    recyclerViewIncomes = (RecyclerView) findViewById(R.id.recyclerViewIncomes);
    recyclerViewOutcomes = (RecyclerView) findViewById(R.id.recyclerViewOutcomes);
    spinnerMonths = (Spinner) findViewById(R.id.spinner_months);

    spinnerMonths.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        // your code here
        ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLUE);
        ((TextView) parentView.getChildAt(0)).setTextSize(20);
        Log.i("onItemSelected", "INIT FUNC");
        Log.i("onItemSelected", "id: " + id);
        Log.i("initViews", "onItemSelected USER PRE" + user.toString());
        cmonthName = nameMonthList.get((int)id).getDesc();
        Log.i("onItemSelected", "cmonthName: " + cmonthName);
        month = databaseHelper.getOrAddMonth(userID, cmonthName, cyear);
        user.setCurrMonth(month.getId());
        databaseHelper.updateUser(user);
        Log.i("initViews", "onItemSelected USER POST" + user.toString());
        Log.i("initViews", "onItemSelected MONTH" + month.toString());
        // initObjects(userID);
        updateObjects();
        Log.i("onItemSelected", "END FUNC");
      }

      @Override
      public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
        Log.i("initViews", "onNothingSelected");
      }

    });
  }

  /**
   * This method is to initialize objects to be used
   */
  private void initObjects(long userID) {
    databaseHelper = new DatabaseHelper(activity);
    nameMonthList = databaseHelper.getAllNameMonth();
    user = databaseHelper.getUser(userID);
    transact = new Transact();

    monthNames = databaseHelper.getAllNameMonthStrings();
    adapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monthNames);
    spinnerMonths.setAdapter(adapterMonths);

    
    // NEW USER
    if (user.getCurrMonth() == -16){
      Log.i("initObjects", "NEW USER");
      cmonthName = nameMonthList.get(cmonth + 1).getDesc();
      month = databaseHelper.addMonth(userID, cmonthName, cyear);
      user.setCurrMonth(month.getId());
      databaseHelper.updateUser(user);
      updateObjects(); 
    }
    // OLD USER
    else {
      Log.i("initObjects", "OLD USER");
      updateObjects();
    }
  } // eof initObjects

  private void initMonth(long userID, long monthID) {
    nameMonthList = databaseHelper.getAllNameMonth();
    cmonthName = nameMonthList.get(((int)monthID) ).getDesc();
    month = databaseHelper.addMonth(userID, cmonthName, cyear);
    user.setCurrMonth(month.getId());
    databaseHelper.updateUser(user);
    updateObjects(); 
  } // eof initMonth

  private void updateObjects(){
    Log.i("updateObjects", "INIT FUNCTION");
    // BACK
    month = databaseHelper.getMonth(user.getCurrMonth());
    Log.i("updateObjects", "MONTH: " + month.toString());
    // period = databaseHelper.getPeriod(month.getPeriodID());
    period = databaseHelper.updatePeriodAmnts(month.getPeriodID());
    Log.i("updateObjects", "PERIOD: " + period.toString());
    expected = databaseHelper.getExpected(month.getExpectedID());
    
    int spinnerPosition = adapterMonths.getPosition(month.getMonth());
    
    //set the default according to value
    spinnerMonths.setSelection(spinnerPosition);
    
    // FRONT
    // tvMonth.setText(month.getMonth());
    tvYear.setText(String.valueOf(month.getYear()));
    
    edtIncomeAmnt.setEnabled(false);

    edtIncomeAmnt.setText("$ " + period.getIncomes());
    llIncome.setBackgroundResource(R.drawable.income_radius);

    edtOutcomeAmnt.setEnabled(false);
    edtOutcomeAmnt.setText("$ " + period.getOutcomes());
    llOutcome.setBackgroundResource(R.drawable.outcome_radius);

    if (period.getBalance() == -16){
      tvCurrBalance.setText("$ ?");
      tvDiffBalance.setText("· ?");
    }
    else {
      tvCurrBalance.setText("$ " + String.valueOf(period.getBalance()));
      // DiffBal = expected.getBalance() - period.getBalance();
      DiffBal = 0;
      if(DiffBal < 0){
        tvDiffBalance.setText("" + DiffBal);
      }
      else{
        tvDiffBalance.setText("+" + DiffBal);
      }
    }

    llins.setVisibility(LinearLayout.GONE);
    llouts.setVisibility(LinearLayout.GONE);

    listTransact = new ArrayList<>();

    listTransactIns = new ArrayList<>();
    transactRecyclerAdapterIns = new TransactRecyclerAdapter(listTransactIns);

    RecyclerView.LayoutManager mLayoutManagerIns = new LinearLayoutManager(getApplicationContext());
    recyclerViewIncomes.setLayoutManager(mLayoutManagerIns);
    recyclerViewIncomes.setItemAnimator(new DefaultItemAnimator());
    recyclerViewIncomes.setHasFixedSize(true);
    recyclerViewIncomes.setAdapter(transactRecyclerAdapterIns);

    recyclerViewIncomes.addOnItemTouchListener(
    new RecyclerItemClickListener(getApplicationContext(), recyclerViewIncomes ,new RecyclerItemClickListener.OnItemClickListener() {
      @Override public void onItemClick(View view, int position) {
        // do whatever
        Log.i("RecyclerItemClickListener", "onItemClick position " + position);
        Log.i("RecyclerItemClickListener", "onItemClick listTransactIns " + listTransactIns.get(position).toString());
        startTransaction(view, listTransactIns.get(position));

      }
      @Override public void onLongItemClick(View view, int position) {
        // do whatever
        Log.i("RecyclerItemClickListener", "onLongItemClick position " + position);
      }
    }));

    listTransactOuts = new ArrayList<>();
    transactRecyclerAdapterOuts = new TransactRecyclerAdapter(listTransactOuts);

    RecyclerView.LayoutManager mLayoutManagerOuts = new LinearLayoutManager(getApplicationContext());
    recyclerViewOutcomes.setLayoutManager(mLayoutManagerOuts);
    recyclerViewOutcomes.setItemAnimator(new DefaultItemAnimator());
    recyclerViewOutcomes.setHasFixedSize(true);
    recyclerViewOutcomes.setAdapter(transactRecyclerAdapterOuts);

    recyclerViewOutcomes.addOnItemTouchListener(
      new RecyclerItemClickListener(getApplicationContext(), recyclerViewIncomes ,new RecyclerItemClickListener.OnItemClickListener() {
        @Override public void onItemClick(View view, int position) {
          // do whatever
          Log.i("RecyclerItemClickListener", "onItemClick position " + position);
          Log.i("RecyclerItemClickListener", "onItemClick listTransactOuts " + listTransactOuts.get(position).toString());
          startTransaction(view, listTransactOuts.get(position));
  
        }
        @Override public void onLongItemClick(View view, int position) {
          // do whatever
          Log.i("RecyclerItemClickListener", "onLongItemClick position " + position);
        }
      }));
  

    getDataFromSQLite(period.getTransactions());

    Log.i("updateObjects", "END FUNCTION");

  }

  /**
   * This method is to fetch all user records from SQLite
   */
  private void getDataFromSQLite(final int transactCounter) {
    // AsyncTask is used that SQLite operation not blocks the UI Thread.
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... params) {
        listTransact.clear();
        listTransactIns.clear();
        listTransactOuts.clear();
        if ( transactCounter > 0) {
          // listTransact.addAll(databaseHelper.getAllTransact(user.getCurrMonth()));
          // for (int counter = 0; counter < listTransact.size(); counter++) { 		      
          //   // System.out.println(listTransact.get(counter));
          //   if (listTransact.get(counter).getType().equals("Income")){
          //     listTransactIns.add(listTransact.get(counter));
          //   }
          //   else if (listTransact.get(counter).getType().equals("Outcome")){
          //     listTransactOuts.add(listTransact.get(counter));
          //   }
          // }   
          listTransactIns.addAll(databaseHelper.getAllTransact(user.getCurrMonth(), "Income"));
          listTransactOuts.addAll(databaseHelper.getAllTransact(user.getCurrMonth(), "Outcome"));
        }
        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        transactRecyclerAdapterIns.notifyDataSetChanged();
        transactRecyclerAdapterOuts.notifyDataSetChanged();
      }
    }.execute();
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
    transactionIntent.putExtra("MONTHNAME", month.getMonth());
    Log.i("startTransaction", "MONTH: " + month.toString());
    transactionIntent.putExtra("EXPECTEDID", String.valueOf(expected.getId()));

    transactionIntent.putExtra("TRANSACTID", String.valueOf(transact.getId()));
    transactionIntent.putExtra("PERIODID", String.valueOf(period.getId()));
    transactionIntent.putExtra("TRANSACTTYPE", String.valueOf(transact.getType()));
    transactionIntent.putExtra("TRANSACTDAY", String.valueOf(transact.getTransactDay()));
    transactionIntent.putExtra("TRANSACTAMNT", String.valueOf(transact.getAmount()));
    transactionIntent.putExtra("TRANSACTCATEGORY", String.valueOf(transact.getCategory()));
    transactionIntent.putExtra("TRANSACTDESC", String.valueOf(transact.getDesc()));

    startActivity(transactionIntent);
  }

  public void startTransaction(View view, Transact transact) {
    Intent transactionIntent = new Intent(activity, TransactionActivity.class);
    transactionIntent.putExtra("USERID", String.valueOf(user.getId()));
    transactionIntent.putExtra("MONTHID", String.valueOf(month.getId()));
    transactionIntent.putExtra("MONTHNAME", month.getMonth());
    Log.i("startTransaction", "MONTH: " + month.toString());
    transactionIntent.putExtra("EXPECTEDID", String.valueOf(expected.getId()));
    
    transactionIntent.putExtra("TRANSACTID", String.valueOf(transact.getId()));
    transactionIntent.putExtra("PERIODID", String.valueOf(period.getId()));
    transactionIntent.putExtra("TRANSACTTYPE", String.valueOf(transact.getType()));
    transactionIntent.putExtra("TRANSACTDAY", String.valueOf(transact.getTransactDay()));
    transactionIntent.putExtra("TRANSACTAMNT", String.valueOf(transact.getAmount()));
    transactionIntent.putExtra("TRANSACTCATEGORY", String.valueOf(transact.getCategory()));
    transactionIntent.putExtra("TRANSACTDESC", String.valueOf(transact.getDesc()));

    startActivity(transactionIntent);
  }


  public void toggleIns(View view){
    Log.i("toggleIns", "flag: " + llins.getVisibility());
    if (llins.getVisibility() == 0){
      llins.setVisibility(LinearLayout.GONE);
    }
    else{
      llins.setVisibility(LinearLayout.VISIBLE);
    }
  }

  public void toggleOuts(View view){
    Log.i("toggleIns", "flag: " + llouts.getVisibility());
    if (llouts.getVisibility() == 0){
      llouts.setVisibility(LinearLayout.GONE);
    }
    else{
      llouts.setVisibility(LinearLayout.VISIBLE);
    }
  }
}
