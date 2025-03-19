package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.UserAggregate;

public interface IUserAggregateServices {
   UserAggregate getUserAggregateByUserId(int user_id);
   UserAggregate getUserAggregateByAccountId(int account_id);
   UserAggregate getUserAggregateByWalletId(int wallet_id);
}
