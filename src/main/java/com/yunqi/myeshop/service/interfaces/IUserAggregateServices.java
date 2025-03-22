package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.UserAggregate;

public interface IUserAggregateServices {
   UserAggregate getUserAggregateByAccountId(int account_id);
}
