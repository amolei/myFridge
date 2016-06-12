package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.UserInfoDto;
import com.jiangjun.fridge.service.FoodInfoService;
import com.jiangjun.fridge.service.FoodKindService;
import com.jiangjun.fridge.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public class UserInfoTest {

    private UserInfoService userInfoService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        userInfoService = (UserInfoService) context.getBean("userInfoService");
    }

    @Test
    public void createAdd(){
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUser_name("12938847598");
        userInfoDto.setNick_name("wangtao");
        userInfoDto.setPassword("123456");
        userInfoDto.setImage_path("12938847598.png");
        userInfoDto.setUpdate_time(new Date());
        userInfoService.addUserInfo(userInfoDto);
    }

    @Test
    public void createList(){
        List<UserInfoDto> list = userInfoService.list();
        for(UserInfoDto u:list){
            System.out.println(u.getUser_name());
        }
    }
}
