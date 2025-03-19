package com.yunqi.myeshop.entity.user;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class Account {
    private int account_id;
    private int user_id;
    private String username;
    private String password_hash;
    private String email;
    private String phone_number;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime last_login_at;

    public int getAccount_id() {return account_id;}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(LocalDateTime last_login_at) {
        this.last_login_at = last_login_at;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", last_login_at=" + last_login_at +
                '}';
    }
}
