package com.zxd.controller;


import com.zxd.dao.RoomtypeDao;
import com.zxd.pojo.*;
import com.zxd.service.RmstatusruleService;
import com.zxd.service.RoomService;
import com.zxd.service.RoomtypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Rmstatusrule)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
@Controller
@RequestMapping("")
public class RmstatusruleController {
    /**
     * 服务对象
     */
    @Resource
    private RmstatusruleService rmstatusruleService;
    @Resource
    private RoomService roomService;
    @Resource
    RoomtypeService roomtypeService;



    @RequestMapping("rmstatusruleList")
    public String queryByPage(
            @ModelAttribute Rmstatusrule rm_r,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            Model model) {

        PageRequest pageRequest;

        if (sortBy != null) {
            // 创建带排序的PageRequest
            Sort.Direction sortDirection = "ASC".equalsIgnoreCase(direction)
                    ? Sort.Direction.ASC
                    : Sort.Direction.DESC;

            pageRequest = PageRequest.of(page, size, sortDirection, sortBy);
        } else {
            // 创建无排序的PageRequest
            pageRequest = PageRequest.of(page, size);
        }
        // 调用原有Service方法
        Page<Rmstatusrule> result = this.rmstatusruleService.queryByPage(rm_r, pageRequest);
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
        model.addAttribute("map", map);
        model.addAttribute("cs", result.getContent());      // 当前页数据列表
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "rmstatusruleList";
    }

    @RequestMapping("room_rule_add")
    public String add(Model model, Rmstatusrule rm_rule) throws ParseException {

        int j = 1;
        try {
            rmstatusruleService.insert(rm_rule);
        } catch (Exception e) {
            j = 0;
            System.out.println(e);
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
        model.addAttribute("map", map);
        return "addRoomRule";
    }


    @RequestMapping("room_rule_edit")
    public String edit(Integer id,Model model) throws IOException {
        Rmstatusrule b=rmstatusruleService.queryById(id);
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


        model.addAttribute("map", map);
        return "editRoomRule";
    }

    @RequestMapping("room_rule_update")
    public String update(Model model,Rmstatusrule c) {
        int j=1;
        try {
            rmstatusruleService.update(c);
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
        model.addAttribute("map", map);
        model.addAttribute("j",j);
        return "editRoomRule";
    }


    @RequestMapping("room_rule_delete")
    public String delete(Integer id) {
        rmstatusruleService.deleteById(id);
        return "redirect:/rmstatusruleList";
    }

    @RequestMapping("room_rule_delete_group")
    public String deleteg(Integer[] ids) {
        for(int i=0;i<ids.length;i++) {
            rmstatusruleService.deleteById(ids[i]);
        }
        return "redirect:/rmstatusruleList";
    }


    //删除重定向，查询不重定向
    @RequestMapping("room_rule_search")
    public String se_tag(Model model,
                         @RequestParam(required = false) String dateStart,
                         @RequestParam(required = false) String dateEnd,
                         @ModelAttribute("reservation") Rmstatusrule rsv) throws ParseException {

        // 手动转换日期
        if (dateStart != null && !dateStart.isEmpty()) {
            java.util.Date cin = new SimpleDateFormat("yyyy-MM-dd").parse(dateStart);
            rsv.setDateStart(new java.sql.Date(cin.getTime()));
        } else {
            rsv.setDateStart(null);
        }

        if (dateEnd != null && !dateEnd.isEmpty()) {
            java.util.Date cout = new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd);
            rsv.setDateEnd(new java.sql.Date(cout.getTime()));
        } else {
            rsv.setDateEnd(null);
        }
        PageRequest p = PageRequest.of(0, 1000);

        Page<Rmstatusrule> result = this.rmstatusruleService.queryByPage(rsv, p);

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

        model.addAttribute("map", map);
        model.addAttribute("cs", result.getContent());
        model.addAttribute("total", result.getTotalElements());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("page", 0);
        model.addAttribute("sear", 1);

        return "rmstatusruleList";
    }

}

