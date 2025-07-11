package com.zxd.service.impl;

import com.zxd.dao.RateruleDao;
import com.zxd.pojo.Raterule;
import com.zxd.service.RateruleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Raterule)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 21:09:03
 */
@Service("rateruleService")
public class RateruleServiceImpl implements RateruleService {
    @Resource
    private RateruleDao rateruleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rateruleId 主键
     * @return 实例对象
     */
    @Override
    public Raterule queryById(Integer rateruleId) {
        return this.rateruleDao.queryById(rateruleId);
    }

    /**
     * 分页查询
     *
     * @param raterule 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Raterule> queryByPage(Raterule raterule, PageRequest pageRequest) {
        long total = this.rateruleDao.count(raterule);
        return new PageImpl<>(this.rateruleDao.queryAllByLimit(raterule, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param raterule 实例对象
     * @return 实例对象
     */
    @Override
    public Raterule insert(Raterule raterule) {
        this.rateruleDao.insert(raterule);
        return raterule;
    }

    /**
     * 修改数据
     *
     * @param raterule 实例对象
     * @return 实例对象
     */
    @Override
    public Raterule update(Raterule raterule) {
        this.rateruleDao.update(raterule);
        return this.queryById(raterule.getRateruleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rateruleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rateruleId) {
        return this.rateruleDao.deleteById(rateruleId) > 0;
    }
}
