package com.xxc.xia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Desc...
 *
 * @Author xxc
 * @Date 2022/10/19 21:56
 */
@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //        // 设置允许跨域的路由
        //        registry.addMapping("/**")
        //            // 设置允许跨域请求的域名
        //            .allowedOrigins("*") // 注意此处
        //            // 是否允许证书（cookies）
        //            .allowCredentials(true)
        //            // 设置允许的方法
        //            .allowedMethods("*")
        //            // 跨域允许时间
        //            .maxAge(3600);

        // 设置允许跨域的路由

        registry.addMapping("/**")
            // 设置允许跨域请求的域名
            .allowedOriginPatterns("*") // 注意此处
            // 是否允许证书（cookies）
            .allowCredentials(true)
            // 设置允许的方法
            .allowedMethods("*")
            //                .allowedMethods("PUT", "DELETE","GET","POST")
            // 跨域允许时间
            .maxAge(3600);

    }
}