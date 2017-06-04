package com.zsmome.day17_student_table.sqlite;

import com.zsmome.day17_student_table.model.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public interface StudentDAO {
    /**
     * 插入学生信息
     */
    void insert(Student stu);
    /**
     * 获取学生信息
     */
    List<Student> getStudentList();
}
