package com.example.htw.myapplication.Model;

import io.realm.RealmObject;

public class Student extends RealmObject {

    String name;
    int marks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "name" + name  + "marks=" + marks ;
    }
}
