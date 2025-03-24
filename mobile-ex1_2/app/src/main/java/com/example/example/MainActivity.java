package com.example.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        Button sumButton = findViewById(R.id.sumButton);
        Button clearButton = findViewById(R.id.clearButton);
        resultTextView = findViewById(R.id.resultTextView);

        sumButton.setOnClickListener(v -> calculateSum());
        clearButton.setOnClickListener(v -> clearResult());
    }

    @SuppressLint("SetTextI18n")
    private void clearResult() {
        editText1.setText("");
        editText2.setText("");
        resultTextView.setText("Result: ");
    }

    @SuppressLint("SetTextI18n")
    private void calculateSum() {
        String num1Str = editText1.getText().toString();
        String num2Str = editText2.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            try {
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);
                int sum = num1 + num2;

                resultTextView.setText("Result: " + sum);
            } catch (NumberFormatException e) {
                resultTextView.setText("Invalid input. Please enter valid number");
            }
        } else {
            resultTextView.setText("Please enter both numbers");
        }
    }
}