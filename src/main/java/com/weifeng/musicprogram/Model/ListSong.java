package com.weifeng.musicprogram.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 歌单歌曲类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListSong implements Serializable {
    /*主键*/
    private Integer id;

    /*歌曲id*/
    private Integer songId;

    /*歌单id*/
    private Integer songListId;
}
