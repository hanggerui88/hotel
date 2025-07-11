package com.zxd.controller;


import com.zxd.pojo.Room;
import com.zxd.pojo.Roomtype;
import com.zxd.pojo.Tag;
import com.zxd.pojo.page;
import com.zxd.service.RoomService;
import com.zxd.service.RoomtypeService;
import com.zxd.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Room)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
@Controller
@RequestMapping("")
public class RoomController {
    /**
     * 服务对象
     */
//    不要自己创建对象，用已经被注入的类
    @Resource
    private RoomService roomService;
    @Resource
    private RoomtypeService roomtypeService;
    @Resource
    private TagService tagService;


    /**
     * 分页查询
     *
     * @param room 筛选条件
     * @param
     * @return 查询结果
     */
    @RequestMapping("roomList")
    public String queryByPage(@ModelAttribute Room room,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String sortBy,
                              @RequestParam(defaultValue = "Desc") String direction,
                              Model model){
        PageRequest pageRequest;
        if (sortBy!=null){
            Sort.Direction sortDiction="ASC".equalsIgnoreCase(direction)?Sort.Direction.ASC:Sort.Direction.DESC;
            pageRequest  = PageRequest.of(page, size, sortDiction, sortBy);
        }else{
            pageRequest=PageRequest.of(page,size);
        }
        Page<Room> result=this.roomService.queryByPage(room,pageRequest);

        com.zxd.pojo.page page1 = new page();
        page1.setStart(0);page1.setCount(10000);
        List<Roomtype> rt = this.roomtypeService.list(page1, new Roomtype());
        List<Tag> rt2 = this.tagService.list(page1, new Tag());

        Map<Integer, String> map = new HashMap<>();
        for (Roomtype r : rt) {
            Integer id = r.getRmtypeId();
            map.put(id, r.getName());
        }

// 构建标签映射
        Map<Integer, String> map2 = new HashMap<>();
        for (Tag r : rt2) {
            Integer id = r.getTagId();
            map2.put(id, r.getContent());
        }
        model.addAttribute("cs",result.getContent());
        model.addAttribute("map",map);
        model.addAttribute("map2",map2);
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "roomList";
    }

    @RequestMapping("room_add")
    public String add(Model model, Room room) {
        int j = 1;
        try {
            roomService.insert(room);
        } catch (Exception e) {
            j = 0;
            model.addAttribute("room", room);
        }
        model.addAttribute("j", j);
        com.zxd.pojo.page page1 = new page();
        page1.setStart(0);
        page1.setCount(1000);
        List<Roomtype> roomtypes = roomtypeService.list(page1, new Roomtype());

        Map<Integer, String> map = new HashMap<>();
        for (Roomtype rt : roomtypes) {
            if (rt.getRmtypeId() != null) {
                map.put(rt.getRmtypeId(), rt.getName());
            }
        }

        List<Tag> tags = tagService.list(page1, new Tag());
        Map<Integer, String> map2 = new HashMap<>();
        for (Tag tag : tags) {
            if (tag.getTagId() != null) {
                map2.put(tag.getTagId(), tag.getContent());
            }
        }

        model.addAttribute("map", map);
        model.addAttribute("map2", map2);
        return "addRoom";
    }


    @RequestMapping("room_edit")
    public String edit(Integer rmNumber,Model model) throws IOException {

        Room b=roomService.queryById(rmNumber);
        model.addAttribute("c", b);
        com.zxd.pojo.page page1 = new page();
        page1.setStart(0);
        page1.setCount(1000);
        List<Roomtype> roomtypes = roomtypeService.list(page1, new Roomtype());

        Map<Integer, String> map = new HashMap<>();
        for (Roomtype rt : roomtypes) {
            if (rt.getRmtypeId() != null) {
                map.put(rt.getRmtypeId(), rt.getName());
            }
        }

        List<Tag> tags = tagService.list(page1, new Tag());
        Map<Integer, String> map2 = new HashMap<>();
        for (Tag tag : tags) {
            if (tag.getTagId() != null) {
                map2.put(tag.getTagId(), tag.getContent());
            }
        }

        model.addAttribute("map", map);
        model.addAttribute("map2", map2);
        return "editRoom";
    }

    @RequestMapping("room_update")
    public String update(Model model,Room c) {
        int j=1;
        try {
            roomService.update(c);
        } catch (Exception e) {
            j=0;
        }
        com.zxd.pojo.page page1 = new page();
        page1.setStart(0);
        page1.setCount(1000);
        List<Roomtype> roomtypes = roomtypeService.list(page1, new Roomtype());

        Map<Integer, String> map = new HashMap<>();
        for (Roomtype rt : roomtypes) {
            if (rt.getRmtypeId() != null) {
                map.put(rt.getRmtypeId(), rt.getName());
            }
        }

        List<Tag> tags = tagService.list(page1, new Tag());
        Map<Integer, String> map2 = new HashMap<>();
        for (Tag tag : tags) {
            if (tag.getTagId() != null) {
                map2.put(tag.getTagId(), tag.getContent());
            }
        }

        model.addAttribute("map", map);
        model.addAttribute("map2", map2);
        model.addAttribute("j",j);
        return "editRoom";
    }


    @RequestMapping("room_delete")
    public String delete(Integer rmNumber) {
        roomService.deleteById(rmNumber);
        return "redirect:/roomList";
    }

    @RequestMapping("room_delete_group")
    public String deleteg(Integer[] rmNumbers) {
        for(int i=0;i<rmNumbers.length;i++) {
            roomService.deleteById(rmNumbers[i]);
        }
        return "redirect:/roomList";
    }
//删除重定向，查询不重定向
@RequestMapping("room_search")
public String se_tag(Model model,
                     @ModelAttribute Room searchRoom) {
    PageRequest p = PageRequest.of(0, 1000);
    Page<Room> result = this.roomService.queryByPage(searchRoom, p);

    com.zxd.pojo.page page1 = new page();
    page1.setStart(0);
    page1.setCount(1000);
    List<Roomtype> roomtypes = roomtypeService.list(page1, new Roomtype());

    Map<Integer, String> map = new HashMap<>();
    for (Roomtype rt : roomtypes) {
        if (rt.getRmtypeId() != null) {
            map.put(rt.getRmtypeId(), rt.getName());
        }
    }

    List<Tag> tags = tagService.list(page1, new Tag());
    Map<Integer, String> map2 = new HashMap<>();
    for (Tag tag : tags) {
        if (tag.getTagId() != null) {
            map2.put(tag.getTagId(), tag.getContent());
        }
    }

    model.addAttribute("map", map);
    model.addAttribute("map2", map2);
    model.addAttribute("cs", result.getContent());
    model.addAttribute("total", result.getTotalElements());
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("page", 0);
    model.addAttribute("sear", 1);
    model.addAttribute("searchRoom", searchRoom);

    return "roomList";
}


}

