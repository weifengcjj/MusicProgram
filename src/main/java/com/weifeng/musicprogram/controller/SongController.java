package com.weifeng.musicprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifeng.musicprogram.Model.Singer;
import com.weifeng.musicprogram.Model.Song;
import com.weifeng.musicprogram.service.SongService;
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
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/song")
public class SongController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public SongController(ResourceLoader resourceLoader, ServletContext servletContext) {
        this.resourceLoader = resourceLoader;
    }
    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addSong(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String singerId=request.getParameter("singerId").trim();
        String name=request.getParameter("name").trim();
        String introducation=request.getParameter("introducation").trim();
        String pic="OIP-C (11).jpg";
        String lyric=request.getParameter("lyric").trim(); //歌词

        //上传歌曲文件
        if(file.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "歌曲上传失败");
            return jsonObject;
        }
        // 获取静态资源目录路径
        String resourceStaticPath;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/song/");
            resourceStaticPath = resource.getFile().getAbsolutePath() + File.separator;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "获取静态资源目录失败" + e.getMessage());
            return jsonObject;
        }
        System.out.println(resourceStaticPath);
        String resourcePath ="D:\\java\\MusicProgram\\src\\main\\resources\\static\\song\\";

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

            Song song=new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroducation(introducation);
            song.setLyric(lyric);
            song.setPic(pic);
            song.setUrl(fileName);
            boolean insert = songService.insert(song);
            if(insert){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "歌曲上传成功");
                jsonObject.put("pic", fileName);
            } else{
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MESSAGE, "歌曲上传失败");
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "保存文件失败：" + e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 修改歌曲
     */
    @PostMapping("/update")
    public Object updatesinger(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String name=request.getParameter("name").trim();
        String lyric=request.getParameter("lyric").trim();
        String singerId=request.getParameter("singerId").trim();
        String introducation=request.getParameter("introducation").trim();
        Song song=new Song();
        song.setId(Integer.parseInt(id));
        song.setSingerId(Integer.parseInt(singerId));
        song.setName(name);
        song.setLyric(lyric);
        song.setIntroducation(introducation);
        boolean update = songService.update(song);
        if(update){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MESSAGE,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MESSAGE,"修改失败");
        return jsonObject;
    }

    /**
     * 删除歌曲
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Object removesinger(@PathVariable("id") int id){
        //todo 先查询到数据库中对应的文件地址，然后再删除掉它
        JSONObject jsonObject=new JSONObject();
        // 获取静态资源目录路径
        String resourceStaticPath;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/song/");
            resourceStaticPath = resource.getFile().getAbsolutePath() + File.separator;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "获取静态资源目录失败" + e.getMessage());
            return jsonObject;
        }
        String resourcePath ="D:\\java\\MusicProgram\\src\\main\\resources\\static\\song\\";

        Song song = songService.selectSongById(id);
        String url=song.getUrl();
        String delUrl=resourceStaticPath+url;
        System.out.println(delUrl);

        String delurl2=resourcePath+url;

        File file=new File(delUrl);
        File file2=new File(delurl2);
        boolean delete = file.delete();
        boolean delete2 = file2.delete();
        boolean remove = false;         //删除数据库
        if(delete&&delete2){ //如果文件删掉了
            System.out.println("文件删掉了");
            remove = songService.remove(id);    ////删除数据库

        }
        return remove;
    }

    //todo 更新歌曲图片
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file")MultipartFile file, @RequestParam("id")int id,@RequestParam("singerid")int singerid){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }
        // 获取静态资源目录路径
        String resourceStaticPath;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/songPic/");
            resourceStaticPath = resource.getFile().getAbsolutePath() + File.separator;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "获取静态资源目录失败" + e.getMessage());
            return jsonObject;
        }
        System.out.println(resourceStaticPath);

        String resourcePath ="D:\\java\\MusicProgram\\src\\main\\resources\\static\\songPic\\";
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

            Song song=new Song();
            song.setId(id);
            song.setSingerId(singerid);
            song.setPic(fileName);
            boolean update = songService.update(song);
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

    //todo 更新歌曲文件
    @PostMapping("/updateSong")
    public Object updateSong(@RequestParam("file")MultipartFile file, @RequestParam("id")int id,@RequestParam("singerid")int singerid){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }
        // 获取静态资源目录路径
        String resourceStaticPath;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/song/");
            resourceStaticPath = resource.getFile().getAbsolutePath() + File.separator;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "获取静态资源目录失败" + e.getMessage());
            return jsonObject;
        }
        String resourcePath ="D:\\java\\MusicProgram\\src\\main\\resources\\static\\song\\";

        Song song = songService.selectSongById(id);
        String url=song.getUrl();
        String delUrl=resourceStaticPath+url;
        String delUrl2=resourcePath+url;
        System.out.println(delUrl);
        // 文件名：当前时间戳和原文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();

        // 实际的文件地址
        File resourceDest = new File(resourceStaticPath + fileName);
        try {
            song.setUrl(fileName);
            File file1=new File(delUrl);
            File file2=new File(delUrl2);
            boolean delete = file1.delete();
            boolean delete2 = file2.delete();
            if(delete&&delete2){ //如果文件删掉了
                System.out.println("文件更新时删除原来的资源");

                // 保存文件到 target 目录
                file.transferTo(resourceDest);

                String filePath = resourcePath + fileName;
                File destination = new File(filePath);
                // 使用Files类的copy方法进行文件复制
                Files.copy(resourceDest.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("文件复制成功！");

                boolean update = songService.update(song);
                if(update){
                    jsonObject.put(Consts.CODE, 1);
                    jsonObject.put(Consts.MESSAGE, "更新成功");
                    jsonObject.put("pic", fileName);
                } else{
                    jsonObject.put(Consts.CODE, 0);
                    jsonObject.put(Consts.MESSAGE, "更新失败");
                }
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "保存文件失败：" + e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 查所有歌曲
     * @param request
     * @return
     */
    @GetMapping("/allSong")
    public Object allSong(HttpServletRequest request){
        return songService.allSong();
    }

    /**
     * 根据歌手id查询歌曲
     */
    @GetMapping("/Singer/detail/{id}")
    public Object Singerdetail(@PathVariable("id") int id){
        return songService.SongBySiger(id);
    }

    /**
     * 根据歌曲id查询歌曲
     */
    @GetMapping("/detail/{id}")
    public Object selectSongById(@PathVariable("id") int id){
        return songService.selectSongById(id);
    }

    /**
     * 根据歌曲名称精准查询歌曲
     */
    @GetMapping("/SongByName/{name}")
    public Object selectSongByName(@PathVariable("name") String name){
        System.out.println(name);
        List<Song> songs = songService.SongByName(name);
        return songs;
    }

    /**
     * 根据歌曲名称精准模糊歌曲
     */
    @GetMapping("/likeSongByName/{name}")
    public Object selectlikeSongByName(@PathVariable("name") String name){
        System.out.println(name);
        String name1="%"+name+"%";
        System.out.println(name);
        List<Song> songs = songService.likeSongByName(name1);
        return songs;
    }


}
