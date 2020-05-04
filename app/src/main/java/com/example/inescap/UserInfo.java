package com.example.inescap;

public class UserInfo {

    private String FirstName;
    private String LastName;
    private String Username;
    private String Birthday;
    private String Gender;
    private String Email;
    private String Password;
    private String Interest;

   public String getInterest() {
        return Interest;
    }

    public void setInterest(String interest) {
        Interest = interest;
    }


    public UserInfo(){}
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUsername() {
        return Username;
    }

    public String getBirthday() {
        return Birthday;
    }

    public String getGender() {
        return Gender;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }



    public UserInfo(String firstName, String lastName, String username, String birthday, String gender, String email, String password, String interest) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        Birthday = birthday;
        Gender = gender;
        Email = email;
        Password = password;
        Interest = interest;
    }




}
