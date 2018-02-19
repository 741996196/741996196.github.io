package com.fuckyou.service.impl;

import com.fuckyou.dao.UserTMapper;
import com.fuckyou.pojo.UserT;
import com.fuckyou.service.UserTService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 陈源熹 on 2017-06-24.
 */
@Service("userTService")
public class UserTServiceImpl implements UserTService{

    @Resource
    private UserTMapper userDao;
    @Override
    public UserT getUserById(int userId) {
        // TODO Auto-generated method stub
        return this.userDao.selectByPrimaryKey(userId);
    }

    @Override
    public UserT updateByPrimaryKeySelective(UserT userT) {

        return  null;
    }

    @Override
    public UserT selectUset(UserT userT) {
        return userDao.selectUset(userT);
    }

}
