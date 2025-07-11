package com.zxd.service.impl;


import com.zxd.dao.RmstatusruleDao;
import com.zxd.pojo.Rmstatusrule;
import com.zxd.service.RmstatusruleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Rmstatusrule)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:13
 */
@Service("rmstatusruleService")
public class RmstatusruleServiceImpl implements RmstatusruleService {
    @Resource
    private RmstatusruleDao rmstatusruleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rmstatusruleId 主键
     * @return 实例对象
     */
    @Override
    public Rmstatusrule queryById(Integer rmstatusruleId) {
        return this.rmstatusruleDao.queryById(rmstatusruleId);
    }

    /**
     * 分页查询
     *
     * @param rmstatusrule 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Rmstatusrule> queryByPage(Rmstatusrule rmstatusrule, PageRequest pageRequest) {
        long total = this.rmstatusruleDao.count(rmstatusrule);
        return new PageImpl<>(this.rmstatusruleDao.queryAllByLimit(rmstatusrule, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rmstatusrule 实例对象
     * @return 实例对象
     */
    @Override
    public Rmstatusrule insert(Rmstatusrule rmstatusrule) {
        this.rmstatusruleDao.insert(rmstatusrule);
        return rmstatusrule;
    }

    /**
     * 修改数据
     *
     * @param rmstatusrule 实例对象
     * @return 实例对象
     */
    @Override
    public Rmstatusrule update(Rmstatusrule rmstatusrule) {
        this.rmstatusruleDao.update(rmstatusrule);
        return this.queryById(rmstatusrule.getRmstatusruleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rmstatusruleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rmstatusruleId) {
        return this.rmstatusruleDao.deleteById(rmstatusruleId) > 0;
    }
}
