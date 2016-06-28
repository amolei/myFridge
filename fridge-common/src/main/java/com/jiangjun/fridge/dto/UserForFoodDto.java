package com.jiangjun.fridge.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jiangjun on 16/6/12.
 */
public class UserForFoodDto {

    private Long user_for_food_id;
    private Long user_id;
    private Long food_id;
    private String num;
    private Date create_time;
    private Date past_time;
    private Integer status;
    private String comment;
    private FoodInfoDto food_info;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUser_for_food_id() {
        return user_for_food_id;
    }

    public void setUser_for_food_id(Long user_for_food_id) {
        this.user_for_food_id = user_for_food_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPast_time() {
        return past_time;
    }

    public void setPast_time(Date past_time) {
        this.past_time = past_time;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
