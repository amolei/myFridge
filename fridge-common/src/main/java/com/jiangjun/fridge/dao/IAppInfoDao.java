package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.AppInfo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 111 on 16/6/1.
 */

@Repository
public interface IAppInfoDao {

    public void addAppInfo(AppInfo appInfo);

    public List<AppInfo> list();

    public AppInfo getAppByName(String app_name);

    public AppInfo getAppByVersion(String app_version);
}
