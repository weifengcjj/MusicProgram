package com.weifeng.musicprogram.Dao;

import com.weifeng.musicprogram.Model.Consumer;
import com.weifeng.musicprogram.Model.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConsumerMapper {
    /**
     * 增加
     */
    int insert(Consumer Consumer);

    /**
     * 修改
     */
    int update(Consumer Consumer);

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
    int updatePassword(@Param("username") String username, @Param("password")String password);

    /**
     * 根据账号查询
     * @return
     */
    Consumer consumerByName(String username);

}
