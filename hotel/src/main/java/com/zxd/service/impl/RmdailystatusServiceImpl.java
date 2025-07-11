package com.zxd.service.impl;

import com.zxd.dao.RmdailystatusDao;
import com.zxd.pojo.Rmdailystatus;
import com.zxd.service.RmdailystatusService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Rmdailystatus)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:12
 */
@Service("rmdailystatusService")
public class RmdailystatusServiceImpl implements RmdailystatusService {
    @Resource
    private RmdailystatusDao rmdailystatusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rmdailystatusId 主键
     * @return 实例对象
     */
    @Override
    public Rmdailystatus queryById(Integer rmdailystatusId) {
        return this.rmdailystatusDao.queryById(rmdailystatusId);
    }

    /**
     * 分页查询
     *
     * @param rmdailystatus 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Rmdailystatus> queryByPage(Rmdailystatus rmdailystatus, PageRequest pageRequest) {
        long total = this.rmdailystatusDao.count(rmdailystatus);
        return new PageImpl<>(this.rmdailystatusDao.queryAllByLimit(rmdailystatus, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rmdailystatus 实例对象
     * @return 实例对象
     */
    @Override
    public Rmdailystatus insert(Rmdailystatus rmdailystatus) {
        this.rmdailystatusDao.insert(rmdailystatus);
        return rmdailystatus;
    }

    /**
     * 修改数据
     *
     * @param rmdailystatus 实例对象
     * @return 实例对象
     */
    @Override
    public Rmdailystatus update(Rmdailystatus rmdailystatus) {
        this.rmdailystatusDao.update(rmdailystatus);
        return this.queryById(rmdailystatus.getRmdailystatusId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rmdailystatusId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rmdailystatusId) {
        return this.rmdailystatusDao.deleteById(rmdailystatusId) > 0;
    }
}
