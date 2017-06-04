package com.zsmome.frame_litepal.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/5/20.
 */

public class Student extends DataSupport {
    @Column(unique = true, nullable = false)
    private String sno;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private boolean sex;

    public Student(String sno, String name, int age, boolean sex) {
        this.sno = sno;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
