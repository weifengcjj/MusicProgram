package com.weifeng.musicprogram.service;

import com.weifeng.musicprogram.Model.Consumer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户service接口
 */
public interface ConsumerService {
    /**
     * 增加
     */
    boolean insert(Consumer Consumer);

    /**
     * 修改
     */
    boolean update(Consumer Consumer);

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
    Consumer selectById(Integer id);
    /**
     * 查所有
     * @return
     */
    List<Consumer> allConsumer();
//    /**
//     * 模糊查询 ---姓名
//     * @param
//     * @return
//     */

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
//    List<Consumer> ConsumerByName(String name);
    boolean updatePassword(@Param("username") String username, @Param("password")String password);

    /**
     * 根据账号查询
     * @return
     */
    Consumer consumerByName(String username);
}
