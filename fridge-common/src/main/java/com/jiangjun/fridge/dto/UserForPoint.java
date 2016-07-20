package com.jiangjun.fridge.dto;

/**
 * Created by jiangjun on 2016/7/21.
 * 用户积分表
 */
public class UserForPoint {
    private long point_id;
    private long user_id;
    private long action_id;//用户的行为Id
    private int point;//此次用户行为获得的积分

    public long getPoint_id() {
        return point_id;
    }

    public void setPoint_id(long point_id) {
        this.point_id = point_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getAction_id() {
        return action_id;
    }

    public void setAction_id(long action_id) {
        this.action_id = action_id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
