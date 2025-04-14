package com.example.combined;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class display extends AppCompatActivity {

    DBHelper db;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        db = new DBHelper(this);
        list=findViewById(R.id.listview);
        ArrayList<String> studList= new ArrayList<>();

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("Name");
        String rollno1 = intent.getStringExtra("Rollno");

        Cursor res = db.fetchStudent(name1,rollno1);

        while(res.moveToNext()){
            String name = res.getString(1);
            String rollno = res.getString(2);
            String gender = res.getString(3);
            String branch = res.getString(4);
            String subjects = res.getString(5);

            studList.add("Name: " + name);
            studList.add("Roll No: " + rollno);
            studList.add("Gender: " + gender);
            studList.add("Branch: " + branch);
            studList.add("Subjects: " + subjects);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,studList);
        list.setAdapter(adapter);
    }
}
