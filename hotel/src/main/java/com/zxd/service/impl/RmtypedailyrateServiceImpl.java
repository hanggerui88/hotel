package com.zxd.service.impl;


import com.zxd.dao.RmtypedailyrateDao;
import com.zxd.pojo.Rmtypedailyrate;
import com.zxd.service.RmtypedailyrateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Rmtypedailyrate)表服务实现类
 *
 * @author makejava
 * @since 2025-06-29 20:46:14
 */
@Service("rmtypedailyrateService")
public class RmtypedailyrateServiceImpl implements RmtypedailyrateService {
    @Resource
    private RmtypedailyrateDao rmtypedailyrateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rmtypedailyrateId 主键
     * @return 实例对象
     */
    @Override
    public Rmtypedailyrate queryById(Integer rmtypedailyrateId) {
        return this.rmtypedailyrateDao.queryById(rmtypedailyrateId);
    }

    /**
     * 分页查询
     *
     * @param rmtypedailyrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Rmtypedailyrate> queryByPage(Rmtypedailyrate rmtypedailyrate, PageRequest pageRequest) {
        long total = this.rmtypedailyrateDao.count(rmtypedailyrate);
        return new PageImpl<>(this.rmtypedailyrateDao.queryAllByLimit(rmtypedailyrate, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rmtypedailyrate 实例对象
     * @return 实例对象
     */
    @Override
    public Rmtypedailyrate insert(Rmtypedailyrate rmtypedailyrate) {
        this.rmtypedailyrateDao.insert(rmtypedailyrate);
        return rmtypedailyrate;
    }

    /**
     * 修改数据
     *
     * @param rmtypedailyrate 实例对象
     * @return 实例对象
     */
    @Override
    public Rmtypedailyrate update(Rmtypedailyrate rmtypedailyrate) {
        this.rmtypedailyrateDao.update(rmtypedailyrate);
        return this.queryById(rmtypedailyrate.getRmtypedailyrateId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rmtypedailyrateId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rmtypedailyrateId) {
        return this.rmtypedailyrateDao.deleteById(rmtypedailyrateId) > 0;
    }
}
