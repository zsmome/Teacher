package com.zsmome.day17_student_table.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsmome.day17_student_table.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库，操作学生信息
 * Created by Administrator on 2017/5/19.
 */

public class StudentDAOImpl implements StudentDAO {
    private MySqliteOpenHelper mySqliteOpenHelper;
    public StudentDAOImpl(Context context) {
        mySqliteOpenHelper = MySqliteOpenHelper.getInstance(context);
    }
    @Override
    public void insert(Student stu) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("insert into student_tb(name,age,sex,address) values(?,?,?,?)",
                new Object[]{stu.getName(), stu.getAge(), stu.getSex(), stu.getAddress()});
        db.close();
    }

    @Override
    public List<Student> getStudentList() {
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student_tb", null);
        List<Student> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
           list.add(new Student(name, age, sex, address));
        }
        return list;
    }
}
