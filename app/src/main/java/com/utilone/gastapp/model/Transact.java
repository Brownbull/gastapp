package com.utilone.gastapp.model;

public class Transact {
  private long id;
  private long periodID;
  private String type;
  private String transactDate;
  private int amount;
  private String category;
  private String desc;

  public Transact() {
    this.id = -16;
    this.periodID = -16;
    this.type = "Outcome";
    this.transactDate = "";
    this.amount = 0;
    this.category = "";
    this.desc = "";
  }

  public Transact(long periodID) {
    this.id = -16;
    this.periodID = periodID;
    this.type = "Outcome";
    this.transactDate = "";
    this.amount = 0;
    this.category = "";
    this.desc = "";
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
      this.id = id;
  }

  public long getPeriodID() {
    return periodID;
  }
  public void setPeriodID(long periodID) {
    this.periodID = periodID;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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

  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }

  public String getDesc() {
    return desc;
  }
  public void setDesc(String desc) {
    this.desc = desc;
  }

}
