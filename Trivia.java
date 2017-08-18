package com.visa.ncg.canteen;

public class Trivia {
  private String text;
  private int number;
  private boolean found;
  private String type;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public boolean isFound() {
    return found;
  }

  public void setFound(boolean found) {
    this.found = found;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Trivia{" +
        "text='" + text + '\'' +
        ", number=" + number +
        ", found=" + found +
        ", type='" + type + '\'' +
        '}';
  }
}
