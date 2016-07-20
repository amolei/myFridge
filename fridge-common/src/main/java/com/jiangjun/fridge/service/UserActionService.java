package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.UserAction;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/20.
 */
public interface UserActionService {
    public void add(UserAction userAction);
    public List<UserAction> list();
}
