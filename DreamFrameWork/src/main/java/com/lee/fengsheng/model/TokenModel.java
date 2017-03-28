package com.lee.fengsheng.model;

/**
 * Created by XinSheng on 2016/12/16.
 */
public class TokenModel {

    //用户id
    private long u_id;

    //随机生成的uuid
    private String token;

    private Long timestamp;

    public TokenModel() {
    }

    public TokenModel(long u_id, String token, Long timestamp) {
        this.u_id = u_id;
        this.token = token;
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}