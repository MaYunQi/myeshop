package com.yunqi.myeshop.entity.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private int user_id;
    private String first_name;
    private String last_name;
    private String id_number;
    private String gender;
    private LocalDate date_of_birth;
}
