package cn.annpeter.graduation.project.chat;

import cn.annpeter.graduation.project.chat.server.ChatServer;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class ChatServerBootstrap {
    public static void main(String[] args) {
        ChatServer server = new ChatServer(8888);
        server.start();
    }
}
