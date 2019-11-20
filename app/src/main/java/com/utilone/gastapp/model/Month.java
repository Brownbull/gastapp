package com.utilone.gastapp.model;

public class Month {
  private long id;
  private long userID;
  private long periodID;
  private long expectedID;
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
    this.balDiff = 0;
  }

  public Month(long id, long userID, long periodID, long expectedID, String month, int year, int balDiff) {
    this.id = id;
    this.userID = userID;
    this.periodID = periodID;
    this.expectedID = expectedID;
    this.month = month;
    this.year = year;
    this.balDiff = balDiff;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
      this.id = id;
  }

  public long getUserID() {
    return userID;
  }
  public void setUserID(long userID) {
      this.userID = userID;
  }

  public long getPeriodID() {
    return periodID;
  }
  public void setPeriodID(long periodID) {
      this.periodID = periodID;
  }

  public long getExpectedID() {
    return expectedID;
  }
  public void setExpectedID(long expectedID) {
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

  @Override
  public String toString() {
    return "Month{" +
            "id=" + id +
            ", userID=" + userID +
            ", periodID=" + periodID +
            ", expectedID=" + expectedID +
            ", month='" + month + '\'' +
            ", year=" + year +
            ", balDiff=" + balDiff +
            '}';
  }
}