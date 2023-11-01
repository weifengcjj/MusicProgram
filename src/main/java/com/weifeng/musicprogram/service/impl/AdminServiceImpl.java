package com.weifeng.musicprogram.service.impl;

import com.weifeng.musicprogram.Dao.AdminMapper;
import com.weifeng.musicprogram.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        int i = adminMapper.verifyPassword(username, password);
        if(i!=0){
            return true;
        }
        return false;
    }
}
