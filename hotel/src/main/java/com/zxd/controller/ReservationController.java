package com.zxd.controller;

import com.zxd.pojo.*;
import com.zxd.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (Reservation)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:11
 */
@Controller
@RequestMapping("")
public class ReservationController {
    /**
     * 服务对象
     */
    @Resource
    private ReservationService reservationService;
    @Resource
    private RoomtypeService roomtypeService;
    @Resource
    private  StaffService staffService;

    /**
     * 分页查询
     *
     * @param reservation 筛选条件
     * @param
     * @return 查询结果
     */
    @RequestMapping("reservationList")
    public String queryByPage(
            @ModelAttribute Reservation reservation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            Model model) {
        PageRequest pageRequest;

        if (sortBy != null) {
            Sort.Direction sortDirection = "ASC".equalsIgnoreCase(direction)
                    ? Sort.Direction.ASC
                    : Sort.Direction.DESC;

            pageRequest = PageRequest.of(page, size, sortDirection, sortBy);
        } else {
            pageRequest = PageRequest.of(page, size);
        }

        Page<Reservation> result = this.reservationService.queryByPage(reservation, pageRequest);

        com.zxd.pojo.page p = new page();
        p.setStart(0);
        p.setCount(1000);
        List<Roomtype> roomtypes = roomtypeService.list(p, new Roomtype());

        Map<Integer, String> map = new HashMap<>();
        for (Roomtype rt : roomtypes) {
            if (rt.getRmtypeId() != null) {
                map.put(rt.getRmtypeId(), rt.getName());
            }
        }
        List<Staff> staffs = staffService.list( new Staff(),p);
        Map<Integer, String> map3 = new HashMap<>();
        for (Staff s : staffs) {
            if (s.getStaffId() != null) {
                map3.put(s.getStaffId(), s.getUsername());
            }
        }
        model.addAttribute("map", map);
        model.addAttribute("map3", map3);
        model.addAttribute("cs", result.getContent());      // 当前页数据列表
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "reservationList";
    }


    @RequestMapping("rsv_edit")
    public String edit(Integer id,Model model) {
        Reservation re=reservationService.queryById(id);
        model.addAttribute("c",re);
        PageRequest p=PageRequest.of(0,10000);
        Page<Reservation> reservations = reservationService.queryByPage(re, p);
        List<Reservation> content = reservations.getContent();

        model.addAttribute("s",content);
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


        return "editReservation";
    }


    @RequestMapping("rsv_update")
    public String update(Model model,
                         @ModelAttribute("reservation") Reservation rsv,
                         @RequestParam(required = false) String cinDate,
                         @RequestParam(required = false) String coutDate) throws ParseException {
        if (cinDate != null && !cinDate.isEmpty()) {
            java.util.Date cin = new SimpleDateFormat("yyyy-MM-dd").parse(cinDate);
            rsv.setCinDate(new java.sql.Date(cin.getTime()));
        } else {
            rsv.setCinDate(null);
        }

        if (coutDate != null && !coutDate.isEmpty()) {
            java.util.Date cout = new SimpleDateFormat("yyyy-MM-dd").parse(coutDate);
            rsv.setCoutDate(new java.sql.Date(cout.getTime()));
        } else {
            rsv.setCoutDate(null);
        }

        int j=1;
        try {
            Reservation a= reservationService.update(rsv);
            System.out.println(a);
        } catch (Exception e) {
            j=0;
            System.out.println(e);

        }
        model.addAttribute("j", j);
        return "addReservation";
    }

    @RequestMapping("rsv_add")
    public String add(Model model,@ModelAttribute("reservation") Reservation rsv,
                      @RequestParam(required = false) String cinDate,
                      @RequestParam(required = false) String coutDate) throws ParseException {
        int j=1;

        if (cinDate != null && !cinDate.isEmpty()) {
            java.util.Date cin = new SimpleDateFormat("yyyy-MM-dd").parse(cinDate);
            rsv.setCinDate(new java.sql.Date(cin.getTime()));
        } else {
            rsv.setCinDate(null);
        }

        if (coutDate != null && !coutDate.isEmpty()) {
            java.util.Date cout = new SimpleDateFormat("yyyy-MM-dd").parse(coutDate);
            rsv.setCoutDate(new java.sql.Date(cout.getTime()));
        } else {
            rsv.setCoutDate(null);
        }
        try {
            reservationService.insert(rsv);
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
        model.addAttribute("j", j);
        return "addReservation";
    }

//1 转换
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private java.util.Date cinDate;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private java.util.Date coutDate;
//2绑定冲突
//    @ModelAttribute("reservation")

    @RequestMapping("search_rsv")
    public String search(Model model,
                         @RequestParam(required = false) String cinDate,
                         @RequestParam(required = false) String coutDate,
                         @ModelAttribute("reservation") Reservation rsv) throws ParseException {

        // 手动转换日期
        if (cinDate != null && !cinDate.isEmpty()) {
            java.util.Date cin = new SimpleDateFormat("yyyy-MM-dd").parse(cinDate);
            rsv.setCinDate(new java.sql.Date(cin.getTime()));
        } else {
            rsv.setCinDate(null);
        }

        if (coutDate != null && !coutDate.isEmpty()) {
            java.util.Date cout = new SimpleDateFormat("yyyy-MM-dd").parse(coutDate);
            rsv.setCoutDate(new java.sql.Date(cout.getTime()));
        } else {
            rsv.setCoutDate(null);
        }
        PageRequest p=PageRequest.of(0,10000);
        Page<Reservation> list = reservationService.queryByPage(rsv,p);

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
        List<Staff> staffs = staffService.list( new Staff(),page1);


        Map<Integer, String> map3 = new HashMap<>();
        for (Staff s : staffs) {
            if (s.getStaffId() != null) {
                map3.put(s.getStaffId(), s.getUsername());
            }
        }
        model.addAttribute("map", map);
        model.addAttribute("map3", map3);
        model.addAttribute("cs", list.getContent());      // 当前页数据列表
        model.addAttribute("total", list.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", list.getTotalPages());// 总页数
        model.addAttribute("page", 10000);
        model.addAttribute("sear", 1);

        return "reservationList";
    }
}

