package com.utilone.gastapp.model;

/**
 * Created by lalit on 9/12/2016 -> Modified by Brownbull 11/09/2019.
 */
public class User {
  private int id;
  private String name;
  private String email;
  private String password;

  public User() {
    this.id = -16;
    this.name = "";
    this.email = "";
    this.password = "";
  }

  public User(int id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
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
}