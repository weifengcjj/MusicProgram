package com.weifeng.musicprogram.service;

import com.weifeng.musicprogram.Model.ListSong;

import java.util.List;

/**
 * 歌单歌曲service
 */
public interface ListSongService {
    /**
     * 增加
     */
    boolean insert(ListSong listsong);

    /**
     * 修改
     */
    boolean update(ListSong listsong);

    /**
     * 删除
     */
    boolean remove(Integer songId,Integer songlistId);

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
