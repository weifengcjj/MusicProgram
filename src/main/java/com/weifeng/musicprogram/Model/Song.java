package com.weifeng.musicprogram.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

//歌曲
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Song implements Serializable {
    /*主键*/
    private int id;
    /*歌手id*/
    private int singerId;
    /*歌手名字*/
    private String name;
    /*歌曲简介*/
    private String introducation;
    /*创建时间*/
    private Date createTime;
    /*修改时间*/
    private Date updateTime;
    /*图片*/
    private String pic;
    /*歌词*/
    private String lyric;
    /*歌曲路径*/
    private String url;

}
