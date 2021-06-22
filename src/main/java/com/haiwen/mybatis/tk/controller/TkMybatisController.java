package com.haiwen.mybatis.tk.controller;

import com.haiwen.core.framework.response.Code;
import com.haiwen.core.framework.response.Response;
import com.haiwen.mybatis.tk.dao.vo.TkMenu;
import com.haiwen.mybatis.tk.service.ITkMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chao
 * @version 1.0
 * @description: 配置controller
 * @date 2021/6/22 上午10:25
 */
@RestController
@RequestMapping(value = "/TkMybatisController")
public class TkMybatisController {

    @Autowired
    private ITkMenuService iTkMenuService;

    /**
     *
     */
    @ApiOperation(value = "插入kMenu")
    @GetMapping("/insertTkMenu")
    public Response insertTkMenu(TkMenu tkMenu) throws Exception {
        iTkMenuService.insertTkMenu(tkMenu);
        return Response.OK();
    }

    /**
     * 根据menuName来查询
     */
    @ApiOperation(value = "根据menuName来查询")
    @GetMapping("/getTkMenuByMenuName")
    public Response getTkMenuByMenuName(String menuName) throws Exception {
        List<TkMenu> tkMenuByMenuName = iTkMenuService.getTkMenuByMenuName(menuName);
        Response response = new Response();
        response.setCode(Code.System.OK);
        response.setContent(tkMenuByMenuName);
        return response;
    }

    /**
     * 根据menuName来查询
     */
    @ApiOperation(value = "查询全部的数据")
    @GetMapping("/selectAll")
    public Response selectAll() throws Exception {
        List<TkMenu> tkMenus = iTkMenuService.selectAll();
        Response response = new Response();
        response.setCode(Code.System.OK);
        response.setContent(tkMenus);
        return response;
    }


}
