package com.weifeng.musicprogram.service;

import com.weifeng.musicprogram.Model.Singer;

import java.util.List;

public interface SingerService {
    /**
     * 增加
     */
    boolean insert(Singer singer);

    /**
     * 修改
     */
    boolean update(Singer singer);

    /**
     * 删除
     */
    boolean remove(Integer id);

    /**
     * 查找
     */
    /**
     *  根据主键查
     * @param id
     * @return
     */
    Singer selectById(Integer id);
    /**
     * 查所有
     * @return
     */
    List<Singer> allSinger();
    /**
     * 模糊查询 ---姓名
     * @param name
     * @return
     */
    List<Singer> singerByName(String name);

    /**
     * 根据性别
     * @param sex
     * @return
     */
    List<Singer> singerBySex(Integer sex);
}
