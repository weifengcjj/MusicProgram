package com.weifeng.musicprogram.Dao;

import com.weifeng.musicprogram.Model.ListSong;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单歌曲Dao
 */
@Mapper
@Repository
public interface ListSongMapper {
    /**
     * 增加
     */
    int insert(ListSong listsong);

    /**
     * 修改
     */
    int update(ListSong listsong);

    /**
     * 删除
     */
    int remove(@Param("songId") Integer songId, @Param("songlistId")Integer songlistId);

    /**
     * 查找
     */
    /**
     *  根据主键查
     * @param id
     * @return
     */
    ListSong selectListSongById(Integer id);
    /**
     * 查所有
     * @return
     */
    List<ListSong> allListSong();

//    /**
//     * 模糊查询 ---标题
//     * @param title
//     * @return
//     */
//    List<ListSong> ListSongByName(String title);

    /**
     * 根据歌单id，查询所有的歌曲
     * @param id
     * @return
     */
    List<ListSong> ListSongBySongListId(Integer id);

}
