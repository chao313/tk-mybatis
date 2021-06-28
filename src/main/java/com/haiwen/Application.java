package com.haiwen;

import lombok.extern.slf4j.Slf4j;
//import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@SpringBootApplication
@Slf4j
public class Application implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * springboot 默认的就是 utf-8
     * 修改为iso-8859-1就会出现乱码
     *
     * @return
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        UnzipService.generateUnzipBash("idMapName");
//        Configuration config =new Configuration();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
//        sqlSessionFactory.openSession().getMapper()
    }

}


