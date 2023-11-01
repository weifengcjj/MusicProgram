package com.weifeng.musicprogram.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

//用户
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consumer implements Serializable {
    /*主键*/
    private int id;
    /*账号*/
    private String username;
    /*密码*/
    private String password;
    /*性别*/
    private Byte sex;
    /*电话*/
    private String phone_num;
    /*邮箱*/
    private String email;
    /*生日*/
    private Date birth;
    /*简介*/
    private String introducation;
    /*地区*/
    private String location;
    /*头像*/
    private String avator;
    /*创建时间*/
    private Date create_time;
    /*修改时间*/
    private Date update_time;


}
