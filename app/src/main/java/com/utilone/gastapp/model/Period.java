package com.utilone.gastapp.model;

public class Period {
  private long id;
  private int incomes;
  private int outcomes;
  private int balance;
  private int transactions;

  public Period() {
    this.id = -16;
    this.incomes = 0;
    this.outcomes = 0;
    this.balance = 0;
    this.transactions = 0;
  }

  public Period(long id, int incomes, int outcomes, int balance, int transactions ) {
    this.id = id;
    this.incomes = incomes;
    this.outcomes = outcomes;
    this.balance = balance;
    this.transactions = transactions;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
      this.id = id;
  }

  public int getIncomes() {
    return incomes;
  }
  public void setIncomes(int incomes) {
      this.incomes = incomes;
  }

  public int getOutcomes() {
    return outcomes;
  }
  public void setOutcomes(int outcomes) {
      this.outcomes = outcomes;
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

  @Override
  public String toString() {
    return "Period{" +
            "id=" + id +
            ", incomes=" + incomes +
            ", outcomes=" + outcomes +
            ", balance=" + balance +
            ", transactions=" + transactions +
            '}';
  }
}