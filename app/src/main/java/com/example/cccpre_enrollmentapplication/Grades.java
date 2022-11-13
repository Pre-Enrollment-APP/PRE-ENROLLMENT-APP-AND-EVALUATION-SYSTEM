package com.example.cccpre_enrollmentapplication;

public class Grades {
    private String Course_Code;
    private String Desc_title;
    private String Grade;

    public Grades() {

    }

    public Grades(String code, String description, String grade) {

        this.Course_Code = code;
        this.Desc_title = description;
        this.Grade = grade;


    }

    public String getCourse_Code() {
        return Course_Code;
    }

    public void setCourse_Code(String course_Code) {
        Course_Code = course_Code;
    }

    public String getDesc_title() {
        return Desc_title;
    }

    public void setDesc_title(String desc_title) {
        Desc_title = desc_title;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
