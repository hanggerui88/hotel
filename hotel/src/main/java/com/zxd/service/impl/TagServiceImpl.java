package com.zxd.service.impl;

import com.zxd.dao.TagDao;
import com.zxd.pojo.Roomtype;
import com.zxd.pojo.Tag;
import com.zxd.pojo.page;
import com.zxd.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:17
 */
@Service("tagService")
@Transactional
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;
    @Resource
    private TagService tagService;
    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(Integer tagId) {
        return this.tagDao.queryById(tagId);
    }

    /**
     * 分页查询
     *
     * @param tag 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Tag> queryByPage(Tag tag, PageRequest pageRequest) {
        long total = this.tagDao.count(tag);
        return new PageImpl<>(this.tagDao.queryAllByLimit(tag, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag insert(Tag tag) {
        this.tagDao.insert(tag);
        return tag;
    }

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(Tag tag) {
        System.out.println(1);
        // 验证ID
        if (tag.getTagId() == null) {
            System.out.println(2);
            throw new IllegalArgumentException("标签ID不能为空");
        }

        // 查询现有记录
        Tag existingTag = tagDao.queryById(tag.getTagId());
        if (existingTag == null) {
            System.out.println(3);
            throw new RuntimeException("找不到记录");
        }

        int result = tagDao.update(tag);
        System.out.println(4);

        return result;
    }




    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tagId) {
        return this.tagDao.deleteById(tagId) > 0;
    }

    @Override
    public List<Tag> list(page page1, Tag tag) {
        return tagDao.list(page1,tag);
    }
}
