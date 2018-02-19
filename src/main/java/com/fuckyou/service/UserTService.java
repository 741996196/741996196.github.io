package com.fuckyou.service;

import com.fuckyou.pojo.UserT;

/**
 * Created by 陈源熹 on 2017-06-24.
 */
public interface UserTService {

    UserT getUserById(int userId);

    public interface IUserService {
        public UserT getUserById(int userId);
    }

    UserT updateByPrimaryKeySelective(UserT userT);

    UserT selectUset(UserT userT);
}
