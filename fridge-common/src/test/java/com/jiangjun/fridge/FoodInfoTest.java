package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.dto.FoodKindDto;
import com.jiangjun.fridge.service.FoodInfoService;
import com.jiangjun.fridge.service.FoodKindService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
public class FoodInfoTest {

    private FoodKindService foodKindService;
    private FoodInfoService foodInfoService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        foodKindService = (FoodKindService) context.getBean("foodKindService");
        foodInfoService = (FoodInfoService) context.getBean("foodInfoService");
    }

    @Test
    public void searchTest(){
        String keywords = "g";
        List<FoodInfoDto> list = foodInfoService.listBySName(keywords);
        System.out.println(list.size());
    }

    @Test
    public void createListByKindId(){
        List<FoodKindDto> foodKindDtos = foodKindService.list();
        for(FoodKindDto fk:foodKindDtos){
            List<FoodInfoDto> fi = foodInfoService.listByKindId(fk.getFood_kind_id());
            if(fi != null && fi.size() > 0){
                for(FoodInfoDto fid:fi){
                    System.out.println(fid.getFood_id() + "" + fid.getFood_name() + fid.getKind_id());
                }
            }
        }
    }

    @Test
    public void createList(){
        List<FoodInfoDto> list = foodInfoService.list();
        for(FoodInfoDto fi:list){
            System.out.println(fi.getFood_id() + ";" + fi.getFood_name() + ";" + fi.getFood_des());
        }
    }

    @Test
    public void createAdd(){
        List<FoodKindDto> foodKindDtos = foodKindService.list();
        for(FoodKindDto fk:foodKindDtos){
            if(fk.getSimple_name().equals("SG")){
                //水果
                FoodInfoDto foodInfoDto = new FoodInfoDto();
                foodInfoDto.setFood_name("西瓜");
                foodInfoDto.setSimple_name("XG");
                foodInfoDto.setFood_des("西瓜");
                foodInfoDto.setFood_img("/images/xihongshi.9.png");
                foodInfoDto.setHot(0);
                foodInfoDto.setKind_id(fk.getFood_kind_id());
                foodInfoService.addFoodInfo(foodInfoDto);
            }
//            if(fk.getSimple_name().equals("SC")) {
//                //蔬菜
//                FoodInfoDto foodInfoDto = new FoodInfoDto();
//                foodInfoDto.setFood_name("萝卜");
//                foodInfoDto.setSimple_name("LB");
//                foodInfoDto.setFood_des("萝卜萝卜");
//                foodInfoDto.setFood_img("lb.png");
//                foodInfoDto.setHot(0);
//                foodInfoDto.setKind_id(fk.getFood_kind_id());
//                foodInfoService.addFoodInfo(foodInfoDto);
//            }
        }
    }
}
