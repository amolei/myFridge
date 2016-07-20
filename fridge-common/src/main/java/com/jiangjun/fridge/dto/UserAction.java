package com.jiangjun.fridge.dto;

import java.util.Date;

/**
 * Created by jiangjun on 2016/7/20.
 */
public class UserAction {

    private long action_id;
    private long user_id;
    private long article_id;
    private Date update_time;
    private int action_type;//0是评论，1是点赞，2是收藏，3是浏览，4是分享
    private String content;//评论内容

    public long getAction_id() {
        return action_id;
    }

    public void setAction_id(long action_id) {
        this.action_id = action_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(long article_id) {
        this.article_id = article_id;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getAction_type() {
        return action_type;
    }

    public void setAction_type(int action_type) {
        this.action_type = action_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
