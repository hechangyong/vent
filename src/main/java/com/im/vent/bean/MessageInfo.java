package com.im.vent.bean;

import java.util.Date;

public class MessageInfo {
    private String id;
    private String message;
    private String ip;
    private String replymsg;
    private Date create_time;

    public MessageInfo(String message, String ip) {
        this.message = message;
        this.ip = ip;
        this.create_time = new Date();
    }

    public MessageInfo() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReplymsg() {
        return replymsg;
    }

    public void setReplymsg(String replymsg) {
        this.replymsg = replymsg;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", ip='" + ip + '\'' +
                ", replymsg='" + replymsg + '\'' +
                '}';
    }
}
