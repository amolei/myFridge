package com.jiangjun.fridge.dto;

/**
 * Created by jiangjun on 2016/7/16.
 */
public class FridgeSellerDto {

    private long seller_id;
    private String seller_name;
    private String seller_address;
    private Integer seller_level;
    private String seller_mobile;

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_address() {
        return seller_address;
    }

    public void setSeller_address(String seller_address) {
        this.seller_address = seller_address;
    }

    public Integer getSeller_level() {
        return seller_level;
    }

    public void setSeller_level(Integer seller_level) {
        this.seller_level = seller_level;
    }

    public String getSeller_mobile() {
        return seller_mobile;
    }

    public void setSeller_mobile(String seller_mobile) {
        this.seller_mobile = seller_mobile;
    }
}
