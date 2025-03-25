package com.example.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity {
    private EditText editTextStudent;
    private TextView teacherCorrection;
    private static final int REQUEST_CODE_REVIEW = 99;
    private static final int RESULT_CODE_TEACHER = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);

        editTextStudent = findViewById(R.id.editTextStudent);
        teacherCorrection = findViewById(R.id.teacherCorrection);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String input = editTextStudent.getText().toString();
            Intent intent = new Intent(StudentActivity.this, TeacherActivity.class);
            intent.putExtra("studentText", input);
            startActivityForResult(intent, REQUEST_CODE_REVIEW);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_REVIEW && resultCode == RESULT_CODE_TEACHER && data != null) {
            String corrected = data.getStringExtra("correctedText");
            teacherCorrection.setText(corrected);

            // Hiện phần sửa bài của cô giáo
            teacherCorrection.setVisibility(View.VISIBLE);
            findViewById(R.id.textView).setVisibility(View.VISIBLE);
        }
    }
}
