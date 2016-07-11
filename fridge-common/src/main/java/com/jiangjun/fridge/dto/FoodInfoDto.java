package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiangjun on 16/6/12.
 */
public class FoodInfoDto {

    private Long food_id;
    private Long kind_id;
    private String food_name;
    private String food_img;
    private String food_des;
    private String simple_name;
    private Integer hot;
    private Integer is_tmp;
    private Integer sort;

    private FoodKindDto foodKindDto;
    private String kind_name;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public Long getKind_id() {
        return kind_id;
    }

    public void setKind_id(Long kind_id) {
        this.kind_id = kind_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public String getFood_des() {
        return food_des;
    }

    public void setFood_des(String food_des) {
        this.food_des = food_des;
    }

    public String getSimple_name() {
        return simple_name;
    }

    public void setSimple_name(String simple_name) {
        this.simple_name = simple_name;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public FoodKindDto getFoodKindDto() {
        return foodKindDto;
    }

    public void setFoodKindDto(FoodKindDto foodKindDto) {
        this.foodKindDto = foodKindDto;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public Integer getIs_tmp() {
        return is_tmp;
    }

    public void setIs_tmp(Integer is_tmp) {
        this.is_tmp = is_tmp;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
