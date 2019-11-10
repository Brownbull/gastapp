package com.utilone.gastapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.utilone.gastapp.R;

public class PanelActivity extends AppCompatActivity {
  int userID;
  String email;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_panel);
    Intent Login = getIntent();
    userID = Login.getIntExtra("userID", 0);
    email = Login.getStringExtra("email");

    initViews();
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

  /* Custom Methods */
  public void setIncome(View view) {
    String value = edtIncomeAmnt.getText().toString();
    int income = Integer.parseInt(value);

    if(income <= 0){
      Toast.makeText(this, "Debe ingresar un monto positivo", Toast.LENGTH_SHORT).show();
    }
    else{
      edtIncomeAmnt.setEnabled(false);
      llIncome.setBackgroundResource(R.drawable.income_radius);
      tvConfirmIncome.setTextSize(20);
      tvConfirmIncome.setText("✔");
    }
  }

  public void setOutcome(View view) {
    String value = edtOutcomeAmnt.getText().toString();
    int outcome = Integer.parseInt(value);

    if(outcome <= 0){
      Toast.makeText(this, "Debe ingresar un monto positivo", Toast.LENGTH_SHORT).show();
    }
    else{
      edtOutcomeAmnt.setEnabled(false);
      llOutcome.setBackgroundResource(R.drawable.outcome_radius);
      tvConfirmOutcome.setTextSize(20);
      tvConfirmOutcome.setText("✔");
    }
  }






}
