package com.yunqi.myeshop.entity.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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
}
