package com.haiwen.mybatis.tk.dao.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haiwen.mybatis.plugin.ChangeLog;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author chao
 * @version 1.0
 * @description: 配置vo
 * @date 2021/6/22 上午10:25
 */
@Data
@ChangeLog
public class TkMenu {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    protected Long id;

    @Column(name = "menu_name")
    private String menuName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    protected Date createTime;

    @Column(name = "create_user")
    protected String createUser;
}