package com.yunqi.myeshop.entity.userdto;

import lombok.Data;
import java.io.Serializable;

@Data
public class AccountJwtDTO implements Serializable {
    private int account_id;
    private String account_uid;
    private String username;
    private String role;
}
