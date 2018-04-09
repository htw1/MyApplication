package com.example.htw.myapplication;

public class JsonModel
{

    private String name;
    private String surname;
    private String points;

    public JsonModel(String name, String surname, String points) {
        this.name = name;
        this.surname = surname;
        this.points = points;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
