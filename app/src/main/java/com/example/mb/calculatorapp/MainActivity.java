package com.example.mb.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button one, two, three, four, five, six, seven, eight, nine, zero, equal, cancel, add, sub, mul, div, advance;
    TextView mTextView, historyTextView;
    Calculator mCalculator;
    Boolean isAdvance = false;

    //ArrayAdapter<String> itemsAdapter;
    //   ArrayList<String> histories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setClickListeners();
    }

    private void setClickListeners() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        equal.setOnClickListener(this);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        advance.setOnClickListener(this);

    }

    private void initViews() {
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        four = findViewById(R.id.button4);
        five = findViewById(R.id.button5);
        six = findViewById(R.id.button6);
        seven = findViewById(R.id.button7);
        eight = findViewById(R.id.button8);
        nine = findViewById(R.id.button9);
        zero = findViewById(R.id.button0);
        equal = findViewById(R.id.buttonEnter);
        cancel = findViewById(R.id.buttonClear);
        add = findViewById(R.id.buttonAdd);
        sub = findViewById(R.id.buttonSub);
        mul = findViewById(R.id.buttonMultiply);
        div = findViewById(R.id.buttonDiv);
        advance = findViewById(R.id.versionChangeButton);
        mTextView = findViewById(R.id.textview);
        historyTextView = findViewById(R.id.historyTextView);
        mCalculator = new Calculator();
        // histories=new ArrayList<>();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        String myText = mTextView.getText().toString();
        Button clickedButton = (Button) view;
        String newText = clickedButton.getText().toString();
        switch (view.getId()) {
            case R.id.buttonClear:
                mTextView.setText("");
                mCalculator.emptyList();
                mTextView.setTextColor(getResources().getColor(R.color.black));
                break;

            case R.id.buttonEnter:
                enterButtonClicked(myText, newText);
                break;

            case R.id.versionChangeButton:
                changeVersion();
                mCalculator.emptyList();
                break;

            default:
                mCalculator.push(newText);

                mTextView.setText(String.format("%s %s", myText, newText));
                checkValue();
                //  checkValidation(((Button) view).getText().toString());
        }
        //checkValidation(mTextView.getText().toString());
    }

    private void checkValue() {
        ArrayList<String> textList = mCalculator.getTextList();
        for (int i = 0; i < textList.size(); i++) {
            if (i > 0) {
                if (textList.get(i).equals("+") || textList.get(i).equals("-")
                        || textList.get(i).equals("*") || textList.get(i).equals("/")) {
                    if (textList.get(i - 1).equals("+") || textList.get(i - 1).equals("-")
                            || textList.get(i - 1).equals("*") || textList.get(i - 1).equals("/")) {
                        Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show();
                        mTextView.setTextColor(getResources().getColor(R.color.red));
                    }

                }
                if (textList.get(i).equals("1") || textList.get(i).equals("2")
                        || textList.get(i).equals("3") || textList.get(i).equals("4")
                        || textList.get(i).equals("5") || textList.get(i).equals("6")
                        || textList.get(i).equals("7") || textList.get(i).equals("8")
                        || textList.get(i).equals("9") || textList.get(i).equals("0")

                ) {
                    if (textList.get(i-1).equals("1") || textList.get(i-1).equals("2")
                            || textList.get(i-1).equals("3") || textList.get(i-1).equals("4")
                            || textList.get(i-1).equals("5") || textList.get(i-1).equals("6")
                            || textList.get(i-1).equals("7") || textList.get(i-1).equals("8")
                            || textList.get(i-1).equals("9") || textList.get(i-1).equals("0")

                    ) {
                        mTextView.setTextColor(getResources().getColor(R.color.red));

                        Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }

    }

    private void enterButtonClicked(String myText, String newText) {
        //     histories.clear();
        String result = mCalculator.calculate();
        mTextView.setText(String.format("%s %s %s", myText, newText, result));
        //   mCalculator.addHistory(mTextView.getText().toString());
        //      histories=mCalculator.getHistoryList();
        String s1 = historyTextView.getText().toString();
        historyTextView.setText(String.format("%s\n%s", s1, mTextView.getText().toString()));
        mCalculator.emptyList();
        /*for (int i = 0; i < histories.size(); i++) {
            historyTextView.setText(histories.get(i)+"\n");
        }*/
        // listView.setAdapter(itemsAdapter);

    }


    private void changeVersion() {
        mTextView.setText("");
        //  setClickableButtons();
        if (!isAdvance) {
            advance.setText(R.string.standard_no_history);
            advance.setBackgroundColor(getResources().getColor(R.color.light_grey1));
            historyTextView.setVisibility(View.VISIBLE);
            historyTextView.setText("");
            isAdvance = true;
        } else {
            isAdvance = false;
            advance.setText(R.string.advance_with_history);
            advance.setBackgroundColor(getResources().getColor(R.color.light_purple));
            historyTextView.setVisibility(View.GONE);

        }

    }

    private void setClickableButtons() {
        add.setClickable(false);
        sub.setClickable(false);
        mul.setClickable(false);
        div.setClickable(false);
        equal.setClickable(false);

        one.setClickable(true);
        two.setClickable(true);
        three.setClickable(true);
        four.setClickable(true);
        five.setClickable(true);
        six.setClickable(true);
        seven.setClickable(true);
        eight.setClickable(true);
        nine.setClickable(true);
        zero.setClickable(true);
    }

    private void checkValidation(String text) {
        if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
            add.setClickable(false);
            sub.setClickable(false);
            mul.setClickable(false);
            div.setClickable(false);
            equal.setClickable(false);

            one.setClickable(true);
            two.setClickable(true);
            three.setClickable(true);
            four.setClickable(true);
            five.setClickable(true);
            six.setClickable(true);
            seven.setClickable(true);
            eight.setClickable(true);
            nine.setClickable(true);
            zero.setClickable(true);


        } else if (text.equals("1") || text.equals("2") || text.equals("3") || text.equals("4")
                || text.equals("5") || text.equals("6") || text.equals("7") || text.equals("8")
                || text.equals("9") || text.equals("0")) {
            one.setClickable(false);
            two.setClickable(false);
            three.setClickable(false);
            four.setClickable(false);
            five.setClickable(false);
            six.setClickable(false);
            seven.setClickable(false);
            eight.setClickable(false);
            nine.setClickable(false);
            zero.setClickable(false);

            add.setClickable(true);
            sub.setClickable(true);
            mul.setClickable(true);
            div.setClickable(true);
            equal.setClickable(true);
        } else {
            add.setClickable(false);
            sub.setClickable(false);
            mul.setClickable(false);
            div.setClickable(false);
            equal.setClickable(false);

            one.setClickable(true);
            two.setClickable(true);
            three.setClickable(true);
            four.setClickable(true);
            five.setClickable(true);
            six.setClickable(true);
            seven.setClickable(true);
            eight.setClickable(true);
            nine.setClickable(true);
            zero.setClickable(true);
        }
    }
}