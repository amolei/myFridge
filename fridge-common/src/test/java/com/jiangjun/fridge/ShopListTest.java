package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.ShopListDto;
import com.jiangjun.fridge.dto.UserInfoDto;
import com.jiangjun.fridge.service.FoodInfoService;
import com.jiangjun.fridge.service.ShopListService;
import com.jiangjun.fridge.service.UserForFoodService;
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
public class ShopListTest {

    private UserInfoService userInfoService;
    private ShopListService shopListService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        userInfoService = (UserInfoService) context.getBean("userInfoService");
        shopListService = (ShopListService) context.getBean("shopListService");
    }

    @Test
    public void createAdd() {
        List<UserInfoDto> userInfoDtos = userInfoService.list();
        for(UserInfoDto u:userInfoDtos){
            long user_id = u.getId();
            ShopListDto shopListDto = new ShopListDto();
            shopListDto.setUser_id(user_id);
            shopListDto.setShop_info_name(u.getUser_name() + "的购物单");
            shopListDto.setShop_info_date(new Date());
            shopListService.addShopList(shopListDto);
        }
    }

    @Test
    public void createList(){
        List<ShopListDto> list = shopListService.list();
        for(ShopListDto s:list){
            System.out.println(s.getShop_info_name());
        }
    }
}
