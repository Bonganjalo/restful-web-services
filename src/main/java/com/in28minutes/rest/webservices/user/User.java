package com.in28minutes.rest.webservices.user;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


public class User {
    private Integer id;

    @Size(min=2, message = "Name should have atleast 2 characters")
    private String name;

    @Past()
    private Date birth;

    protected User() {

    }
    public User(Integer id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }
}
