package com.haiwen.mybatis.tk.base;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author chao
 * @version 1.0
 * @description: TODO
 * @date 2021/6/22 上午10:28
 */
public interface BaseRepository<T> extends Mapper<T>, IdsMapper<T>, InsertListMapper<T> {

}
