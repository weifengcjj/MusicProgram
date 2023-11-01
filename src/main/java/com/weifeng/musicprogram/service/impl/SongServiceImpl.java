package com.weifeng.musicprogram.service.impl;

import com.weifeng.musicprogram.Dao.SongMapper;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;
import com.weifeng.musicprogram.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;
    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song)>0;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean remove(Integer id) {
        return songMapper.remove(id)>0;
    }

    /**
     * 根据主键查
     *
     * @param id
     * @return
     */
    @Override
    public Song selectSongById(Integer id) {
        return songMapper.selectSongById(id);
    }

    /**
     * 查所有
     *
     * @return
     */
    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    /**
     * 精确查询 ---歌名
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> SongByName(String name) {
        return songMapper.SongByName(name);
    }

    /**
     * 模糊查询 ---歌名
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> likeSongByName(String name) {
        return songMapper.likeSongByName(name);
    }

    /**
     * 模糊查询 ---歌手
     *
     * @param id
     * @return
     */
    @Override
    public List<Song> SongBySiger(Integer id) {
        return songMapper.SongBySiger(id);
    }
}
