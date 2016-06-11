package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.AppInfo;
import com.jiangjun.fridge.service.AppInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by jiangjun on 16/6/11.
 */
public class AppInfoTest {

    private AppInfoService appInfoService;

    @Before
    public void before(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml","classpath:conf/spring/spring-mybatis.xml"});
        appInfoService = (AppInfoService) context.getBean("appInfoService");
    }

    @Test
    public void createTest(){
        AppInfo appInfo = new AppInfo();
        appInfo.setId(1L);
        appInfo.setApp_info("test_info");
        appInfo.setApp_path("test_path");
        appInfo.setApp_name("test_name");
        appInfo.setApp_version("test_version");
        appInfo.setUpdate_time(new Date());
        appInfoService.addAppInfo(appInfo);
    }


}
