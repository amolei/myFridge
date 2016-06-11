package com.jiangjun.fridge.dto;

import java.util.Date;

/**
 * Created by jiangjun on 16/6/1.
 */
public class AppAdvice {

    private Long id;
    private Long user_id;
    private String advice;
    private Date update_time;

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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
