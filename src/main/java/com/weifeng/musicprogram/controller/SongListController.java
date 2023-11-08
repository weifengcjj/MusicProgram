package com.weifeng.musicprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.SongList;
import com.weifeng.musicprogram.service.SingerService;
import com.weifeng.musicprogram.service.SongListService;
import com.weifeng.musicprogram.utils.Consts;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 歌单控制类
 */
@RestController
@RequestMapping("/songList")
@CrossOrigin("*")
public class SongListController {
    private final ResourceLoader resourceLoader;
    private final ServletContext servletContext;

    @Autowired
    public SongListController(ResourceLoader resourceLoader, ServletContext servletContext) {
        this.resourceLoader = resourceLoader;
        this.servletContext = servletContext;
    }

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     *
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addsinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introducation = request.getParameter("introducation").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroducation(introducation);
        songList.setStyle(style);
        boolean insert = songListService.insert(songList);
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
     * 修改歌单
     *
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updatesinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String introducation = request.getParameter("introducation").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroducation(introducation);
        songList.setStyle(style);
        boolean update = songListService.update(songList);
        if (update) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "修改失败");
        return jsonObject;
    }

    /**
     * 删除歌单
     *
     * @param request
     * @return
     */
    @GetMapping("/delete/{id}")
    public Object removesinger(HttpServletRequest request, @PathVariable("id") int id) {
        boolean remove = songListService.remove(id);
        return remove;
    }

    /**
     * 查询一个歌单  id
     *
     * @param request
     * @return
     */
    @GetMapping("/selectById/{id}")
    public Object Onesinger(HttpServletRequest request, @PathVariable("id") int id) {
        return songListService.selectSongListById(id);
    }

    /**
     * 查所有歌单
     *
     * @param request
     * @return
     */
    @GetMapping("/allSongList")
    public Object allSinger(HttpServletRequest request) {
        return songListService.allSongList();
    }

    /**
     * 根据标题精确查歌单
     *
     * @param request
     * @param title
     * @return
     */
    @GetMapping("/songListByTitle/{title}")
    public Object songListByTitle(HttpServletRequest request, @PathVariable("title") String title) {
        return songListService.SongListByTitle(title);
    }

    /**
     * 根据标题模糊查歌单
     *
     * @param request
     * @param title
     * @return
     */
    @GetMapping("/SongListLikeTitle/{title}")
    public Object SongListLikeTitle(HttpServletRequest request, @PathVariable("title") String title) {
        return songListService.SongListLikeTitle("%" + title + "%");
    }

    /**
     * 根据风格模糊查歌单
     *
     * @param request
     * @param style
     * @return
     */
    @GetMapping("/SongListLikeStyle/{style}")
    public Object SongListLikeStyle(HttpServletRequest request, @PathVariable("style") String style) {
        return songListService.SongListLikeStyle("%" + style + "%");
    }


    //todo 更新歌单图片
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }

        // 获取静态资源目录路径
        String resourceStaticPath;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/songlistPic/");
            resourceStaticPath = resource.getFile().getAbsolutePath() + File.separator;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "获取静态资源目录失败" + e.getMessage());
            return jsonObject;
        }

        String resourcePath = "D:\\java\\MusicProgram\\src\\main\\resources\\static\\songlistPic\\";
//        System.out.println("resourcePath===="+resourcePath);//resourcePath

        System.out.println(resourceStaticPath);
        // 文件名：当前时间戳和原文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();

        // 实际的文件地址
        File resourceDest = new File(resourceStaticPath + fileName);

        try {
            // 保存文件到 target 目录
            file.transferTo(resourceDest);

            String filePath = resourcePath + fileName;
            File destination = new File(filePath);
            // 使用Files类的copy方法进行文件复制
            Files.copy(resourceDest.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件复制成功！");

            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(fileName);
            boolean update = songListService.update(songList);
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
