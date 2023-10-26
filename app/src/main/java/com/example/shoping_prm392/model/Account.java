package com.example.shoping_prm392.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

public class Account  implements Serializable {
    private String id;
    private String email;
    private String password;

    private String role;

    private String image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
