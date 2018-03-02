package com.wangchong.blog.websocket;

import io.netty.channel.Channel;

public class Message {

    private String id;
    private Channel sender;
    private Channel receiver;
    private String msg;

    public Message(){
        this.id = System.currentTimeMillis()+"";
    }

    public Channel getSender() {
        return sender;
    }

    public void setSender(Channel sender) {
        this.sender = sender;
    }

    public Channel getReceiver() {
        return receiver;
    }

    public void setReceiver(Channel receiver) {
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
