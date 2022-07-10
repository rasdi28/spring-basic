package com.sdd.springbasic.entity;

import javax.persistence.*;

@Entity
public class Muser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer muserpk;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "address")
    private String address;
    @Column(name = "age")
    private Integer age;

    public Integer getMuserpk() {
        return muserpk;
    }

    public void setMuserpk(Integer muserpk) {
        this.muserpk = muserpk;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
