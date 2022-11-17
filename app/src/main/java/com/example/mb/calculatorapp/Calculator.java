package com.example.mb.calculatorapp;

import java.util.ArrayList;

class Calculator {
    ArrayList<String> textList = new ArrayList<>();
   // ArrayList<String> historyList = new ArrayList<>();
    String result = "";
    int value = 0;

    public void push(String value) {
        textList.add(value);
    }
    public ArrayList<String> getTextList(){
        return textList;
    }
    public void emptyList() {
        textList.clear();
      //  historyList.clear();
    }

  //  public ArrayList<String> getHistoryList() {
   //     return historyList;
  //  }

   // public void addHistory(String value) {
    //    historyList.add(value);
    //}

    public String calculate() {

        for (int i = 0; i < textList.size(); i++) {
            if (textList.get(i).equals("+") || textList.get(i).equals("-")
                    || textList.get(i).equals("*") || textList.get(i).equals("/")) {
                int n1 = Integer.parseInt(textList.get(0));
                int n2 = Integer.parseInt(textList.get(i + 1));
                if (i == 1) {
                    value = doOperation(n1, n2, textList.get(i));
                } else {
                    value = doOperation(value, n2, textList.get(i));
                }
                result = String.valueOf(value);
            }
        }
        textList.clear();
        return result;

    }

    private int doOperation(int n1, int n2, String opr) {

        switch (opr) {
            case "+":
                value = n1 + n2;
                break;
            case "-":
                value = n1 - n2;
                break;
            case "*":
                value = n1 * n2;
                break;
            case "/":
                value = n1 / n2;
                break;
        }
        return value;
    }
}
