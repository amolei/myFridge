package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiangjun on 16/6/26.
 */
public class FridgeBrandDto {

    private long brand_id;
    private String fridge_name;
    private String fridge_logo;
    private String fridge_pinyin;
    private String fridge_mobile;
    private String keep_info;
    private String keep_time;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(long brand_id) {
        this.brand_id = brand_id;
    }

    public String getFridge_name() {
        return fridge_name;
    }

    public void setFridge_name(String fridge_name) {
        this.fridge_name = fridge_name;
    }

    public String getFridge_logo() {
        return fridge_logo;
    }

    public void setFridge_logo(String fridge_logo) {
        this.fridge_logo = fridge_logo;
    }

    public String getFridge_pinyin() {
        return fridge_pinyin;
    }

    public void setFridge_pinyin(String fridge_pinyin) {
        this.fridge_pinyin = fridge_pinyin;
    }

    public String getFridge_mobile() {
        return fridge_mobile;
    }

    public void setFridge_mobile(String fridge_mobile) {
        this.fridge_mobile = fridge_mobile;
    }

    public String getKeep_info() {
        return keep_info;
    }

    public void setKeep_info(String keep_info) {
        this.keep_info = keep_info;
    }

    public String getKeep_time() {
        return keep_time;
    }

    public void setKeep_time(String keep_time) {
        this.keep_time = keep_time;
    }
}
