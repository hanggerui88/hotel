package com.zxd.controller;


import com.zxd.pojo.Tag;
import com.zxd.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (Tag)表控制层
 *
 * @author makejava
 * @since 2025-06-29 20:46:16
 */
@Controller
@RequestMapping("")
public class TagController {
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;

    /**
     * 分页查询

     * @return 查询结果
     */
    @RequestMapping("tagList")
    public String queryByPage(@ModelAttribute Tag t,
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
        Page<Tag> result=this.tagService.queryByPage(t,pageRequest);

        model.addAttribute("cs",result.getContent());
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", page);
        model.addAttribute("sear", 1);

        return "tagList";
    }
    @RequestMapping("tag_add")
    public String add(Model model, Tag rr) {
        int j = 1;
        try {
            tagService.insert(rr);
        } catch (Exception e) {
            j = 0;
            model.addAttribute("rr", rr);
        }
        model.addAttribute("j", j);
        return "editTag";
    }


    @RequestMapping("tag_edit")
    public String edit(Integer id,Model model) throws IOException {

        Tag b=tagService.queryById(id);
        model.addAttribute("c", b);
        return "editTag";
    }

    @RequestMapping("tag_update")
    public String update(Model model, Tag c) {

        int j=1;
        try {
            Integer result = tagService.update(c);
            System.out.println("Service Result: " + result);
        } catch (Exception e) {
            j=0;
        }
        model.addAttribute("j",j);
        return "editTag";
    }


    @RequestMapping("tag_delete")
    public String delete(Integer id) {
        tagService.deleteById(id);
        return "redirect:/tagList";
    }

    @RequestMapping("tag_delete_group")
    public String deleteg(Integer[] id) {
        for(int i=0;i<id.length;i++) {
            tagService.deleteById(id[i]);
        }
        return "redirect:/tagList";
    }

    @RequestMapping("tag_search")
    public String se_tag(Model model, Tag t) {
       PageRequest p=PageRequest.of(0,10);
        Page<Tag> result= this.tagService.queryByPage(t,p);
        model.addAttribute("cs",result.getContent());
        model.addAttribute("total", result.getTotalElements());  // 总记录数
        model.addAttribute("totalPages", result.getTotalPages());// 总页数
        model.addAttribute("page", 10);
        model.addAttribute("sear", 1);
        return "tagList";
    }
}

