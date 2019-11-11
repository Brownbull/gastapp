package com.utilone.gastapp.model;

public class Period {
  private long id;
  private int balance;
  private int transactions;

  public Period() {
    this.id = -16;
    this.balance = 0;
    this.transactions = 0;
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

  public int getTransactions() {
    return transactions;
  }
  public void setTransactions(int transactions) {
      this.transactions = transactions;
  }
}