package com.yupi.yuaiagent.demo.invoke;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * 使用Hutool调用阿里云通义千问API
 */
public class QwenApiClient {

    // 替换成你的API Key
    private static final String DASHSCOPE_API_KEY = TestApiKey.API_KEY;
    private static final String API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    public static void main(String[] args) {
        try {
            // 1. 构建请求体JSON
            JSONObject requestBody = new JSONObject();
            // 设置模型名称
            requestBody.set("model", "qwen-plus");
            
            // 构建input部分
            JSONObject input = new JSONObject();
            // 构建messages数组
            JSONObject systemMsg = new JSONObject();
            systemMsg.set("role", "system");
            systemMsg.set("content", "You are a helpful assistant.");
            
            JSONObject userMsg = new JSONObject();
            userMsg.set("role", "user");
            userMsg.set("content", "你是谁？");
            
            input.set("messages", new JSONObject[]{systemMsg, userMsg});
            requestBody.set("input", input);
            
            // 构建parameters部分
            JSONObject parameters = new JSONObject();
            parameters.set("result_format", "message");
            requestBody.set("parameters", parameters);

            // 2. 发送POST请求
            HttpResponse response = HttpRequest.post(API_URL)
                    // 设置请求头
                    .header(Header.AUTHORIZATION, "Bearer " + DASHSCOPE_API_KEY)
                    .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                    // 设置请求体
                    .body(requestBody.toString())
                    // 设置超时时间（可选，单位：毫秒）
                    .timeout(30000)
                    // 执行请求
                    .execute();

            // 3. 处理响应
            if (response.isOk()) {
                // 获取响应体（UTF-8编码）
                String responseBody = String.valueOf(response.body());
                System.out.println("响应结果：");
                System.out.println(responseBody);
            } else {
                System.err.println("请求失败，状态码：" + response.getStatus());
                System.err.println("错误信息：" + response.body());
            }
        } catch (Exception e) {
            System.err.println("请求过程中发生异常：");
            e.printStackTrace();
        }
    }
}