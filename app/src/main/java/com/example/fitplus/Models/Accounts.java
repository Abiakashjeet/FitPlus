package com.example.fitplus.Models;

import com.google.firebase.firestore.auth.User;

public class Accounts {
    String Name;
    String Email;
    String Age;
    String Gender;
    String Password;
    String User_Type;


    public Accounts(){
    }

    public Accounts(String name, String email, String age, String gender, String password, String user_Type) {
        Name = name;
        Email = email;
        Age = age;
        Gender = gender;
        Password = password;
        User_Type = user_Type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUser_Type() {
        return User_Type;
    }

    public void setUser_Type(String user_Type) {
        User_Type = user_Type;
    }
}
