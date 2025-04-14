package com.example.combined;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        EditText name = findViewById(R.id.editTextName);
        EditText rollno = findViewById(R.id.editTextRoll);
        RadioGroup rg = findViewById(R.id.radioGroupGender);
        Spinner branch = findViewById(R.id.spinnerBranch);
        CheckBox ai = findViewById(R.id.checkBoxAI);
        CheckBox ml = findViewById(R.id.checkBoxML);
        CheckBox dbms = findViewById(R.id.checkBoxDBMS);
        Button submit = findViewById(R.id.buttonSubmit);

        String[] options = {"CS", "IT", "CCE", "DS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);

        submit.setOnClickListener(v -> {
            int selectedId = rg.getCheckedRadioButtonId();
            if (selectedId == -1) {
                showToast("Please select gender");
                return;
            }

            RadioButton gender = findViewById(selectedId);

            StringBuilder subjects = new StringBuilder();
            if (ai.isChecked()) subjects.append("AI, ");
            if (ml.isChecked()) subjects.append("ML, ");
            if (dbms.isChecked()) subjects.append("DBMS");

            boolean inserted = db.insertStudent(
                    name.getText().toString(),
                    rollno.getText().toString(),
                    gender.getText().toString(),
                    branch.getSelectedItem().toString(),
                    subjects.toString()
            );

            if (inserted) {
                showToast("Data Inserted");
                Intent intent = new Intent(MainActivity.this, display.class);
                intent.putExtra("Name",name.getText().toString());
                intent.putExtra("Rollno",rollno.getText().toString());
                startActivity(intent);

            } else {
                showToast("Insert Failed");
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
