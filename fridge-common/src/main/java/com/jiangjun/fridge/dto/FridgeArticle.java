package com.jiangjun.fridge.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
public class FridgeArticle {

    private Long article_id;
    //频道ID
    private Long channel_id;
    //文章标题
    private String article_title;
    //文章来源
    private String source;
    private Date update_time;
    //文章类型：1是九宫格，2是图文，3是图集
    private Integer article_type;
    //文章内容
    private String content;
    //文章地址
    private String content_path;
    //图片地址，用逗号隔开
    private String images;
    //状态，1是发布，0是未发布
    private String status;

    //评论列表
    private List<UserAction> comments;
    //评论次数
    private int commentSize;
    //点赞的次数
    private int likeSize;
    //收藏的次数
    private int storeSize;
    //分享的次数
    private int shareSize;
    //是否点赞
    private boolean isLike;
    //是否收藏
    private boolean isStore;


    private List<String> picUrlList;

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getArticle_type() {
        return article_type;
    }

    public void setArticle_type(Integer article_type) {
        this.article_type = article_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_path() {
        return content_path;
    }

    public void setContent_path(String content_path) {
        this.content_path = content_path;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public List<UserAction> getComments() {
        return comments;
    }

    public void setComments(List<UserAction> comments) {
        this.comments = comments;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isStore() {
        return isStore;
    }

    public void setStore(boolean store) {
        isStore = store;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public int getLikeSize() {
        return likeSize;
    }

    public void setLikeSize(int likeSize) {
        this.likeSize = likeSize;
    }

    public int getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(int storeSize) {
        this.storeSize = storeSize;
    }

    public int getShareSize() {
        return shareSize;
    }

    public void setShareSize(int shareSize) {
        this.shareSize = shareSize;
    }
}
