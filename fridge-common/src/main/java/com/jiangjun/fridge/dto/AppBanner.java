package com.jiangjun.fridge.dto;

import java.util.Date;

/**
 * Created by jiangjun on 16/6/1.
 */
public class AppBanner {

    private Long id;
    private Long app_id;//对应AppInfo表的主键ID
    private String image_path;
    private String image_info;
    private Date update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApp_id() {
        return app_id;
    }

    public void setApp_id(Long app_id) {
        this.app_id = app_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_info() {
        return image_info;
    }

    public void setImage_info(String image_info) {
        this.image_info = image_info;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
