package com.xxc.xia.controller.ai;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/14 0:11
 */
@RestController
@RequestMapping("/ai/simpleChat")
public class SimpleChatController {

    private final ChatClient chatClient;

    public SimpleChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .build();
    }

    // @Operation(summary = "generation")
    @GetMapping("/generation")
    public String generation(String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }
}
