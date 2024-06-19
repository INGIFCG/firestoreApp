package com.example.firestoreapplication.models;

import java.io.Serializable;

public class Friends  implements Serializable {
    private String name, email ,id;
    public Friends() {
    }
    public Friends(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public Friends(String id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
