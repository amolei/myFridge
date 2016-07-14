package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.UserInfoDto;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public interface UserInfoService {

    public void addUserInfo(UserInfoDto userInfoDto);

    public List<UserInfoDto> list();

    public UserInfoDto queryByUserName(String user_name);

    public UserInfoDto queryByUserId(long user_id);

    public UserInfoDto login(String user_name,String password);
}
