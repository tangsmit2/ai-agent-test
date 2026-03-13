package com.yupi.yuaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chatID = UUID.randomUUID().toString();
        //第一轮对话
        String message = "你好，我是小唐";
        String answer = loveApp.doChat(message, chatID);
        //第二轮
        message = "我想让另一半小明更爱我";
        answer = loveApp.doChat(message, chatID);
        Assertions.assertNotNull(answer);
        //第三轮
        message = "我的另一半叫什么来着?刚跟你说过，帮我回忆一下";
        answer = loveApp.doChat(message, chatID);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatID = UUID.randomUUID().toString();
        //第一轮对话
        String message = "你好,我是程序员鱼皮，我想让另一半（编程导航）更爱我，但我不知道该怎么做";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chatID);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doChatWithRag() {
        String chatID = UUID.randomUUID().toString();
        String message = "我已经结婚了,但是婚后关系不太亲密，怎么办?";
        String answer = loveApp.doChatWithRag(message, chatID);
        Assertions.assertNotNull(answer);
    }
}