package com.weifeng.musicprogram.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

//歌手
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Singer implements Serializable {
    /*主键*/
    private int id;
    /*账号*/
    private String name;
    /*性别*/
    private Byte sex;
    /*图片*/
    private String pic;
    /*生日*/
    private Date birth;
    /*地区*/
    private String location;
    /*简介*/
    private String introducation;
}
