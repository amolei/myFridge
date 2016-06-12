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
    public void createListByKindId(){
        List<FoodKindDto> foodKindDtos = foodKindService.list();
        for(FoodKindDto fk:foodKindDtos){
            List<FoodInfoDto> fi = foodInfoService.listByKindId(fk.getId());
            if(fi != null && fi.size() > 0){
                for(FoodInfoDto fid:fi){
                    System.out.println(fid.getId() + "" + fid.getFood_name() + fid.getKind_id());
                }
            }
        }
    }

    @Test
    public void createList(){
        List<FoodInfoDto> list = foodInfoService.list();
        for(FoodInfoDto fi:list){
            System.out.println(fi.getId() + ";" + fi.getFood_name() + ";" + fi.getFood_des());
        }
    }

    @Test
    public void createAdd(){
        List<FoodKindDto> foodKindDtos = foodKindService.list();
        for(FoodKindDto fk:foodKindDtos){
            if(fk.getSimple_name().equals("SG")){
                //水果
                FoodInfoDto foodInfoDto = new FoodInfoDto();
                foodInfoDto.setFood_name("香蕉");
                foodInfoDto.setSimple_name("XJ");
                foodInfoDto.setFood_des("香蕉香蕉");
                foodInfoDto.setFood_img("xj.png");
                foodInfoDto.setHot(0);
                foodInfoDto.setKind_id(fk.getId());
                foodInfoService.addFoodInfo(foodInfoDto);
            }
            if(fk.getSimple_name().equals("SC")) {
                //蔬菜
                FoodInfoDto foodInfoDto = new FoodInfoDto();
                foodInfoDto.setFood_name("青菜");
                foodInfoDto.setSimple_name("QC");
                foodInfoDto.setFood_des("青菜青菜");
                foodInfoDto.setFood_img("qc.png");
                foodInfoDto.setHot(0);
                foodInfoDto.setKind_id(fk.getId());
                foodInfoService.addFoodInfo(foodInfoDto);
            }
        }
    }
}
