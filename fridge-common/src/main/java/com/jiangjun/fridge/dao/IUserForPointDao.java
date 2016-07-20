package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.UserForPoint;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangjun on 2016/7/21.
 */
@Repository
public interface IUserForPointDao {

    public void add(UserForPoint userForPoint);
}
