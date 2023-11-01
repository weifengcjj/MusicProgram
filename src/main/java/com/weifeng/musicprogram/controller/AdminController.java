package com.weifeng.musicprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifeng.musicprogram.service.AdminService;
import com.weifeng.musicprogram.service.impl.AdminServiceImpl;
import com.weifeng.musicprogram.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 判断管理员登录
     * @return
     */
    @PostMapping("/admin/login/status")
    @ResponseBody
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObjec=new JSONObject();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        System.out.println(name+"---"+password);
        boolean b = adminService.verifyPassword(name, password);
        if(b){
            jsonObjec.put(Consts.CODE,1);
            jsonObjec.put(Consts.MESSAGE,"管理员登录成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObjec;
        }

        jsonObjec.put(Consts.CODE,0);
        jsonObjec.put(Consts.MESSAGE,"用户名或密码错误！");
        return jsonObjec;
    }
}






















