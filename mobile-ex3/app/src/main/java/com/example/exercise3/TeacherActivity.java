package com.example.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity {

    private static final int RESULT_CODE_TEACHER = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        EditText textToReview = findViewById(R.id.textToReview);
        Button sendBackButton = findViewById(R.id.sendBackButton);

        String studentText = getIntent().getStringExtra("studentText");
        String correctedText = correctText(studentText);
        textToReview.setText(correctedText);

        sendBackButton.setOnClickListener(v -> {
            String finalText = textToReview.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("correctedText", finalText);
            setResult(RESULT_CODE_TEACHER, resultIntent); // Code 33
            finish(); // Quay về cho học sinh
        });
    }

    private String correctText(String text) {
        if (text == null || text.trim().isEmpty()) return "Không có nội dung.";

        text = text.trim().replaceAll("\\s+", " ");
        String[] lienTu = { "và", "nhưng", "nên", "vì", "tuy", "dù", "mặc dù" };
        for (String tu : lienTu) {
            text = text.replaceAll(" (?i)" + tu + " ", ", " + tu + " ");
        }

        String[] sentences = text.split("(?<=[.!?])\\s*");
        StringBuilder corrected = new StringBuilder();
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.isEmpty()) continue;
            sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
            corrected.append(sentence).append(" ");
        }

        String result = corrected.toString().trim();
        if (!result.endsWith(".") && !result.endsWith("!") && !result.endsWith("?")) {
            result += ".";
        }

        return result;
    }
}