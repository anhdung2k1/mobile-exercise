package com.mobile.app.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.app.R;
import com.mobile.app.model.Student;

public class InfoActivity extends AppCompatActivity {

    private TextView name, id, studentClass, phone, grade, major, goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_layout);

        initViews();
        populateStudentInfo();
    }

    private void initViews() {
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        studentClass = findViewById(R.id.studentClass);
        phone = findViewById(R.id.phone);
        grade = findViewById(R.id.grade);
        major = findViewById(R.id.major);
        goal = findViewById(R.id.goal);
        Button backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(v -> {
            finish(); // closes InfoActivity and returns to MainActivity
        });
    }

    private void populateStudentInfo() {
        Student student = getIntent().getSerializableExtra("student", Student.class);
        String goalText = getIntent().getStringExtra("goal");

        if (student != null) {
            name.setText(student.getName());
            id.setText(student.getId());
            studentClass.setText(student.getStudentClass());
            phone.setText(student.getPhone());
            grade.setText(student.getGrade());
            major.setText(student.getMajor());
        }

        goal.setText((goalText != null && !goalText.trim().isEmpty()) ? goalText : "No goal set");
    }
}