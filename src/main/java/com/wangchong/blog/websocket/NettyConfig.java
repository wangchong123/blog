package com.wangchong.blog.websocket;


import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class NettyConfig {

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static List<Message> session = new ArrayList<Message>();

    public static List<String> userList = new ArrayList<>();

    public static List<UserSession> userSessions = new ArrayList<>();

    public static List<UserSession> createSession(UserSession session){

        return NettyConfig.userSessions;
    }

}
