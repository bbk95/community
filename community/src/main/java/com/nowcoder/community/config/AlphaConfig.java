package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig {
    @Bean //方法名就是bean的名字，bean的名字是以方法命名的
    public SimpleDateFormat simpleDateFormat(){ //这段的意思是：方法返回的对象将被装配到容器里
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
