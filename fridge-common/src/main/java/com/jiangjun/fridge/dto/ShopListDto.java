package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jiangjun on 16/6/13.
 */
public class ShopListDto {

    private Long id;
    private Long user_id;
    private String shop_info_name;
    private Date shop_info_date;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getShop_info_name() {
        return shop_info_name;
    }

    public void setShop_info_name(String shop_info_name) {
        this.shop_info_name = shop_info_name;
    }

    public Date getShop_info_date() {
        return shop_info_date;
    }

    public void setShop_info_date(Date shop_info_date) {
        this.shop_info_date = shop_info_date;
    }
}
