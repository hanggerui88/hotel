package com.zxd.controller;


import com.zxd.pojo.Reservation;
import com.zxd.pojo.Staff;
import com.zxd.service.StaffService;
import com.zxd.util.md5;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:15
 */
@Controller
@RequestMapping("")
public class StaffController {
    /**
     * 服务对象
     */
    @Resource
    private StaffService staffService;

    /**
     * 分页查询
     *
     * @param staff 筛选条件
     * @param
     * @return 查询结果
     */

    @RequestMapping("staffList")
    public String queryByPage(@ModelAttribute Staff staff,
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
        Page<Staff> result=this.staffService.queryByPage(staff,pageRequest);

        model.addAttribute("sa",result.getContent());
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "staffList";
    }
    @RequestMapping("login")
    public String login(Model model,Integer id, String pd) {
        int j=0;
        Staff staff=staffService.queryById(id);
        if (staff!= null && (Objects.equals(staff.getPassword(), md5.encrypt(pd)))){
            model.addAttribute("staff",staff);
            model.addAttribute("power",1);
            return "main";
        }else{
            j=1;
        }
        model.addAttribute("j", j);
        return "login";
    }

    @RequestMapping("")
    public String index() {
        return "login";
    }
    @RequestMapping("/")
    public String index2() {
        return "login";
    }

    @RequestMapping("staff_add")
    public String add(Model model, Staff rr,String pd) {
        int j = 1;
        try {
            staffService.insert(rr,pd);
        } catch (Exception e) {
            j = 0;
            model.addAttribute("rr", rr);
        }
        model.addAttribute("j", j);
        return "addStaff";
    }


    @RequestMapping("staff_edit")
    public String edit(Integer id,Model model) throws IOException {

        Staff b=staffService.queryById(id);
        model.addAttribute("c", b);
        Staff r=staffService.queryById(staffService.queryById(id).getStaffId());
        model.addAttribute("r",r);
        return "editStaff";
    }

    @RequestMapping("staff_update")
    public String update(Model model,Staff c) {
        int j=1;
        try {
            staffService.update(c);
        } catch (Exception e) {
            j=0;
        }
        model.addAttribute("j",j);
        return "editStaff";
    }


    @RequestMapping("staff_delete")
    public String delete(Integer id) {
        staffService.deleteById(id);
        return "redirect:/staffList";
    }

    @RequestMapping("staff_delete_group")
    public String deleteg(Integer[] id) {
        for(int i=0;i<id.length;i++) {
            staffService.deleteById(id[i]);
        }
        return "redirect:/staffList";
    }
    @RequestMapping("staff_person")
    public String showPerson(Integer id,Model model) {
        Staff r=staffService.queryById(id);
        model.addAttribute("c", r);
        return "showPerson";
    }

}

