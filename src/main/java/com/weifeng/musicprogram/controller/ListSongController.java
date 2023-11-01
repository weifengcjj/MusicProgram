package com.weifeng.musicprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifeng.musicprogram.Model.ListSong;
import com.weifeng.musicprogram.service.ListSongService;
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

/**
 * 歌单 里的歌曲控制
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/listsong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    /**
     * 添加歌曲
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addListSong(HttpServletRequest request){
            JSONObject jsonObject=new JSONObject();
            String songId=request.getParameter("songId").trim();
            String songListId=request.getParameter("songListId").trim();

            ListSong listSong=new ListSong();
            listSong.setSongId(Integer.parseInt(songId));
            listSong.setSongListId(Integer.parseInt(songListId));
            boolean insert = listSongService.insert(listSong);
            if(insert){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "歌单歌曲上传成功");
            } else{
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MESSAGE, "歌单歌曲上传失败");
            }
        return jsonObject;
    }

    /**
     * 删除歌单里的歌曲
     * @param songId
     * @param listsongId
     * @return
     *
     * */
    @GetMapping("/delete/{songId}/{listsongId}")

    public Object delete(@PathVariable("songId") int songId,@PathVariable("listsongId") int listsongId){
        boolean remove = listSongService.remove(songId,listsongId);
        return remove;
    }

    /**
     * 根据歌单id查询歌曲
     */
    @GetMapping("/detail/{id}")
    public Object selectListSongById(@PathVariable("id") int id){
        return listSongService.ListSongBySongListId(id);
    }
}
