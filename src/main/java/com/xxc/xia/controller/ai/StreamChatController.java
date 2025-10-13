package com.xxc.xia.controller.ai;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/14 1:06
 */
@RestController
@RequestMapping("/ai/streamChat")
public class StreamChatController {

    @Autowired
    private OpenAiChatModel baseChatModel;
    @Autowired
    private OpenAiApi baseOpenAiApi;


    @Operation(summary = "streamChat")
    @PostMapping("/chat")
    public Flux<String> chat(@RequestBody JSONObject req) {
        Flux<String> output = ChatClient.builder(baseChatModel)
                .build()
                .prompt()
                .user(req.getString("userMsg"))
                .stream()
                .content();
        Flux<String> res = output.map(s -> {
            System.out.println(s);
            return s;
        });
        return res;
    }
}
