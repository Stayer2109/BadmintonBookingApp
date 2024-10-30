package com.example.badmintonbookingapp.dto.response;

import java.util.Date;

public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private Boolean status;
    private Boolean gender;
    private Date dob;
    private String firstName;
    private String lastName;

    public UserResponseDTO(Integer id, String username, String email, Boolean status, Boolean gender, Date dob, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
        this.gender = gender;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
