package com.zxd.controller;


import com.zxd.pojo.Roomtype;
import com.zxd.service.RoomtypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (Roomtype)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
@Controller
@RequestMapping("")
public class RoomtypeController {
    /**
     * 服务对象
     */
    @Resource
    private RoomtypeService roomtypeService;


    @RequestMapping("roomtypeList")
    public String queryByPage(@ModelAttribute Roomtype rt,
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
        //调用原有service
        Page<Roomtype> result=this.roomtypeService.queryByPage(rt,pageRequest);

        model.addAttribute("cs",result.getContent());
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "roomtypeList";
    }

    @RequestMapping("room_type_add")
    public String add( Model model,Roomtype roomType) {
        int j=1;
        try {
            roomtypeService.insert(roomType);
        } catch (Exception e) {
            j=0;
        }
        model.addAttribute("j", j);
        return "addRoomType";
    }
    @RequestMapping("room_type_edit")
    public String edit(Integer rmtypeId,Model model) throws IOException {
        Roomtype a=roomtypeService.queryById(rmtypeId);
        model.addAttribute("c", a);
        return "editRoomType";
    }

    @RequestMapping("room_type_update")
    public String update(Model model,Roomtype c) {
        int j=1;
        try {
            roomtypeService.update(c);
        } catch (Exception e) {
            j=0;
        }
        model.addAttribute("j",j);
        return "editRoomType";
    }
    @RequestMapping("room_type_delete")
    public String delete(Integer rmtypeId) {
        roomtypeService.deleteById(rmtypeId);
        return "redirect:/roomtypeList";
    }

    @RequestMapping("room_type_delete_group")
    public String deleteg(Integer[] rmtypeIds) {
        for(int i=0;i<rmtypeIds.length;i++) {
            roomtypeService.deleteById(rmtypeIds[i]);
        }
        return "redirect:/roomtypeList";
    }
}

