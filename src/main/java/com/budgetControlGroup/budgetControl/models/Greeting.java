package com.budgetControlGroup.budgetControl.Models;


public class Greeting {

  private final long id;
  private final String content;

  public Greeting(long id, String content) {
    this.id = id;
    this.content = content;
    System.out.println("here is greeting");
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}