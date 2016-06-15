package com.jiangjun.fridge;

import com.jiangjun.fridge.dto.FoodKindDto;
import com.jiangjun.fridge.service.AppInfoService;
import com.jiangjun.fridge.service.FoodKindService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
public class FoodKindTest {

    private FoodKindService foodKindService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        foodKindService = (FoodKindService) context.getBean("foodKindService");
    }

    @Test
    public void createTest() {
        FoodKindDto foodKindDto = new FoodKindDto();
        foodKindDto.setFood_kind_name("海鲜");
        foodKindDto.setFood_kind_info("海鲜海鲜");
        foodKindDto.setSimple_name("HX");
        foodKindDto.setFood_kind_img("hx.png");
        foodKindService.addFoodKind(foodKindDto);
    }

    @Test
    public void createList() {
        List<FoodKindDto> list = foodKindService.list();
        for (FoodKindDto f : list) {
            try {
                System.out.println(f.getFood_kind_id() + ">>>" + f.getFood_kind_name() + ";" + f.getFood_kind_info() + ";" + f.getFood_kind_img());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
