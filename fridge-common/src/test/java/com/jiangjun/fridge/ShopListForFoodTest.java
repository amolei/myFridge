package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.dto.ShopListDto;
import com.jiangjun.fridge.dto.ShopListForFoodDto;
import com.jiangjun.fridge.service.FoodInfoService;
import com.jiangjun.fridge.service.ShopListForFoodService;
import com.jiangjun.fridge.service.ShopListService;
import com.jiangjun.fridge.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public class ShopListForFoodTest {

    private FoodInfoService foodInfoService;
    private ShopListService shopListService;
    private ShopListForFoodService shopListForFoodService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        shopListService = (ShopListService) context.getBean("shopListService");
        shopListForFoodService = (ShopListForFoodService) context.getBean("shopListForFoodService");
        foodInfoService = (FoodInfoService) context.getBean("foodInfoService");
    }

    @Test
    public void createAdd(){
        List<ShopListDto> shopListDtos = shopListService.list();
        List<FoodInfoDto> foodInfoDtos = foodInfoService.list();
        for(ShopListDto s:shopListDtos){
            long shop_list_id = s.getShop_list_id();
            for(FoodInfoDto f:foodInfoDtos){
                long food_id = f.getFood_id();
                ShopListForFoodDto shopListForFoodDto = new ShopListForFoodDto();
                shopListForFoodDto.setShop_list_id(shop_list_id);
                shopListForFoodDto.setFood_id(food_id);
                shopListForFoodDto.setStatus(1);
                shopListForFoodService.addShopListForFood(shopListForFoodDto);
            }
        }
    }

    @Test
    public void createListByShopListId(){
        List<ShopListForFoodDto> list = shopListForFoodService.listByShopListId(1L);
        for(ShopListForFoodDto s:list){
            System.out.println(s.getFood_id());
        }
    }

    @Test
    public void createList(){

    }

}
