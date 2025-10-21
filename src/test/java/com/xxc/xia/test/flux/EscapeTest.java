package com.xxc.xia.test.flux;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/19 18:12
 */
public class EscapeTest {

    @Test
    public void test() {
//        出现调用 WriteFileTools/tmp/vue_sample/src/App.vue <template>\n  <div>\n    <h1>{{ message }}</h1>\n    <HelloWorld />\n  </div>\n</template>\n\n<script>\nimport HelloWorld from './components/HelloWorld.vue';\n\nexport default {\n  components: {\n    HelloWorld\n  },\n  data() {\n    return {\n      message: 'Vue Sample Project'\n    }\n  }\n}\n</script>
//                source: "{\"path\": \"/tmp/vue_sample/src/components/HelloWorld.vue\", \"content\": \"<template>\\\\n  <div class=\\\"hello\\\">\\\\n    <p>This is a sample component</p>\\\\n    <p>Counter: {{ count }}</p>\\\\n    <button @click=\\\"increment\\\">Increment</button>\\\\n  </div>\\\\n</template>\\\\n\\\\n<script>\\\\nexport default {\\\\n  data() {\\\\n    return {\\\\n      count: 0\\\\n    }\\\\n  },\\\\n  methods: {\\\\n    increment() {\\\\n      this.count++\\\\n    }\\\\n  }\\\\n}\\\\n</script>\\\\n\\\\n<style scoped>\\\\n.hello {\\\\n  margin-top: 20px;\\\\n  padding: 10px;\\\\n  border: 1px solid #ccc;\\\\n}\\\\n</style>\"}"
//        escaped: {"path": "/tmp/vue_sample/src/components/HelloWorld.vue", "content": "<template>\\\\n  <div class=\\"hello\\">\\\\n    <p>This is a sample component</p>\\\\n    <p>Counter: {{ count }}</p>\\\\n    <button @click=\\"increment\\">Increment</button>\\\\n  </div>\\\\n</template>\\\\n\\\\n<script>\\\\nexport default {\\\\n  data() {\\\\n    return {\\\\n      count: 0\\\\n    }\\\\n  },\\\\n  methods: {\\\\n    increment() {\\\\n      this.count++\\\\n    }\\\\n  }\\\\n}\\\\n</script>\\\\n\\\\n<style scoped>\\\\n.hello {\\\\n  margin-top: 20px;\\\\n  padding: 10px;\\\\n  border: 1px solid #ccc;\\\\n}\\\\n</style>"}
//        工具调用参数: "{\"path\": \"/tmp/vue_sample/src/components/HelloWorld.vue\", \"content\": \"<template>\\\\n  <div class=\\\"hello\\\">\\\\n    <p>This is a sample component</p>\\\\n    <p>Counter: {{ count }}</p>\\\\n    <button @click=\\\"increment\\\">Increment</button>\\\\n  </div>\\\\n</template>\\\\n\\\\n<script>\\\\nexport default {\\\\n  data() {\\\\n    return {\\\\n      count: 0\\\\n    }\\\\n  },\\\\n  methods: {\\\\n    increment() {\\\\n      this.count++\\\\n    }\\\\n  }\\\\n}\\\\n</script>\\\\n\\\\n<style scoped>\\\\n.hello {\\\\n  margin-top: 20px;\\\\n  padding: 10px;\\\\n  border: 1px solid #ccc;\\\\n}\\\\n</style>\"}"

        // String source = "{\"path\": \"/tmp/vue_sample/src/components/HelloWorld.vue\", \"content\": \"<template>\\\\n  <div class=\\\"hello\\\">\\\\n    <p>This is a sample component</p>\\\\n    <p>Counter: {{ count }}</p>\\\\n    <button @click=\\\"increment\\\">Increment</button>\\\\n  </div>\\\\n</template>\\\\n\\\\n<script>\\\\nexport default {\\\\n  data() {\\\\n    return {\\\\n      count: 0\\\\n    }\\\\n  },\\\\n  methods: {\\\\n    increment() {\\\\n      this.count++\\\\n    }\\\\n  }\\\\n}\\\\n</script>\\\\n\\\\n<style scoped>\\\\n.hello {\\\\n  margin-top: 20px;\\\\n  padding: 10px;\\\\n  border: 1px solid #ccc;\\\\n}\\\\n</style>\"}";
        String source = "\"{\\\"path\\\": \\\"/tmp/vue_sample/src/components/HelloWorld.vue\\\", \\\"content\\\": \\\"<template>\\\\\\\\n  <div class=\\\\\\\"hello\\\\\\\">\\\\\\\\n    <p>This is a sample component</p>\\\\\\\\n    <p>Counter: {{ count }}</p>\\\\\\\\n    <button @click=\\\\\\\"increment\\\\\\\">Increment</button>\\\\\\\\n  </div>\\\\\\\\n</template>\\\\\\\\n\\\\\\\\n<script>\\\\\\\\nexport default {\\\\\\\\n  data() {\\\\\\\\n    return {\\\\\\\\n      count: 0\\\\\\\\n    }\\\\\\\\n  },\\\\\\\\n  methods: {\\\\\\\\n    increment() {\\\\\\\\n      this.count++\\\\\\\\n    }\\\\\\\\n  }\\\\\\\\n}\\\\\\\\n</script>\\\\\\\\n\\\\\\\\n<style scoped>\\\\\\\\n.hello {\\\\\\\\n  margin-top: 20px;\\\\\\\\n  padding: 10px;\\\\\\\\n  border: 1px solid #ccc;\\\\\\\\n}\\\\\\\\n</style>\\\"}\"";
        String temp = String.format("{\"a\":%s}", source);
        System.out.println("source: " + temp);
        String a = JSON.parseObject(temp).getString("a");
        System.out.println("a: " + a);
        String escaped = JSON.parseObject(a).toJSONString();
        System.out.println("escaped: " + escaped);
        JSONObject jsonObject = JSON.parseObject(escaped);
        System.out.println(jsonObject);
    }
}
