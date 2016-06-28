package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dto.AppInfo;
import com.jiangjun.fridge.dao.IAppInfoDao;
import com.jiangjun.fridge.service.AppInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 111 on 16/6/1.
 */
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

    @Resource
    private IAppInfoDao appInfoDao;

    public void addAppInfo(AppInfo appInfo) {
        this.appInfoDao.addAppInfo(appInfo);
    }

    public List<AppInfo> list() {
        return appInfoDao.list();
    }

    public AppInfo getAppByName(String app_name) {
        return appInfoDao.getAppByName(app_name);
    }

    public AppInfo getAppByVersion(String app_version) {
        return appInfoDao.getAppByVersion(app_version);
    }
}
