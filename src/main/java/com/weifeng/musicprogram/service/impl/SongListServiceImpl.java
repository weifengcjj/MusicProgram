package com.weifeng.musicprogram.service.impl;

import com.weifeng.musicprogram.Dao.SongListMapper;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;
import com.weifeng.musicprogram.Model.SongList;
import com.weifeng.musicprogram.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapper songListMapper;
    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList)>0;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean remove(Integer id) {
        return songListMapper.remove(id)>0;
    }

    /**
     * 根据主键查
     *
     * @param id
     * @return
     */
    @Override
    public SongList selectSongListById(Integer id) {

        return songListMapper.selectSongListById(id);
    }

    /**
     * 查所有歌单
     *
     * @return
     */
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }

    /**
     * 精确查询歌单列表 ---标题
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> SongListByTitle(String title) {
        return songListMapper.SongListByTitle(title);
    }

    /**
     * 模糊查询歌单列表 ---标题
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> SongListLikeTitle(String title) {
        return songListMapper.SongListLikeTitle(title);
    }

    /**
     * 模糊查询歌单列表 ---style 风格
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> SongListLikeStyle(String style) {
        return songListMapper.SongListLikeStyle(style);
    }
}
