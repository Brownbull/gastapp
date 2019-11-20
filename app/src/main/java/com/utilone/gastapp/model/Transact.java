package com.utilone.gastapp.model;

public class Transact {
  private long id;
  private long periodID;
  private String type;
  private int transactDay;
  private int amount;
  private String category;
  private String desc;

  public Transact() {
    this.id = -16;
    this.periodID = -16;
    this.type = "Outcome";
    this.transactDay = 0;
    this.amount = 0;
    this.category = "";
    this.desc = "";
  }

  public Transact(long periodID) {
    this.id = -16;
    this.periodID = periodID;
    this.type = "Outcome";
    this.transactDay = 0;
    this.amount = 0;
    this.category = "";
    this.desc = "";
  }

  public Transact(long id, long periodID,  String type,  int transactDay,  int amount,  String category,  String desc){
    this.id = id;
    this.periodID = periodID;
    this.type = type;
    this.transactDay = transactDay;
    this.amount = amount;
    this.category = category;
    this.desc = desc;
  }

  public Transact(long periodID,  String type,  int transactDay,  int amount,  String category,  String desc){
    this.id = -16;
    this.periodID = periodID;
    this.type = type;
    this.transactDay = transactDay;
    this.amount = amount;
    this.category = category;
    this.desc = desc;
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

  public int getTransactDay() {
    return transactDay;
  }
  public String getTransactDayStr() {
    return String.valueOf(transactDay);
  }
  public void setTransactDay(int transactDay) {
    this.transactDay = transactDay;
  }

  public int getAmount() {
    return amount;
  }
  public String getAmountStr() {
    return String.valueOf(amount);
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

  @Override
  public String toString() {
    return "Transact{" +
        "id=" + id +
        ", periodID=" + periodID +
        ", type='" + type + '\'' +
        ", transactDay=" + transactDay +
        ", amount=" + amount +
        ", category='" + category + '\'' +
        ", desc='" + desc + '\'' +
        '}';
  }
}
