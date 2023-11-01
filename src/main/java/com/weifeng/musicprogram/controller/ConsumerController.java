package com.weifeng.musicprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifeng.musicprogram.Model.Consumer;
import com.weifeng.musicprogram.service.ConsumerService;
import com.weifeng.musicprogram.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 歌手接口
 */
@RestController
@RequestMapping("/consumer")
@CrossOrigin("*")
public class ConsumerController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ConsumerController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加歌手
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addconsumer(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password").trim();
        String sex=request.getParameter("sex").trim();
        String phone_num=request.getParameter("phone_num").trim();
        String email=request.getParameter("email").trim();
        String birth=request.getParameter("birth").trim();
        String introducation=request.getParameter("introducation").trim();
        String location=request.getParameter("location").trim();
        String avator=request.getParameter("avator").trim();
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"密码不能为空");
            return jsonObject;
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try {
            date=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Consumer consumer=new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhone_num(phone_num);
        consumer.setEmail(email);
        consumer.setBirth(date);
        consumer.setIntroducation(introducation);
        consumer.setLocation(location);
        consumer.setAvator(avator);

        boolean insert = consumerService.insert(consumer);
        if(insert){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MESSAGE,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MESSAGE,"添加失败");
        return jsonObject;
    }

    /**
     * 修改歌手
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updateconsumer(HttpServletRequest request) throws ParseException {
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password").trim();
        String sex=request.getParameter("sex").trim();
        String phone_num=request.getParameter("phone_num").trim();
        String email=request.getParameter("email").trim();
        String birth=request.getParameter("birth").trim();
        String introducation=request.getParameter("introducation").trim();
        String location=request.getParameter("location").trim();
//        String avator=request.getParameter("avator").trim();
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"密码不能为空");
            return jsonObject;
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateFormat.parse(birth);
        Consumer consumer=new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhone_num(phone_num);
        consumer.setEmail(email);
        consumer.setBirth(date);
        consumer.setIntroducation(introducation);
        consumer.setLocation(location);
        System.out.println(consumer);

        boolean insert = consumerService.update(consumer);
        if(insert){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MESSAGE,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MESSAGE,"修改失败");
        return jsonObject;
    }

    /**
     * 删除歌手
     * @param request
     * @return
     */
    @GetMapping("/delete/{id}")
    public Object removeconsumer(HttpServletRequest request, @PathVariable("id") int id){
        boolean remove = consumerService.remove(id);
        return remove;
    }

    /**
     * 查询一个用户  id
     * @param request
     * @return
     */
    @GetMapping("/selectById/{id}")
    public Object Oneconsumer(HttpServletRequest request,@PathVariable("id") int id){
        return consumerService.selectById(id);
    }

    /**
     * 查所有歌手
     * @param request
     * @return
     */
    @GetMapping("/allConsumer")
    public Object allConsumer(HttpServletRequest request){
        return consumerService.allConsumer();
    }

    /**
     * 根据账号查歌手
     * @param request
     * @param username
     * @return
     */
    @GetMapping("/consumerByName/{username}")
    public Object consumerByName(HttpServletRequest request,@PathVariable("username") String username){
        return consumerService.consumerByName(username);
    }



    //todo 更新前端用户图片
    @PostMapping("/updateConsumerPic")
    public Object updateConsumerPic(@RequestParam("file")MultipartFile file, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }

        // 获取静态资源目录路径
        String resourceStaticPath;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/images/");
            resourceStaticPath = resource.getFile().getAbsolutePath() + File.separator;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "获取静态资源目录失败" + e.getMessage());
            return jsonObject;
        }
        System.out.println(resourceStaticPath);
        // 文件名：当前时间戳和原文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();

        // 实际的文件地址
        String resourcePath ="D:\\java\\MusicProgram\\src\\main\\resources\\static\\images\\";
        File resourceDest = new File(resourceStaticPath + fileName);

        try {
            // 保存文件到 target 目录
            file.transferTo(resourceDest);

            String filePath = resourcePath + fileName;
            File destination = new File(filePath);
            // 使用Files类的copy方法进行文件复制
            Files.copy(resourceDest.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件复制成功！");

            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(fileName);
            boolean update = consumerService.update(consumer);
            if(update){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "更新成功");
                jsonObject.put("pic", fileName);
            } else{
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MESSAGE, "更新失败");
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "保存文件失败：" + e.getMessage());
        }

        return jsonObject;
    }
}
