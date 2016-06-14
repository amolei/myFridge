package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiangjun on 16/6/12.
 */
public class FoodKindDto {

    private Long food_kind_id;
    private String food_kind_name;
    private String food_kind_info;
    private String food_kind_img;
    private String simple_name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getFood_kind_id() {
        return food_kind_id;
    }

    public void setFood_kind_id(Long food_kind_id) {
        this.food_kind_id = food_kind_id;
    }

    public String getFood_kind_name() {
        return food_kind_name;
    }

    public void setFood_kind_name(String food_kind_name) {
        this.food_kind_name = food_kind_name;
    }

    public String getFood_kind_info() {
        return food_kind_info;
    }

    public void setFood_kind_info(String food_kind_info) {
        this.food_kind_info = food_kind_info;
    }

    public String getSimple_name() {
        return simple_name;
    }

    public void setSimple_name(String simple_name) {
        this.simple_name = simple_name;
    }

    public String getFood_kind_img() {
        return food_kind_img;
    }

    public void setFood_kind_img(String food_kind_img) {
        this.food_kind_img = food_kind_img;
    }
}
