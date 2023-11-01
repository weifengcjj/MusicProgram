package com.weifeng.musicprogram.service.impl;

import com.weifeng.musicprogram.Dao.ConsumerMapper;
import com.weifeng.musicprogram.Model.Consumer;
import com.weifeng.musicprogram.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    /**
     * 增加
     *
     * @param Consumer
     */
    @Override
    public boolean insert(Consumer Consumer) {
        return consumerMapper.insert(Consumer)>0;
    }

    /**
     * 修改
     *
     * @param Consumer
     */
    @Override
    public boolean update(Consumer Consumer) {
        return consumerMapper.update(Consumer)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean remove(Integer id) {
        return consumerMapper.remove(id)>0;
    }

    /**
     * 根据主键查
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectById(Integer id) {
        return consumerMapper.selectById(id);
    }

    /**
     * 查所有
     *
     * @return
     */
    @Override
    public List<Consumer> allConsumer() {
        return consumerMapper.allConsumer();
    }

    /**
     * 修改密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean updatePassword(String username, String password) {
        return consumerMapper.updatePassword(username,password)>0;
    }

    /**
     * 根据账号查询
     *
     * @param username
     * @return
     */
    @Override
    public Consumer consumerByName(String username) {
        return consumerMapper.consumerByName(username);
    }
}
