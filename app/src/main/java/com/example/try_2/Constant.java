package com.example.try_2;

import java.util.ArrayList;

public class Constant {

  private static ArrayList<Task> arraylist;

  public static Task staticTask = new Task();

  static {

    setArraylist(new ArrayList<Task>());
  }

  public static ArrayList<Task> getArraylist() {
    return arraylist;
  }

  public static void setArraylist(ArrayList<Task> arraylist) {
    Constant.arraylist = arraylist;
  }

  private static float bmi_value;

  public static float getBmi_value() {
    return bmi_value;
  }

  public static void setBmi_value(float bmi_value) {
    Constant.bmi_value = bmi_value;
  }
}
