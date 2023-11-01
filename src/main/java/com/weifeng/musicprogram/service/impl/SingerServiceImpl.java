package com.weifeng.musicprogram.service.impl;

import com.weifeng.musicprogram.Dao.SingerMapper;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手service实现类
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;
    /**
     * 增加
     * @param singer
     */
    @Override
    public boolean insert(Singer singer) {
        int insert = singerMapper.insert(singer);
        if(insert==1){
            return true;
        }
        return false;
    }

    /**
     * 修改
     * @param singer
     */
    @Override
    public boolean update(Singer singer) {
        int update = singerMapper.update(singer);
        if(update==1){
            return true;
        }
        return false;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public boolean remove(Integer id) {
        int remove = singerMapper.remove(id);
        if(remove==1){
            return true;
        }
        return false;
    }

    /**
     * 根据主键查
     * @param id
     * @return
     */
    @Override
    public Singer selectById(Integer id) {
        Singer singer = singerMapper.selectById(id);
        return singer;
    }

    /**
     * 查所有
     * @return
     */
    @Override
    public List<Singer> allSinger() {
        List<Singer> singers = singerMapper.allSinger();
        return singers;
    }

    /**
     * 模糊查询 ---姓名
     * @param name
     * @return
     */
    @Override
    public List<Singer> singerByName(String name) {
        List<Singer> singers = singerMapper.singerByName(name);
        return singers;
    }

    /**
     * 根据性别
     * @param sex
     * @return
     */
    @Override
    public List<Singer> singerBySex(Integer sex) {
        List<Singer> singers = singerMapper.singerBySex(sex);
        return singers;
    }
}
