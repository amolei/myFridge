package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.UserAction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/20.
 */
@Repository
public interface IUserActionDao {

    public void add(UserAction userAction);
    public List<UserAction> list();

}
