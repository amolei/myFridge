package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.AppInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by 111 on 16/6/1.
 */

@Repository
public interface IAppInfoDao {

    public void addAppInfo(AppInfo appInfo);
}
