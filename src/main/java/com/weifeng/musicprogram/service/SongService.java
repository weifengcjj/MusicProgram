package com.weifeng.musicprogram.service;

import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;

import java.util.List;

/**
 * 歌曲service接口
 */
public interface SongService {
    /**
     * 增加
     */
    boolean insert(Song song);

    /**
     * 修改
     */
    boolean update(Song song);

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
    Song selectSongById(Integer id);
    /**
     * 查所有
     * @return
     */
    List<Song> allSong();

    /**
     * 精确查询 ---歌名
     * @param name
     * @return
     */
    List<Song> SongByName(String name);

    /**
     * 模糊查询 ---歌名
     * @param name
     * @return
     */
    List<Song> likeSongByName(String name);

    /**
     * 模糊查询 ---歌手
     * @param id
     * @return
     */
    List<Song> SongBySiger(Integer id);
}
