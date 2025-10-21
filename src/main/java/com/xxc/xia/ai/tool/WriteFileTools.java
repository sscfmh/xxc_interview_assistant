package com.xxc.xia.ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.UUID;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/18 22:12
 */
@Component
public class WriteFileTools {

    String prefix = UUID.randomUUID().toString().replace("-", "");

    @Tool(description = "在 path 创建文件，并写入 content, 示例：{\"path\": \"index.jsx\", \"content\":\"import {useState} from \"React\";\n// 。。。\n\"}")
    public String writeFile(@ToolParam(description = "file path") String path,
                            @ToolParam(description = "file content") String content) {
        File file = new File("./temp/" + prefix + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
