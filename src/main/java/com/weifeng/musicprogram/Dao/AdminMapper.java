package com.weifeng.musicprogram.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {
    /*验证密码是否正确*/
    int verifyPassword(@Param("username") String username, @Param("password")String password);
}
