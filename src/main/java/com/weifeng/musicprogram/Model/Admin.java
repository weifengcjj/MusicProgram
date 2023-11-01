package com.weifeng.musicprogram.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

//管理员
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin implements Serializable {
    /*主键*/
    private int id;
    /*账号*/
    private String name;
    /*密码*/
    private String password;
}
