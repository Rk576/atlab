package com.example.combined;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "StudentDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,rollno TEXT,gender TEXT,branch TEXT,courses TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    public boolean insertStudent(String name, String rollno, String gender, String branch, String courses) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name",name);
        cv.put("rollno",rollno);
        cv.put("gender",gender);
        cv.put("branch",branch);
        cv.put("courses",courses);

        long result=db.insert("student",null,cv);

        return result!=-1;
    }

    public Cursor fetchStudent(String name, String rollno){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM student WHERE name=? AND rollno=?",new String[]{name,rollno});
    }
}
