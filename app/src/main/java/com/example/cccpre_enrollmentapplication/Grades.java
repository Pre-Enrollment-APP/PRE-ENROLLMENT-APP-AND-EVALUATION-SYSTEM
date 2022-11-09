package com.example.cccpre_enrollmentapplication;

public class Grades {
    private String code;
    private String description;
    private String grade;

    public Grades(){

    }

    public Grades(String code, String description,String grade){

        this.code=code;
        this.description=description;
        this.grade=grade;


    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
