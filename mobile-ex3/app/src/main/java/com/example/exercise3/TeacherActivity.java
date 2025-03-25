package com.example.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        TextView textToReview = findViewById(R.id.textToReview);
        Button sendBackButton = findViewById(R.id.sendBackButton);

        String studentText = getIntent().getStringExtra("studentText");
        String correctedText = correctText(studentText);
        textToReview.setText(correctedText);

        sendBackButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("correctedText", correctedText);
            setResult(RESULT_OK, resultIntent);
            finish(); // return to student
        });
    }

    private String correctText(String text) {
        if (text == null || text.trim().isEmpty()) return "Không có nội dung.";

        // Chuẩn hoá khoảng trắng
        text = text.trim().replaceAll("\\s+", " ");

        // Thêm dấu phẩy sau một số liên từ tiếng Việt (nếu chưa có)
        String[] lienTu = { "và", "nhưng", "nên", "vì", "tuy", "dù", "mặc dù" };
        for (String tu : lienTu) {
            text = text.replaceAll(" (?i)" + tu + " ", ", " + tu + " ");
        }

        // Viết hoa chữ cái đầu câu
        String[] sentences = text.split("(?<=[.!?])\\s*");
        StringBuilder corrected = new StringBuilder();
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.isEmpty()) continue;

            // Viết hoa chữ cái đầu
            sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
            corrected.append(sentence).append(" ");
        }

        // Thêm dấu chấm cuối nếu thiếu
        String result = corrected.toString().trim();
        if (!result.endsWith(".") && !result.endsWith("!") && !result.endsWith("?")) {
            result += ".";
        }

        return result;
    }
}
