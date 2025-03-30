package com.mobile.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.activity.InfoActivity;
import com.mobile.app.model.Student;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText id;
    private EditText studentClass;
    private EditText phone;
    private EditText goal1Input;
    private RadioGroup gradeRadioGroup, majorRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.form_layout);

        init();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init() {
        this.name = findViewById(R.id.name);
        this.id = findViewById(R.id.id);
        this.studentClass = findViewById(R.id.studentClass);
        this.phone = findViewById(R.id.phone);
        this.goal1Input = findViewById(R.id.goal1Input);

        this.gradeRadioGroup = findViewById(R.id.gradeRadioGroup);
        this.majorRadioGroup = findViewById(R.id.majorRadioGroup);

        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(v -> submit());
    }

    private String getMajor(RadioGroup group) {
        int checkedId = group.getCheckedRadioButtonId();
        if (checkedId == -1) return "No major selected";

        RadioButton checkedRadioButton = group.findViewById(checkedId);
        return checkedRadioButton.getContentDescription() != null ?
                checkedRadioButton.getContentDescription().toString() : "Unknown major";
    }

    private String getGrade(RadioGroup group) {
        int checkedId = group.getCheckedRadioButtonId();
        if (checkedId == -1) return "No grade selected";

        RadioButton checkedRadioButton = group.findViewById(checkedId);
        return checkedRadioButton.getContentDescription() != null ?
                checkedRadioButton.getContentDescription().toString() : "Unknown grade";
    }

    private void submit() {
        String name = this.name.getText().toString();
        String id = this.id.getText().toString();
        String studentClass = this.studentClass.getText().toString();
        String phone = this.phone.getText().toString();
        String goal = this.goal1Input.getText().toString();

        String major = getMajor(this.majorRadioGroup);
        String grade = getGrade(this.gradeRadioGroup);

        Student st = new Student(name, id, studentClass, phone, grade, major);
        switchIntent(st, goal);
    }

    private void switchIntent(Student st, String goal) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("student", st);
        intent.putExtra("goal", goal);
        startActivity(intent);
    }
}