package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.UserAggregate;
import com.yunqi.myeshop.service.implementation.UserAggregateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class UseAggregateController {
    @Autowired
    private UserAggregateServices userAggregateServices;

    @GetMapping("/{user_id}")
    public UserAggregate getUserDetailByUserId(@PathVariable int user_id) {
        return userAggregateServices.getUserAggregateByUserId(user_id);
    }
}
