package com.weifeng.musicprogram.Dao;

import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;
import com.weifeng.musicprogram.Model.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单
 */
@Mapper
@Repository
public interface SongListMapper {
    /**
     * 增加
     */
    int insert(SongList songList);

    /**
     * 修改
     */
    int update(SongList songList);

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
    SongList selectSongListById(Integer id);
    /**
     * 查所有歌单
     * @return
     */
    List<SongList> allSongList();

    /**
     * 精确查询歌单列表 ---标题
     * @param title
     * @return
     */
    List<SongList> SongListByTitle(String title);

    /**
     * 模糊查询歌单列表 ---标题
     * @param title
     * @return
     */
    List<SongList> SongListLikeTitle(String title);

    /**
     * 模糊查询歌单列表 ---style 风格
     * @param style
     * @return
     */
    List<SongList> SongListLikeStyle(String style);

}
