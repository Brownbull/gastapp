package com.utilone.gastapp.model;

public class Expected {
  private long id;
  private int balance;
  private int monthlyIncome;
  private int monthlyOutcome;

  public Expected() {
    this.id = -16;
    this.balance = 0;
    this.monthlyIncome = 0;
    this.monthlyOutcome = 0;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
      this.id = id;
  }

  public int getBalance() {
    return balance;
  }
  public void setBalance(int balance) {
      this.balance = balance;
  }

  public int getMonthlyIncome() {
    return monthlyIncome;
  }
  public void setMonthlyIncome(int monthlyIncome) {
      this.monthlyIncome = monthlyIncome;
  }

  public int getMonthlyOutcome() {
    return monthlyOutcome;
  }
  public void setMonthlyOutcome(int monthlyOutcome) {
      this.monthlyOutcome = monthlyOutcome;
  }
}
