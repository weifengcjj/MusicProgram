package com.weifeng.musicprogram.Dao;

import com.weifeng.musicprogram.Model.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SingerMapper {
    /**
     * 增加
     */
    int insert(Singer singer);

    /**
     * 修改
     */
    int update(Singer singer);

    /**
     * 删除
     */
    int remove(Integer id);

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
