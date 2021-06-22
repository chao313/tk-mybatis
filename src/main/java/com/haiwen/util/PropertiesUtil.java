package com.haiwen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * 用于读取配置文件中的数据
 * <p>
 * {@link SpringContextUtil}
 */
public class PropertiesUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Environment ENV = AwareUtil.applicationContext.getBean("environment", Environment.class);

    private PropertiesUtil() {
        LOGGER.info("PropertiesUtils init");
    }

    public static String getProperty(String properties) {
        return ENV.getProperty(properties);
    }
}