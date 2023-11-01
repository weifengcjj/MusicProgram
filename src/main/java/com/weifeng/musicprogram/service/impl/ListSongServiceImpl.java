package com.weifeng.musicprogram.service.impl;

import com.weifeng.musicprogram.Dao.ListSongMapper;
import com.weifeng.musicprogram.Model.ListSong;
import com.weifeng.musicprogram.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单歌曲实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;
    /**
     * 增加
     *
     * @param listsong
     */
    @Override
    public boolean insert(ListSong listsong) {
        return listSongMapper.insert(listsong)>0;
    }

    /**
     * 修改
     *
     * @param listsong
     */
    @Override
    public boolean update(ListSong listsong) {
        return listSongMapper.update(listsong)>0;
    }

    /**
     * 删除
     *
     * @param songId
     * @param songlistId
     */
    @Override
    public boolean remove(Integer songId,Integer songlistId) {
        return listSongMapper.remove(songId,songlistId)>0;
    }

    /**
     * 根据主键查
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectListSongById(Integer id) {
        return listSongMapper.selectListSongById(id);
    }

    /**
     * 查所有
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    /**
     * 根据歌单id，查询所有的歌曲
     *
     * @param id
     * @return
     */
    @Override
    public List<ListSong> ListSongBySongListId(Integer id) {
        return listSongMapper.ListSongBySongListId(id);
    }
}
