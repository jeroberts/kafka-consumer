package com.jonny.kafkaconsumer.model;

public class Person {

  private String name;
  private String dateOfBirth;
  private String email;

  public Person() {}

  public Person(String name, String dateOfBirth, String email) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
