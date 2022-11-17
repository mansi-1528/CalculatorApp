package com.example.mb.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button one, two, three, four, five, six, seven, eight, nine, zero, equal, cancel, add, sub, mul, div, advance;
    TextView mTextView, historyTextView;
    Calculator mCalculator;
    Boolean isAdvance = false;

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
        historyTextView.setMovementMethod(new ScrollingMovementMethod());

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
                mTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
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
        }

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
                        //mTextView.setTextColor(getResources().getColor(R.color.red));
                        mTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.red));
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
                        mTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.red));

                        Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }

    }

    private void enterButtonClicked(String myText, String newText) {
        String result = mCalculator.calculate();
        mTextView.setText(String.format("%s %s %s", myText, newText, result));
        String s1 = historyTextView.getText().toString();
        historyTextView.setText(String.format("%s\n%s", s1, mTextView.getText().toString()));
        mCalculator.emptyList();

    }


    private void changeVersion() {
        mTextView.setText("");
        //  setClickableButtons();
        if (!isAdvance) {
            advance.setText(R.string.standard_no_history);
            mTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.light_grey1));
            historyTextView.setVisibility(View.VISIBLE);
            historyTextView.setText("");
            isAdvance = true;
        } else {
            isAdvance = false;
            advance.setText(R.string.advance_with_history);
            mTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.light_purple));
            historyTextView.setVisibility(View.GONE);

        }

    }
}