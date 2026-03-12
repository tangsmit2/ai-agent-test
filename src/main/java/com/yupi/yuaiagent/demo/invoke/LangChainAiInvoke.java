package com.yupi.yuaiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;

public class LangChainAiInvoke {
    public static void main(String[] args) {
        ChatModel qwenChatModel = QwenChatModel.builder()
                .apiKey(TestApiKey.API_KEY)
                .modelName("qwen-plus")
                .build();
        String chat = qwenChatModel.chat("我是小田，你好");
        System.out.println(chat);
    }
}
