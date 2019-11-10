package com.utilone.gastapp.model;

public class Period {
  private int id;
  private int balance;
  private int transactions;

  public int getId() {
    return id;
  }
  public void setId(int id) {
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