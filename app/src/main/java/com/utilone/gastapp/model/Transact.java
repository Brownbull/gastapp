package com.utilone.gastapp.model;

public class Transact {
  private int id;
  private int transactID;
  private int periodID;
  private int typeID;
  private String transactDate;
  private int amount;
  private int categoryID1;
  private int categoryID2;
  private int categoryID3;

  public int getId() {
    return id;
  }
  public void setId(int id) {
      this.id = id;
  }

  public int getTransactID() {
    return transactID;
  }
  public void setTransactID(int transactID) {
    this.transactID = transactID;
  }

  public int getPeriodID() {
    return periodID;
  }
  public void setPeriodID(int periodID) {
    this.periodID = periodID;
  }

  public int getTypeID() {
    return typeID;
  }
  public void setTypeID(int typeID) {
    this.typeID = typeID;
  }

  public String getTransactDate() {
    return transactDate;
  }
  public void setTransactDate(String transactDate) {
    this.transactDate = transactDate;
  }

  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getCategoryID1() {
    return categoryID1;
  }
  public void setCategoryID1(int categoryID1) {
    this.categoryID1 = categoryID1;
  }

  public int getCategoryID2() {
    return categoryID2;
  }
  public void setCategoryID2(int categoryID2) {
    this.categoryID2 = categoryID2;
  }

  public int getCategoryID3() {
    return categoryID3;
  }
  public void setCategoryID3(int categoryID3) {
    this.categoryID3 = categoryID3;
  }
}
