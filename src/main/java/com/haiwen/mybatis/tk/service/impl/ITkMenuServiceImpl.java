package com.haiwen.mybatis.tk.service.impl;

import com.haiwen.mybatis.tk.dao.TkMenuRepository;
import com.haiwen.mybatis.tk.dao.vo.TkMenu;
import com.haiwen.mybatis.tk.service.ITkMenuService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chao
 * @version 1.0
 * @description: TODO
 * @date 2021/6/22 上午10:42
 */
@Service
public class ITkMenuServiceImpl implements ITkMenuService {

    @Resource
    private TkMenuRepository menuRepository;

    @Override
    public TkMenu getTkMenu() {
        return null;
    }

    @Override
    public void insertTkMenu(TkMenu tkMenu) {
        menuRepository.insert(tkMenu);
    }

    @Override
    public List<TkMenu> getTkMenuByMenuName(String menuName) {
        Example example = new Example(TkMenu.class);//实例化
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("menuName", menuName);
        List<TkMenu> tkMenus = menuRepository.selectByExample(example);
        return tkMenus;
    }

    @Override
    public List<TkMenu> selectAll() {
        return menuRepository.selectAll();
    }

}
