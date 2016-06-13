package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiangjun on 16/6/13.
 */
public class ShopListForFoodDto {

    private Long id;
    private Long shop_list_id;
    private Long food_id;
    private Integer status;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
