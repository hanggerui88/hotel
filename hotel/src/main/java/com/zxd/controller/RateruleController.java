package com.zxd.controller;


import com.zxd.pojo.*;
import com.zxd.service.RateruleService;
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
 * (Raterule)表控制层
 *
 * @author makejava
 * @since 2025-06-29 21:09:03
 */
@Controller
@RequestMapping("")
public class RateruleController {
    /**
     * 服务对象
     */
    @Resource
    private RateruleService rateruleService;
    @Resource
    private RoomtypeService roomtypeService;

    /**
     * 分页查询
     *
   分页对象
     * @return 查询结果
     */
    @RequestMapping("rateruleList")
    public String queryByPage(
            @ModelAttribute Raterule rr,
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
        Page<Raterule> result = this.rateruleService.queryByPage(rr, pageRequest);
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
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "rateruleList"; // 返回视图名称
    }

    @RequestMapping("show_rate_rule_add")
    public String add(Model model, Raterule rr) {
        int j = 1;
        try {
            rateruleService.insert(rr);
        } catch (Exception e) {
            j = 0;
            model.addAttribute("rr", rr);
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
        return "addRateRule";
    }


    @RequestMapping("rate_rule_edit")
    public String edit(Integer rateruleId,Model model) throws IOException {
        Raterule b=rateruleService.queryById(rateruleId);
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
        return "editRateRule";
    }

    @RequestMapping("rate_rule_update")
    public String update(Model model,Raterule c) {
        int j=1;
        try {
            rateruleService.update(c);
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
        return "editRateRule";
    }


    @RequestMapping("rate_rule_delete")
    public String delete(Integer rateruleId) {
        rateruleService.deleteById(rateruleId);
        return "redirect:/rateruleList";
    }

    @RequestMapping("rate_rule_delete_group")
    public String deleteg(Integer[] ids) {
        for(int i=0;i<ids.length;i++) {
            rateruleService.deleteById(ids[i]);
        }
        return "redirect:/rateruleList";
    }



    @RequestMapping("rate_rule_search")
    public String search(Model model,
                         @RequestParam(required = false) String cinDate,
                         @RequestParam(required = false) String coutDate,
                         @ModelAttribute("reservation") Raterule rsv) throws ParseException {

        // 手动转换日期
        if (cinDate != null && !cinDate.isEmpty()) {
            java.util.Date cin = new SimpleDateFormat("yyyy-MM-dd").parse(cinDate);
            rsv.setDateStart(new java.sql.Date(cin.getTime()));
        } else {
            rsv.setDateStart(null);
        }

        if (coutDate != null && !coutDate.isEmpty()) {
            java.util.Date cout = new SimpleDateFormat("yyyy-MM-dd").parse(coutDate);
            rsv.setDateEnd(new java.sql.Date(cout.getTime()));
        } else {
            rsv.setDateEnd(null);
        }
        PageRequest p=PageRequest.of(0,10000);
        Page<Raterule> list = rateruleService.queryByPage(rsv,p);

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
        model.addAttribute("cs", list.getContent());      // 当前页数据列表
        model.addAttribute("total", list.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", list.getTotalPages());// 总页数
        model.addAttribute("page", 10000);
        model.addAttribute("sear", 1);

        return "rateruleList";
    }
}

