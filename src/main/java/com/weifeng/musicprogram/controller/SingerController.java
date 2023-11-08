package com.weifeng.musicprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.service.SingerService;
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
@RequestMapping("/singer")
@CrossOrigin("*")
//@CrossOrigin("http://localhost:8081")
public class SingerController {
    private final ResourceLoader resourceLoader;
    private final ServletContext servletContext;

    @Autowired
    public SingerController(ResourceLoader resourceLoader, ServletContext servletContext) {
        this.resourceLoader = resourceLoader;
        this.servletContext = servletContext;
    }

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     *
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addsinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introducation = request.getParameter("introducation").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(date);
        singer.setLocation(location);
        singer.setIntroducation(introducation);
        boolean insert = singerService.insert(singer);
        if (insert) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "添加失败");
        return jsonObject;
    }

    /**
     * 修改歌手
     *
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updatesinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
//        String pic=request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introducation = request.getParameter("introducation").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
//        singer.setPic(pic);
        singer.setBirth(date);
        singer.setLocation(location);
        singer.setIntroducation(introducation);
        boolean insert = singerService.update(singer);
        if (insert) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "修改失败");
        return jsonObject;
    }

    /**
     * 删除歌手
     *
     * @param request
     * @return
     */
    @GetMapping("/delete/{id}")
    public Object removesinger(HttpServletRequest request, @PathVariable("id") int id) {
        boolean remove = singerService.remove(id);
        return remove;
    }

    /**
     * 查询一个歌手  id
     *
     * @param request
     * @return
     */
    @GetMapping("/selectById/{id}")
    public Object Onesinger(HttpServletRequest request, @PathVariable("id") int id) {
        return singerService.selectById(id);
    }

    /**
     * 查所有歌手
     *
     * @param request
     * @return
     */
    @GetMapping("/allSinger")
    public Object allSinger(HttpServletRequest request) {
        return singerService.allSinger();
    }

    /**
     * 根据名字查歌手
     *
     * @param request
     * @param name
     * @return
     */
    @GetMapping("/singerByName/{name}")
    public Object singerByName(HttpServletRequest request, @PathVariable("name") String name) {
        return singerService.singerByName(name);
    }

    /**
     * 根据性别查歌手
     *
     * @param request
     * @param sex
     * @return
     */
    @GetMapping("/singerBySex/{sex}")
    public Object singerBySex(HttpServletRequest request, @PathVariable("sex") Integer sex) {
        return singerService.singerBySex(sex);
    }


    //todo 更新歌手图片
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
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
        String resourcePath = "D:\\java\\MusicProgram\\src\\main\\resources\\static\\images\\";
        File resourceDest = new File(resourceStaticPath + fileName);

        try {
            // 保存文件到 target 目录
            file.transferTo(resourceDest);

            String filePath = resourcePath + fileName;
            File destination = new File(filePath);
            // 使用Files类的copy方法进行文件复制
            Files.copy(resourceDest.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件复制成功！");

            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(fileName);
            boolean update = singerService.update(singer);
            if (update) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "更新成功");
                jsonObject.put("pic", fileName);
            } else {
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
