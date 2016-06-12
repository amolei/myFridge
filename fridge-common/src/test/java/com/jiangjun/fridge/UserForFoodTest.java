package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.dto.UserForFoodDto;
import com.jiangjun.fridge.dto.UserInfoDto;
import com.jiangjun.fridge.service.FoodInfoService;
import com.jiangjun.fridge.service.UserForFoodService;
import com.jiangjun.fridge.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public class UserForFoodTest {

    private UserForFoodService userForFoodService;
    private UserInfoService userInfoService;
    private FoodInfoService foodInfoService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        userForFoodService = (UserForFoodService) context.getBean("userForFoodService");
        userInfoService = (UserInfoService) context.getBean("userInfoService");
        foodInfoService = (FoodInfoService) context.getBean("foodInfoService");
    }

    @Test
    public void createList()
    {
        List<UserForFoodDto> list = userForFoodService.list();
        for(UserForFoodDto u:list){
            System.out.println(u.getUser_id() + "<>" + u.getFood_id());
        }
    }

    @Test
    public void createAdd() {
        List<UserInfoDto> userInfoDtoList = userInfoService.list();
        List<FoodInfoDto> foodInfoDtos = foodInfoService.list();
        for (UserInfoDto ui : userInfoDtoList) {
            for (FoodInfoDto fi : foodInfoDtos) {
                UserForFoodDto userForFoodDto = new UserForFoodDto();
                userForFoodDto.setUser_id(ui.getId());
                userForFoodDto.setFood_id(fi.getId());
                userForFoodDto.setNum(3);
                userForFoodDto.setWeight("10");
                userForFoodDto.setCreate_time(new Date());
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, 1);
                userForFoodDto.setPast_time(c.getTime());
                userForFoodDto.setStatus(1);
                userForFoodService.addUserForFood(userForFoodDto);
            }
        }
    }
}
