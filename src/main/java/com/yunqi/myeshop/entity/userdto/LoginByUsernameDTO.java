package com.yunqi.myeshop.entity.userdto;

import lombok.Data;
import java.io.Serializable;

@Data
public class LoginByUsernameDTO implements Serializable {
    private String username;
    private String password;
    private String role;
    public LoginByUsernameDTO() {}
}
