package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dto.AppInfo;
import com.jiangjun.fridge.dao.IAppInfoDao;
import com.jiangjun.fridge.service.AppInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 111 on 16/6/1.
 */
@Service("appInfoService")
public class AppInfoDaoImpl implements AppInfoService {

    @Resource
    private IAppInfoDao appInfoDao;

    public void addAppInfo(AppInfo appInfo) {
        this.appInfoDao.addAppInfo(appInfo);
    }
}
