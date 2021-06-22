package com.haiwen.mybatis.tk.service;

import com.haiwen.mybatis.tk.dao.vo.TkMenu;

import java.util.List;

/**
 * @author chao
 * @version 1.0
 * @description: TODO
 * @date 2021/6/22 上午10:40
 */
public interface ITkMenuService {

    TkMenu getTkMenu();


    void insertTkMenu(TkMenu tkMenu);


    List<TkMenu> getTkMenuByMenuName(String menuName);

    List<TkMenu> selectAll();


}
