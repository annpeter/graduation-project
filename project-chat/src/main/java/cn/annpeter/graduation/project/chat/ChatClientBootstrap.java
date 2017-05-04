package cn.annpeter.graduation.project.chat;

import cn.annpeter.graduation.project.chat.client.ChatClient;

/**
 * Created on 2017/03/14
 *
 * @author annpeter.it@gmail.com
 */
public class ChatClientBootstrap {
    public static void main(String[] args) {
        String nickName = "annpeter";
        new ChatClient("localhost", 8888, nickName).connect();
    }
}
