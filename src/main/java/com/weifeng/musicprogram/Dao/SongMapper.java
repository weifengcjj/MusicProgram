package com.weifeng.musicprogram.Dao;

import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {
    /**
     * 增加
     */
    int insert(Song song);

    /**
     * 修改
     */
    int update(Song song);

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
