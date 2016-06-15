package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiangjun on 16/6/13.
 */
public class ShopListForFoodDto {

    private Long shop_list_for_food_id;
    private Long shop_list_id;
    private Long food_id;
    private Integer status;
    private FoodInfoDto food_info;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getShop_list_for_food_id() {
        return shop_list_for_food_id;
    }

    public void setShop_list_for_food_id(Long shop_list_for_food_id) {
        this.shop_list_for_food_id = shop_list_for_food_id;
    }

    public Long getShop_list_id() {
        return shop_list_id;
    }

    public void setShop_list_id(Long shop_list_id) {
        this.shop_list_id = shop_list_id;
    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public FoodInfoDto getFood_info() {
        return food_info;
    }

    public void setFood_info(FoodInfoDto food_info) {
        this.food_info = food_info;
    }
}
