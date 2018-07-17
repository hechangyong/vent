package com.im.vent.bean;

public class BaiduToken {
    private Long id;
    private String token;
    private String session_key;
    private String scope;
    private String refresh_token;
    private String session_secret;
    private Long expires_in;

    private String date;



    @Override
    public String toString() {
        return "BaiduToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expires_in=" + expires_in +
                ", date='" + date + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
