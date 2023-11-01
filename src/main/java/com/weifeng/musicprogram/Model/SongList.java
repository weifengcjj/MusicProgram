package com.weifeng.musicprogram.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

//歌单
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SongList implements Serializable {
    /*主键*/
    private Integer id;
    /*歌单标题*/
    private String title;
    /*歌单图片*/
    private String pic;
    /*歌单简介*/
    private String introducation;
    /*歌单风格*/
    private String style;

}
