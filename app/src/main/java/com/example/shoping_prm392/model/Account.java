package com.example.shoping_prm392.model;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Account {
    private String id;
    private String email;
    private String password;

    private String role;
    public Account() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account(String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.role="user";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
