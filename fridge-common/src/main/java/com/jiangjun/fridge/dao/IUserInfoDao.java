package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.UserInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Repository
public interface IUserInfoDao {

    public void addUserInfo(UserInfoDto userInfoDto);

    public List<UserInfoDto> list();

    public UserInfoDto queryByUserName(String user_name);

    public UserInfoDto queryByUserId(long user_id);

    public UserInfoDto login(@Param("user_name") String user_name, @Param("password") String password);

    public void update(UserInfoDto userInfoDto);
}
