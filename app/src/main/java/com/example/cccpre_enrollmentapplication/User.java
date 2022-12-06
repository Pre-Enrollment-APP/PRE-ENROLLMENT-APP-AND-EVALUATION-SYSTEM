package com.example.cccpre_enrollmentapplication;


public class User {

    public String Name, Email, Student_number, Course, Address, Birthday, Contact_Number, Mother, Emergency_number, Father,Emergency_name;

    public User() {

    }

    public User(String Name, String Email, String Student_number, String Course, String Address, String Birthday, String Contact_Number, String Mother, String Emergency_number, String Father, String Emergency_name ){
        this.Name = Name;
        this.Email = Email;
        this.Student_number = Student_number;
        this.Course = Course;
        this.Address = Address;
        this.Birthday = Birthday;
        this.Contact_Number = Contact_Number;
        this.Mother = Mother;
        this.Emergency_name = Emergency_name;
        this.Father = Father;
        this.Emergency_number = Emergency_number;

    }

}