package com.zxd.controller;


import com.zxd.pojo.*;
import com.zxd.service.RoomtypeService;
import com.zxd.service.VwDailyroomrateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (VwDailyroomrate)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
@Controller
@RequestMapping("")
public class VwDailyroomrateController {
    /**
     * 服务对象
     */
    @Resource
    private VwDailyroomrateService vwDailyroomrateService;
@Resource
private RoomtypeService roomtypeService;


    @RequestMapping("vwrateList")
    public String queryByPage(
            @ModelAttribute VwDailyroomrate vw_rate,
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
        Page<VwDailyroomrate> result = this.vwDailyroomrateService.queryByPage(vw_rate, pageRequest);


        model.addAttribute("cs", result.getContent());
        model.addAttribute("total", result.getTotalElements());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);
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
        return "vwrateList"; // 返回视图名称
    }
    @RequestMapping("vw_rate_search")
    public String se_tag(Model model,
                         @RequestParam(required = false) String ddate,
                         @ModelAttribute("vwDailyroomrate") VwDailyroomrate rsv) throws ParseException {

        if (ddate != null && !ddate.isEmpty()) {
            java.util.Date cin = new SimpleDateFormat("yyyy-MM-dd").parse(ddate);
            rsv.setDdate(new java.sql.Date(cin.getTime()));
        } else {
            rsv.setDdate(null);
        }
        PageRequest p = PageRequest.of(0, 1000);
        Page<VwDailyroomrate> result = this.vwDailyroomrateService.queryByPage(rsv, p);

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

        return "vwroomstatusList";
    }

}

