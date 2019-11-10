package com.utilone.gastapp.model;

public class Month {
  private int id;
  private int userID;
  private int periodID;
  private int expectedID;
  private String month;
  private int year;
  private int balDiff;

  public Month() {
    this.id = -16;
    this.userID = -16;
    this.periodID = -16;
    this.expectedID = -16;
    this.month = "";
    this.year = -16;
    this.balDiff = -16;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
      this.id = id;
  }

  public int getUserID() {
    return userID;
  }
  public void setUserID(int userID) {
      this.userID = userID;
  }

  public int getPeriodID() {
    return periodID;
  }
  public void setPeriodID(int periodID) {
      this.periodID = periodID;
  }

  public int getExpectedID() {
    return expectedID;
  }
  public void setExpectedID(int expectedID) {
      this.expectedID = expectedID;
  }

  public String getMonth() {
    return month;
  }
  public void setMonth(String month) {
      this.month = month;
  }

  public int getYear() {
    return year;
  }
  public void setYear(int year) {
      this.year = year;
  }

  public int getBalDiff() {
    return balDiff;
  }
  public void setBalDiff(int balDiff) {
      this.balDiff = balDiff;
  }
}