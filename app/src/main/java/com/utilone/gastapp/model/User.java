package com.utilone.gastapp.model;

/**
 * Created by lalit on 9/12/2016 -> Modified by Brownbull 11/09/2019.
 */
public class User {
  private long id;
  private String name;
  private String email;
  private String password;
  private long currmonthID;

  public User() {
    this.id = -16;
    this.name = "";
    this.email = "";
    this.password = "";
    this.currmonthID = -16;
  }

  public User(long id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.currmonthID = -16;
  }

  public User(long id, String name, String email, String password, int currmonthID) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.currmonthID = currmonthID;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
      this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
      this.name = name;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
      this.email = email;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public long getCurrMonth() {
    return currmonthID;
  }
  public void setCurrMonth(long currmonthID) {
      this.currmonthID = currmonthID;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", currmonthID=" + currmonthID +
            '}';
  }
}