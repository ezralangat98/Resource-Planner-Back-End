package com.react_spring_boot.User;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.react_spring_boot.Organization.Organization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@JsonIncludeProperties({"id","firstName", "lastName","emailId","phoneNo", "gender"})
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "gender")
    private String gender;

    @ManyToOne
//    @NotBlank(message = "Organization is required")
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /** Default Constructor */
    public User() {
    }

    /** All Arguments Constructor */
    public User(int id, String firstName, String lastName, String emailId, String phoneNo, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.gender = gender;
    }

    /** Getters and Setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
