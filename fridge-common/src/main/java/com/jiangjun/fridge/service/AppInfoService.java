package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.AppInfo;

import java.util.List;

/**
 * Created by jiangjun on 16/6/11.
 */
public interface AppInfoService {
    public void addAppInfo(AppInfo appInfo);

    public List<AppInfo> list();

    public AppInfo getAppByName(String app_name);

    public AppInfo getAppByVersion(String app_version);

}
