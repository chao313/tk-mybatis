package com.haiwen.mybatis.tk.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author chao
 * @version 1.0
 * @description: 配置tkMybatis
 * @date 2021/6/22 上午10:25
 */
@Configuration
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.haiwen.mybatis.tk.dao")
public class TkMyBatisDalConfig {
}
