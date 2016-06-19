package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IUserInfoDao;
import com.jiangjun.fridge.dto.UserInfoDto;
import com.jiangjun.fridge.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    public void addUserInfo(UserInfoDto userInfoDto) {
        userInfoDao.addUserInfo(userInfoDto);
    }

    public List<UserInfoDto> list() {
        return userInfoDao.list();
    }
    public UserInfoDto queryByUserName(String user_name){
        return userInfoDao.queryByUserName(user_name);
    }

    public UserInfoDto queryByUserId(long user_id){
        return userInfoDao.queryByUserId(user_id);
    }
}
