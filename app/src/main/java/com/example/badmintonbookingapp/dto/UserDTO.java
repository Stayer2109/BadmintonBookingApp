package com.example.badmintonbookingapp.dto;

import java.util.Date;

public class UserDTO {
    private Boolean status;
    private Boolean gender;
    private Date dob;
    private String firstName;
    private String lastName;

    public UserDTO() {
    }

    public UserDTO(Boolean status, Boolean gender, Date dob, String firstName, String lastName) {
        this.status = status;
        this.gender = gender;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
