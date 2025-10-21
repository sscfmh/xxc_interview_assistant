package com.xxc.xia.controller.ai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxc.xia.ai.tool.DateTimeTools;
import com.xxc.xia.ai.tool.WriteFileTools;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.DefaultToolCallingManager;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionResult;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
    private OpenAiApi       baseOpenAiApi;
    @Autowired
    private ChatMemory      chatMemory;

    @Autowired
    private DateTimeTools   dateTimeTools;

    @Autowired
    private WriteFileTools  writeFileTools;

    @Operation(summary = "streamChat")
    @PostMapping("/chat")
    public Flux<String> chat(@RequestBody JSONObject req) {
        Flux<String> output = ChatClient.builder(baseChatModel).build().prompt()
            .user(req.getString("userMsg")).stream().content();
        Flux<String> res = output.map(s -> {
            System.out.println(s);
            return s;
        });
        return res;
    }

    @Operation(summary = "chatWithMemo")
    @PostMapping("/chatWithMemo")
    public Flux<String> chatWithMemo(@RequestParam("chatId") String chatId,
                                     @RequestBody JSONObject req) {
        Flux<String> output = ChatClient.builder(baseChatModel)
            .defaultAdvisors(
                MessageChatMemoryAdvisor.builder(chatMemory).conversationId(chatId).build())
            .build().prompt().system("简介回答、格式美观即可").user(req.getString("userMsg")).stream()
            .content();
        Flux<String> res = output.map(s -> {
            return s;
        });
        List<Message> messageList = chatMemory.get(chatId);
        for (Message message : messageList) {
            System.out.println(message);
            System.out.println();
        }
        return res;
    }

    @Operation(summary = "chatWithTools")
    @PostMapping("/chatWithTools")
    public Flux<String> chatWithTools(@RequestParam("chatId") String chatId,
                                      @RequestBody JSONObject req) {
        Flux<String> output = ChatClient.builder(baseChatModel)
            .defaultAdvisors(
                MessageChatMemoryAdvisor.builder(chatMemory).conversationId(chatId).build(),
                new SimpleLoggerAdvisor())
            .build().prompt().system("简介回答、格式美观即可").user(req.getString("userMsg"))
            .tools(dateTimeTools, writeFileTools).stream().content();
        //        List<Message> messageList = chatMemory.get(chatId);
        //        for (Message message : messageList) {
        //            System.out.println(message);
        //            System.out.println();
        //        }
        return output;
    }

    @Operation(summary = "chatWithUserControlledTools")
    @PostMapping("/chatWithUserControlledTools")
    public Flux<String> chatWithUserControlledTools(@RequestParam("chatId") String chatId,
                                                    @RequestBody JSONObject req) {
        ToolCallingManager toolCallingManager = DefaultToolCallingManager.builder().build();
        String conversationId = chatId;

        ChatOptions chatOptions = ToolCallingChatOptions.builder()
            .toolCallbacks(ToolCallbacks.from(dateTimeTools, writeFileTools))
            .internalToolExecutionEnabled(false).build();

        List<Message> messageList = chatMemory.get(conversationId);
        if (messageList.isEmpty()) {
            chatMemory.add(conversationId,
                List.of(new SystemMessage("一次最多调用一次工具，等上个工具执行结束才能调用下一个。简介回答、格式美观即可")));
            messageList = chatMemory.get(conversationId);
        }
        List<Message> msgList = new ArrayList<>(messageList);
        msgList.add(new UserMessage(req.getString("userMsg")));
        chatMemory.add(conversationId, List.of(msgList.get(msgList.size() - 1)));
        return processStream2(msgList, conversationId, chatOptions, toolCallingManager, 0)
            .doOnComplete(() -> {
                System.out.println("===================================");
                System.out.println(chatMemory.get(conversationId));
            });

        //        return Flux.create(sink -> {
        //            processChat(msgList, conversationId, chatOptions, toolCallingManager, 0)
        //                .subscribe(sink::next, e -> {
        //                    e.printStackTrace();
        //                    sink.error(e);
        //                }, () -> {
        //                    sink.next("\ndo finished...");
        //                    System.out.println(chatMemory.get(conversationId));
        //                    sink.complete();
        //                });
        //        });

        //        boolean[] isToolCall = { false };
        //        return Flux.create(sink -> {
        //            chatResponseFlux.subscribe(chatResponse -> {
        //                boolean hasToolCalls = chatResponse.hasToolCalls();
        //                System.out.println("hasToolCalls: " + hasToolCalls);
        //                if (hasToolCalls) {
        //                    isToolCall[0] = true;
        //                    ToolExecutionResult toolExecutionResult = toolCallingManager
        //                        .executeToolCalls(prompt, chatResponse);
        //                    boolean[] returnDirect = { toolExecutionResult.returnDirect() };
        //                    sink.next("tool call: " + toolExecutionResult);
        //                    chatMemory.add(conversationId, toolExecutionResult.conversationHistory()
        //                        .get(toolExecutionResult.conversationHistory().size() - 1));
        //
        //                    StringBuilder sb = new StringBuilder();
        //                    int cnt = 0;
        //                    while (!returnDirect[0] && cnt++ < 5) {
        //                        Prompt prompt0 = new Prompt(toolExecutionResult.conversationHistory(),
        //                            chatOptions);
        //                        Flux<ChatResponse> chatResponseFlux1 = baseChatModel.stream(prompt0);
        //                        try {
        //                            CountDownLatch cdl = new CountDownLatch(1);
        //                            chatResponseFlux1.subscribe(chatResponse1 -> {
        //                                System.out.println("hasToolCalls: " + chatResponse1.hasToolCalls());
        //                                if (chatResponse1.hasToolCalls()) {
        //                                    ToolExecutionResult toolExecutionResult1 = toolCallingManager
        //                                        .executeToolCalls(prompt0, chatResponse1);
        //                                    returnDirect[0] = toolExecutionResult1.returnDirect();
        //                                    sink.next("tool call: " + toolExecutionResult1);
        //                                    chatMemory.add(conversationId,
        //                                        toolExecutionResult1.conversationHistory().get(
        //                                            toolExecutionResult1.conversationHistory().size() - 1));
        //                                } else {
        //                                    String chunk = Optional.ofNullable(chatResponse1)
        //                                        .map(x -> x.getResult()).map(x -> x.getOutput())
        //                                        .map(x -> x.getText()).orElse(null);
        //                                    if (chunk != null) {
        //                                        sb.append(chunk);
        //                                        returnDirect[0] = true;
        //                                    }
        //                                }
        //                            }, (e) -> {
        //                                throw new RuntimeException(e);
        //                            }, () -> {
        //                                cdl.countDown();
        //                            });
        //                            cdl.await();
        //                        } catch (Exception e) {
        //                            throw new RuntimeException(e);
        //                        }
        //                        toolExecutionResult = toolCallingManager.executeToolCalls(prompt0,
        //                            chatResponse);
        //                    }
        //                    chatMemory.add(conversationId, new AssistantMessage(sb.toString()));
        //                    sink.next(sb.toString());
        //                    sink.next("finished...");
        //                    sink.complete();
        //
        //                } else {
        //                    String chunk = Optional.ofNullable(chatResponse).map(x -> x.getResult())
        //                        .map(x -> x.getOutput()).map(x -> x.getText()).orElse(null);
        //                    if (chunk != null) {
        //                        sink.next(chunk);
        //                    }
        //
        //                }
        //            }, e -> {
        //                sink.error(e);
        //                e.printStackTrace();
        //            }, () -> {
        //                if (!isToolCall[0]) {
        //                    sink.complete();
        //                }
        //            });
        //        });
    }

    /**
     * 递归处理对话流程：调用AI -> 检查工具调用 -> 执行工具（返回结果）-> 循环，直到无需工具
     * @param messages 当前对话历史
     * @param conversationId 对话ID
     * @param chatOptions 对话配置
     * @param toolManager 工具管理器
     * @param loopCount 循环计数器（防止无限循环）
     * @return 实时推送的结果流
     */
    private Flux<String> processChat(List<Message> messages, String conversationId,
                                     ChatOptions chatOptions, ToolCallingManager toolManager,
                                     int loopCount) {

        // 防止无限循环（最多10轮工具调用）
        if (loopCount > 10) {
            return Flux.just("已达到最大工具调用次数，对话结束");
        }

        // 构建当前请求并调用AI
        Prompt prompt = new Prompt(messages, chatOptions);
        return baseChatModel.stream(prompt).flatMap(chatResponse -> {
            if (chatResponse.hasToolCalls()) {
                // 1. 有工具调用：执行工具并返回结果给前端
                ToolExecutionResult toolResult = toolManager.executeToolCalls(prompt, chatResponse);
                // 工具结果即时推送给前端
                String toolResultStr = "工具调用结果：" + toolResult.conversationHistory()
                    .get(toolResult.conversationHistory().size() - 1);

                // 2. 更新对话历史（添加工具调用结果）
                List<Message> updatedMessages = new ArrayList<>(toolResult.conversationHistory());
                chatMemory.add(conversationId,
                    List.of(updatedMessages.get(updatedMessages.size() - 1)));

                // 3. 递归调用：用更新后的历史继续调用AI（循环）
                return Flux.just(toolResultStr).concatWith(processChat(updatedMessages,
                    conversationId, chatOptions, toolManager, loopCount + 1));
            } else {
                // 无工具调用：返回AI最终回答，结束流程
                String finalAnswer = Optional.ofNullable(chatResponse.getResult())
                    .map(x -> x.getOutput()).map(x -> x.getText()).orElse(null);
                if (finalAnswer == null) {
                    return Mono.empty();
                }
                // 更新历史（添加AI回答）
                chatMemory.add(conversationId, List.of(new AssistantMessage(finalAnswer)));
                return Flux.just(finalAnswer);
            }
        }).onErrorResume(e -> {
            // 异常处理：返回错误信息给前端
            String errorMsg = "对话出错：" + e.getMessage();
            chatMemory.add(conversationId, List.of(new SystemMessage(errorMsg)));
            return Flux.just(errorMsg);
        });
    }

    /**
     * 递归处理流式对话与工具调用
     * https://help.aliyun.com/zh/model-studio/qwen-function-calling#dad2dbe656yhp
     * @param history 当前对话历史（含用户消息）
     * @param conversationId 对话ID
     * @param options 对话配置
     * @param toolManager 工具管理器
     * @param loopCount 工具调用计数器（防止无限循环）
     * @return 流式输出给前端的内容（AI片段/工具结果/最终回答）
     */
    private Flux<String> processStream2(List<Message> history, String conversationId,
                                        ChatOptions options, ToolCallingManager toolManager,
                                        int loopCount) {

        // 安全限制：最多10轮工具调用
        if (loopCount > 10) {
            String endMsg = "已达最大工具调用次数，对话结束。";
            chatMemory.add(conversationId, List.of(new SystemMessage(endMsg))); // 存档系统消息
            return Flux.just(endMsg);
        }

        Prompt prompt = new Prompt(history, options);
        StringBuilder aiFullResponse = new StringBuilder();
        AtomicBoolean isToolCall = new AtomicBoolean(false);
        AtomicBoolean isSendPath = new AtomicBoolean(false);
        AtomicInteger lastContentIdx = new AtomicInteger(-1);

        JSONObject toolCallData = new JSONObject();

        return Flux.create(sink -> {
            baseChatModel.stream(prompt).subscribe(
                chatResponse -> {
                    if (chatResponse.hasToolCalls()) {
                        AssistantMessage.ToolCall toolCall = chatResponse.getResult().getOutput()
                            .getToolCalls().get(0);
                        if (!isToolCall.get()) {
                            isToolCall.set(true);
                            toolCallData.put("toolId", toolCall.id());
                            toolCallData.put("type", toolCall.type());
                            toolCallData.put("arguments", toolCall.arguments());
                            toolCallData.put("name", toolCall.name());
                            sink.next("\n\n开始工具调用:\n\n");
                            sink.next(MessageFormat.format("[toolId={0} type={1} name={2}]\n",
                                toolCall.id(), toolCall.type(), toolCall.name()));
                        } else {
                            toolCallData.put("arguments",
                                toolCallData.getString("arguments") + toolCall.arguments());
                        }
                        if (Objects.equals(toolCallData.getString("name"), "writeFile")) {
                            String arguments = toolCallData.getString("arguments");
                            int startIdx = arguments.indexOf("\"path\": \"");
                            boolean hasSpace = true;
                            if (startIdx < 0) {
                                startIdx = arguments.indexOf("\"path\":\"");
                                hasSpace = false;
                            }
                            if (startIdx >= 0) {
                                int endIdx = arguments.indexOf(",", startIdx);
                                if (endIdx >= 0) {
                                    String path = arguments.substring(startIdx + (hasSpace ? 9 : 8),
                                        endIdx);
                                    if (!isSendPath.get()) {
                                        isSendPath.set(true);
                                        sink.next("\n\n开始写入文件: " + path + "\n\n");
                                        sink.next("\n\n==================================\n\n");
                                        sink.next("\n```\n");
                                    }
                                }
                            }

                            startIdx = arguments.indexOf("\"content\": \"");
                            hasSpace = true;
                            if (startIdx < 0) {
                                startIdx = arguments.indexOf("\"content\":\"");
                                hasSpace = false;
                            }
                            if (startIdx >= 0) {
                                if (lastContentIdx.get() < 0) {
                                    sink.next(arguments.substring(startIdx + (hasSpace ? 12 : 11)));
                                    lastContentIdx.set(arguments.length());
                                } else {
                                    sink.next(arguments.substring(lastContentIdx.get()));
                                    lastContentIdx.set(arguments.length());
                                }
                            }
                        } else {
                            sink.next(toolCall.arguments());
                        }
                    } else {
                        String chunk = Optional.ofNullable(chatResponse.getResult())
                            .map(x -> x.getOutput()).map(x -> x.getText()).orElse(null);
                        if (chunk != null) {
                            aiFullResponse.append(chunk);
                            sink.next(chunk);
                        }
                    }
                },
                error -> {
                    String errorMsg = "对话出错：" + error.getMessage();
                    chatMemory.add(conversationId, List.of(new SystemMessage(errorMsg))); // 存档错误消息
                    sink.error(new RuntimeException(errorMsg));
                    sink.complete();
                },
                () -> {
                    try {
                        Message aiAnswerMsg = new AssistantMessage(aiFullResponse.toString());
                        chatMemory.add(conversationId, aiAnswerMsg);
                        Prompt prompt0 = new Prompt(chatMemory.get(conversationId), options);
                        if (isToolCall.get()) {
                            ChatResponse completeResponse = ChatResponse.builder()
                                .generations(
                                    List.of(
                                        new Generation(new AssistantMessage("", Map.of(),
                                            List.of(new AssistantMessage.ToolCall(
                                                toolCallData.getString("toolId"),
                                                toolCallData.getString("type"),
                                                toolCallData.getString("name"),
                                                convertToValidJson(
                                                    toolCallData.getString("arguments"))))))))
                                .build();

                            // 执行工具调用
                            System.out
                                .println("工具调用参数: " + toolCallData.getString("arguments") + "\n");
                            ToolExecutionResult toolResult = toolManager.executeToolCalls(prompt0,
                                completeResponse);

                            // 存档工具调用
                            List<Message> toolMsgs = toolResult.conversationHistory().subList(
                                toolResult.conversationHistory().size() - 2,
                                toolResult.conversationHistory().size());
                            chatMemory.add(conversationId, toolMsgs);

                            if (Objects.equals(toolCallData.getString("name"), "writeFile")) {
                                sink.next("\n```\n");
                                sink.next("\n\n==================================\n\n");
                            }

                            // 推送工具结果
                            sink.next("\n\n工具执行结果：" + JSON.toJSONString(toolMsgs.get(1)) + "\n\n");

                            // 递归进入下一轮（继续调用AI）
                            processStream2(chatMemory.get(conversationId), conversationId, options,
                                toolManager, loopCount + 1).subscribe(sink::next,
                                    sink::error,
                                    sink::complete
                            );
                        } else {
                            sink.complete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String errorMsg = "处理响应失败：" + e.getMessage();
                        chatMemory.add(conversationId, List.of(new SystemMessage(errorMsg)));
                        sink.error(new RuntimeException(errorMsg));
                    }
                });
        });
    }

    /**
     * 将可能包含外层引号的错误JSON字符串转换为标准JSON字符串
     * @param invalidJson 可能存在格式问题的JSON字符串（如外层带引号）
     * @return 标准JSON字符串
     */
    public static String convertToValidJson(String invalidJson) {
        if (invalidJson == null || invalidJson.trim().isEmpty()) {
            throw new IllegalArgumentException("输入JSON字符串不能为空");
        }

        // 去除首尾空格
        String trimmedJson = invalidJson.trim();

        // 处理外层带双引号的情况（错误格式特征）
        if (trimmedJson.startsWith("\"") && trimmedJson.endsWith("\"")) {
            System.out.println("source: " + invalidJson);
            String temp = String.format("{\"a\":%s}", invalidJson);
            System.out.println("temp: " + temp);
            String a = JSON.parseObject(temp).getString("a");
            System.out.println("a: " + a);
            String escaped = JSON.parseObject(a).toJSONString();
            System.out.println("escaped: " + escaped);
            return escaped;
        }

        // 若格式正常则直接返回
        return trimmedJson;
    }
}
